package apap.tugas.sipas.service;

import apap.tugas.sipas.model.AsuransiModel;

import java.util.Optional;

public interface AsuransiService {
    Optional<AsuransiModel> getAsuransiByIDAsuransi(Long idAsuransi);
}
