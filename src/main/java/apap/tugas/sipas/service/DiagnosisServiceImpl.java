package apap.tugas.sipas.service;


import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.repository.DiagnosisPenyakitDB;
import apap.tugas.sipas.repository.PasienDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiagnosisServiceImpl implements DiagnosisService{
    @Autowired
    private PasienDB pasienDB;

    @Autowired
    private DiagnosisPenyakitDB diagnosisPenyakitDB;

    @Override
    public void addPenyakit(DiagnosisPenyakitModel diagnosisPenyakitModel) {
        diagnosisPenyakitDB.save(diagnosisPenyakitModel);
    }

    @Override
    public List<DiagnosisPenyakitModel> getPenyakitList() {
        return diagnosisPenyakitDB.findAll();
    }

    @Override
    public List<PasienModel> getPasienFromPenyakitList() {
        return null;
    }

    @Override
    public Optional<DiagnosisPenyakitModel> getPenyakitByIDPenyakit(Long idPenyakit) {
        return diagnosisPenyakitDB.findByIdPenyakit(idPenyakit);
    }

    @Override
    public void deleteDiagnosis(DiagnosisPenyakitModel diagnosisPenyakitModel) {
        diagnosisPenyakitDB.delete(diagnosisPenyakitModel); }

    @Override
    public DiagnosisPenyakitModel changeDiagnosis(DiagnosisPenyakitModel diagnosisPenyakitModel) {
        DiagnosisPenyakitModel targetDiagnosis = diagnosisPenyakitDB.findByIdPenyakit(diagnosisPenyakitModel.getIdPenyakit()).get();
        try {
            targetDiagnosis.setNamaPenyakit(diagnosisPenyakitModel.getNamaPenyakit());
            targetDiagnosis.setKodePenyakit(diagnosisPenyakitModel.getKodePenyakit());
            diagnosisPenyakitDB.save(targetDiagnosis);
            return targetDiagnosis;
        } catch (NullPointerException nullException) {
            return null;
        }
    }
}
