package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.AsuransiModel;
import apap.tugas.sipas.model.EmergencyContactModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.repository.AsuransiDB;
import apap.tugas.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PasienController {

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

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
    public String addPasienSubmit(@ModelAttribute PasienModel pasien, @ModelAttribute EmergencyContactModel emergencyContact, @ModelAttribute AsuransiModel asuransiModel, Model model) {
        pasien.setEmergencyContactModel(emergencyContact);
        pasienService.addPasien(pasien);
        pasienService.addEmergencyContact(emergencyContact);

        List<AsuransiModel> listAsuransi = new ArrayList<>();
        listAsuransi.add(asuransiModel);
        pasien.setListAsuransi(listAsuransi);

        List<PasienModel> listPasien = new ArrayList<>();
        listPasien.add(pasien);
        asuransiModel.setListPasien(listPasien);
        //PasienModel pasienModel = pasienService.getPasiendByIdPasien(pasien.getIdPasien()).get();
        //pasienModel.setEmergencyContactModel(emergencyContact);
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
}
