package apap.tugas.sipas.service;

import apap.tugas.sipas.model.AsuransiModel;
import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.model.EmergencyContactModel;
import apap.tugas.sipas.model.PasienModel;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PasienInMemoryService implements PasienService{
    private List<PasienModel> listPasien;
    private EmergencyContactModel emergencyContactModel;

    public PasienInMemoryService() {
        listPasien = new ArrayList<>();
    }
    @Override
    public void addPasien(PasienModel pasienModel) {
        listPasien.add(pasienModel);
    }

    @Override
    public void addEmergencyContact(EmergencyContactModel emergencyContactModel) {
        this.emergencyContactModel = emergencyContactModel;
    }

    @Override
    public List<PasienModel> getPasienList() {
        return listPasien;
    }

    @Override
    public EmergencyContactModel getEmergencyContactModel() {
        return emergencyContactModel;
    }

    @Override
    public Optional<PasienModel> getPasiendByIdPasien(Long idPasien) {
        for (PasienModel pasien : listPasien) {
            if (idPasien.equals(pasien.getIdPasien())) {
                return Optional.of(pasien);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<PasienModel> getPasienByNIKPasien(Long nikPasien) {
        for (PasienModel pasien : listPasien) {
            if (nikPasien.equals(pasien.getNikPasien())) {
                return Optional.of(pasien);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<AsuransiModel> getAsuransiList() {
        return null;
    }

    @Override
    public List<DiagnosisPenyakitModel> getDiagnosisPenyakitList() {
        return null;
    }

    @Override
    public PasienModel changePasien(PasienModel pasienModel) {
        return null;
    }

    @Override
    public void deletePasien(PasienModel pasienModel) {

    }

    @Override
    public PasienModel generateCode(PasienModel pasien) throws ParseException {
        return null;
    }
}
