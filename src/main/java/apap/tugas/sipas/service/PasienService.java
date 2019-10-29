package apap.tugas.sipas.service;

import apap.tugas.sipas.model.AsuransiModel;
import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.model.EmergencyContactModel;
import apap.tugas.sipas.model.PasienModel;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface PasienService {
    void addPasien(PasienModel pasienModel);

    void addEmergencyContact(EmergencyContactModel emergencyContactModel);

    List<PasienModel> getPasienList();

    EmergencyContactModel getEmergencyContactModel();

    Optional<PasienModel> getPasiendByIdPasien(Long idPasien);

    Optional<PasienModel> getPasienByNIKPasien(Long nikPasien);

    List<AsuransiModel> getAsuransiList();

    List<DiagnosisPenyakitModel> getDiagnosisPenyakitList();

    PasienModel changeRestoran(PasienModel pasienModel);


}
