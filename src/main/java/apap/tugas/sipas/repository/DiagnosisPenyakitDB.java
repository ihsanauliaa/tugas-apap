package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiagnosisPenyakitDB extends JpaRepository<DiagnosisPenyakitModel, Long> {
    Optional<DiagnosisPenyakitModel> findByIdPenyakit(Long idPenyakit);
}
