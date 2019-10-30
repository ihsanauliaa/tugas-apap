package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.AsuransiModel;
import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.model.EmergencyContactModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.repository.AsuransiDB;
import apap.tugas.sipas.service.AsuransiService;
import apap.tugas.sipas.service.DiagnosisService;
import apap.tugas.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PasienController {

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Qualifier("diagnosisServiceImpl")
    @Autowired
    private DiagnosisService diagnosisService;

    @Qualifier("asuransiServiceImpl")
    @Autowired
    private AsuransiService asuransiService;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("active", "active");
        model.addAttribute("listPasien", pasienService.getPasienList());
        return "home";
    }

    @RequestMapping(value = "/pasien/add", method = RequestMethod.GET)
    public String addPasienFormPage(Model model) {
        PasienModel newPasien = new PasienModel();
        EmergencyContactModel newEmergencyContactModel = new EmergencyContactModel();
        List<AsuransiModel> newAsuransiModel = pasienService.getAsuransiList();

        AsuransiModel newAsuransi = new AsuransiModel();

        List<AsuransiModel> listAsuransi = new ArrayList<>();
        listAsuransi.add(newAsuransi);
        newPasien.setListAsuransi(listAsuransi);
        // newPasien.setEmergencyContactModel(newEmergencyContactModel);

        model.addAttribute("emergencyContact", newEmergencyContactModel);
        model.addAttribute("pasien", newPasien);
        model.addAttribute("asuransi", newAsuransiModel);
        return "form-add-pasien";
    }

    @RequestMapping(path = "/pasien/add", method = RequestMethod.POST)
    public String addPasienSubmit(@ModelAttribute PasienModel pasien, @ModelAttribute EmergencyContactModel emergencyContact, @ModelAttribute AsuransiModel asuransiModel, Model model) throws ParseException {
        pasienService.generateCode(pasien);
        pasien.setEmergencyContactModel(emergencyContact);
        pasienService.addPasien(pasien);
        pasienService.addEmergencyContact(emergencyContact);

        List<AsuransiModel> listAsuransi = new ArrayList<>();
        listAsuransi.add(asuransiModel);
        pasien.setListAsuransi(listAsuransi);

        List<PasienModel> listPasien = new ArrayList<>();
        listPasien.add(pasien);
        asuransiModel.setListPasien(listPasien);

        model.addAttribute("namaPasien", pasien.getNamaPasien());
        model.addAttribute("kodePasien", pasien.getKodePasien());
        model.addAttribute("namaEmergencyContact", emergencyContact.getNamaEC());
        return "add-pasien";
    }

    @RequestMapping(value = "/pasien/add", method = RequestMethod.POST, params = {"addRow"})
    public String addRow(@ModelAttribute PasienModel pasien, Model model) {
        if (pasien.getListAsuransi() == null) {
            pasien.setListAsuransi(new ArrayList<>());
        }

        EmergencyContactModel newEmergencyContactModel = new EmergencyContactModel();

        pasien.getListAsuransi().add(new AsuransiModel());
        List<AsuransiModel> newAsuransiModel = pasienService.getAsuransiList();

        model.addAttribute("pasien", pasien);
        model.addAttribute("emergencyContact", newEmergencyContactModel);
        model.addAttribute("asuransi", newAsuransiModel);
        return "form-add-pasien";
    }

    @RequestMapping("/pasien")
    public String view(
            @RequestParam(value = "nikPasien") Long nikPasien, Model model
    ) {
        model.addAttribute("targetPasien", pasienService.getPasienByNIKPasien(nikPasien).get());
        model.addAttribute("asuransiPasien", pasienService.getPasienByNIKPasien(nikPasien).get().getListAsuransi());
        model.addAttribute("listPenyakit", diagnosisService.getPenyakitList());
        return "view-pasien";
    }

    @RequestMapping(value = "pasien/change/{nikPasien}", method = RequestMethod.GET)
    public String changePasienFormPage(@PathVariable Long nikPasien, Model model) {
        PasienModel targetPasien = pasienService.getPasienByNIKPasien(nikPasien).get();
        model.addAttribute("pasienChange", targetPasien);
        return "form-change-pasien";
    }

    @RequestMapping(value = "pasien/change/{nikPasien}", method = RequestMethod.POST)
    public String changePasienFormSubmit(@PathVariable Long nikPasien, @ModelAttribute PasienModel pasien, Model model) {
        PasienModel newPasienData = pasienService.changePasien(pasien);
        model.addAttribute("pasienChange", newPasienData);
        return "change-pasien";
    }

    @RequestMapping(value = "pasien/{nikPasien}/tambah-diagnosis", method = RequestMethod.GET)
    public String formAddDiagnosis(@PathVariable Long nikPasien, @ModelAttribute DiagnosisPenyakitModel diagnosisPenyakitModel, Model model) {
        PasienModel targetPasien = pasienService.getPasienByNIKPasien(nikPasien).get();
        model.addAttribute("targetPasien", targetPasien);
        model.addAttribute("listPenyakit", diagnosisService.getPenyakitList());
        return "form-add-diagnosis-pasien";
    }

    @RequestMapping(value = "pasien/{nikPasien}/tambah-diagnosis", method = RequestMethod.POST)
    public String addDiagnosisToPatient(@PathVariable Long nikPasien, @RequestParam Long idDiagnosis, @ModelAttribute DiagnosisPenyakitModel diagnosisPenyakitModel, Model model) {
        PasienModel targetTambahPasien = pasienService.getPasienByNIKPasien(nikPasien).get();
        DiagnosisPenyakitModel penyakit = diagnosisService.getPenyakitByIDPenyakit(idDiagnosis).get();

        if (targetTambahPasien.getListDiagnosisPenyakit() == null) {
            List<DiagnosisPenyakitModel> listDiagnosis = new ArrayList<>();
            listDiagnosis.add(penyakit);
            targetTambahPasien.setListDiagnosisPenyakit(listDiagnosis);
        } else {
            targetTambahPasien.getListDiagnosisPenyakit().add(0, penyakit);
        }

        if (penyakit.getListPasien() == null) {
            List<PasienModel> listPasien = new ArrayList<>();
            listPasien.add(targetTambahPasien);
            penyakit.setListPasien(listPasien);
        } else {
            penyakit.getListPasien().add(0, targetTambahPasien);
        }

        pasienService.addPasien(targetTambahPasien);

        DiagnosisPenyakitModel diagnosiss = targetTambahPasien.getListDiagnosisPenyakit().get(0);

        model.addAttribute("namaPenyakit", penyakit.getNamaPenyakit());
        model.addAttribute("pasienTarget", targetTambahPasien);
        return "tambah-diagnosis";
    }

    @RequestMapping(value = "pasien/cari/jenis-kelamin", method = RequestMethod.GET)
    public String searchByGenderForm(Model model) {
        model.addAttribute("listPenyakit", diagnosisService.getPenyakitList());
        return "form-search-gender";
    }

    @RequestMapping(value = "pasien/cari/jenis-kelamin", method = RequestMethod.POST)
    public String searchByGender(@RequestParam Long idDiagnosis, Model model) {
        DiagnosisPenyakitModel diagnosis = diagnosisService.getPenyakitByIDPenyakit(idDiagnosis).get();
        List<PasienModel> listPasien = diagnosis.getListPasien();

        int counterMen = 0;
        int counterWomen = 0;

        for (PasienModel pasien : listPasien) {
            if (pasien.getJenisKelamin() == 1) {
                counterMen++;
            }
            if (pasien.getJenisKelamin() == 2) {
                counterWomen++;
            }
        }
        model.addAttribute("counterMen", counterMen);
        model.addAttribute("counterWomen", counterWomen);
        model.addAttribute("namaPenyakit", diagnosis.getNamaPenyakit());
        return "search-gender";
    }
    @RequestMapping(value = "/pasien/cari", method = RequestMethod.GET)
    public String searchPasienByDiagnosisAndAsuransi(@RequestParam(required = false) Long idAsuransi, @RequestParam(required = false) Long idDiagnosis, Model model) {
        List<PasienModel> pasienList = new ArrayList<>();
        model.addAttribute("listAsuransi", pasienService.getAsuransiList());
        model.addAttribute("listPenyakit", diagnosisService.getPenyakitList());

        if (idAsuransi == null && idDiagnosis == null) {
            return "cari-pasien";
        }
        else if (idDiagnosis != null && idAsuransi == null) {
            DiagnosisPenyakitModel diagnosis = diagnosisService.getPenyakitByIDPenyakit(idDiagnosis).get();
            pasienList = diagnosis.getListPasien();
            model.addAttribute("listPasien", pasienList);
            return "cari-pasien";
        }
        else if (idAsuransi != null && idDiagnosis == null) {
            AsuransiModel asuransi = asuransiService.getAsuransiByIDAsuransi(idAsuransi).get();
            pasienList = asuransi.getListPasien();
            model.addAttribute("listPasien", pasienList);
            return "cari-pasien";
        }
        else {
            DiagnosisPenyakitModel diagnosis = diagnosisService.getPenyakitByIDPenyakit(idDiagnosis).get();
            AsuransiModel asuransi = asuransiService.getAsuransiByIDAsuransi(idAsuransi).get();
            List<PasienModel> pasienListByAsuransi = asuransi.getListPasien();
            List<PasienModel> pasienListByDiagnosis = diagnosis.getListPasien();
            pasienList = pasienListByAsuransi.stream()
                    .distinct()
                    .filter(pasienListByDiagnosis::contains)
                    .collect(Collectors.toList());;
            model.addAttribute("listPasien", pasienList);
            return "cari-pasien";
        }
    }

    @RequestMapping(value = "/pasien/delete/{nikPasien}", method = RequestMethod.GET)
    public String deletePasien(@PathVariable Long nikPasien, Model model) {
        PasienModel hapusPasien = pasienService.getPasienByNIKPasien(nikPasien).get();
        pasienService.deletePasien(hapusPasien);
        model.addAttribute("targetPasien", hapusPasien.getNamaPasien());
        return "hapus-pasien";
    }
}
