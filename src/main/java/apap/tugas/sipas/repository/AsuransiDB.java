package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.AsuransiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AsuransiDB extends JpaRepository<AsuransiModel, Long> {
    List<AsuransiModel> findByIdAsuransi(Long idAsuransi);
}
