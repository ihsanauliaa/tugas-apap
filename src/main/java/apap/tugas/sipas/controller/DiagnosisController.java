package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.service.DiagnosisService;
import apap.tugas.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DiagnosisController {

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Qualifier("diagnosisServiceImpl")
    @Autowired
    private DiagnosisService diagnosisService;

    @RequestMapping(value = "diagnosis-penyakit-all", method = RequestMethod.GET)
    public String viewAllPenyakit(Model model) {
        model.addAttribute("listPenyakitAll", diagnosisService.getPenyakitList());
        model.addAttribute("active", "active");
        return "view-penyakit-all";
    }

    @RequestMapping("/diagnosis-penyakit")
    public String viewPenyakit(
            @RequestParam(value = "idDiagnosis") Long idDiagnosis, Model model
    ) {
        model.addAttribute("targetPenyakit", diagnosisService.getPenyakitByIDPenyakit(idDiagnosis).get());
        //model.addAttribute("penyakitPenderitaList", diagnosisService.getPasienFromPenyakitList(nikPasien).get().getListAsuransi());
        model.addAttribute("activeDiagnosis", "active");
        return "view-penyakit";
    }
    @RequestMapping(value = "/diagnosis-penyakit/add", method = RequestMethod.GET)
    public String addDiagnosisFormPage(Model model) {
        DiagnosisPenyakitModel diagnosisPenyakitModel = new DiagnosisPenyakitModel();
        model.addAttribute("diagnosisNew", diagnosisPenyakitModel);
        return "form-add-diagnosis";
    }

    @RequestMapping(value = "/diagnosis-penyakit/add", method = RequestMethod.POST)
    public String addDiagnosisSubmit(@ModelAttribute DiagnosisPenyakitModel diagnosisPenyakitModel, Model model) {
        diagnosisService.addPenyakit(diagnosisPenyakitModel);
        model.addAttribute("diagnosisNew", diagnosisPenyakitModel);
        return "add-diagnosis";
    }

    @RequestMapping(value="/diagnosis-penyakit/delete/{idDiagnosis}", method = RequestMethod.GET)
    public String deleteDiagnosis(@PathVariable Long idDiagnosis, Model model) {
        DiagnosisPenyakitModel hapusDiagnosis = diagnosisService.getPenyakitByIDPenyakit(idDiagnosis).get();
        diagnosisService.deleteDiagnosis(hapusDiagnosis);
        model.addAttribute("targetDiagnosis", hapusDiagnosis.getNamaPenyakit());
        return "delete-diagnosis";
    }
}
