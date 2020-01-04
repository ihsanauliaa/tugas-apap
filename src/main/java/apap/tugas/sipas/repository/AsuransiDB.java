package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.AsuransiModel;
import apap.tugas.sipas.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface AsuransiDB extends JpaRepository<AsuransiModel, Long> {
    Optional<AsuransiModel> findByIdAsuransi(Long idAsuransi);
}
