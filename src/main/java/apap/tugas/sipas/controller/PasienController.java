package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.EmergencyContactModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PasienController {

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/pasien/add", method = RequestMethod.GET)
    public String addPasienFormPage(Model model) {
        PasienModel newPasien = new PasienModel();
        EmergencyContactModel newEmergencyContactModel = new EmergencyContactModel();
        model.addAttribute("emergencyContact", newEmergencyContactModel);
        model.addAttribute("pasien", newPasien);
        return "form-add-pasien";
    }

    @RequestMapping(path = "/pasien/add", method = RequestMethod.POST)
    public String addPasienSubmit(@ModelAttribute PasienModel pasien, @ModelAttribute EmergencyContactModel emergencyContact, Model model) {
        pasien.setEmergencyContactModel(emergencyContact);
        pasienService.addPasien(pasien);
        pasienService.addEmergencyContact(emergencyContact);
        PasienModel pasienModel = pasienService.getPasiendByIdPasien(pasien.getIdPasien()).get();
        pasienModel.setEmergencyContactModel(emergencyContact);
        // pasienService.addEmergencyContact(emergencyContact);
        model.addAttribute("namaPasien", pasien.getNamaPasien());
        model.addAttribute("namaEmergencyContact", emergencyContact.getNamaEC());
        return "add-pasien";
    }
}
