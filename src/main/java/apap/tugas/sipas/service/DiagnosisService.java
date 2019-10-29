package apap.tugas.sipas.service;

import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.model.PasienModel;

import java.util.List;
import java.util.Optional;

public interface DiagnosisService {
    void addPenyakit(DiagnosisPenyakitModel diagnosisPenyakitModel);

    List<DiagnosisPenyakitModel> getPenyakitList();

    List<PasienModel> getPasienFromPenyakitList();

    Optional<DiagnosisPenyakitModel> getPenyakitByIDPenyakit(Long idPenyakit);

    void deleteDiagnosis(DiagnosisPenyakitModel diagnosisPenyakitModel);
}
