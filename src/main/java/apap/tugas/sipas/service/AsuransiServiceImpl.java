package apap.tugas.sipas.service;

import apap.tugas.sipas.model.AsuransiModel;
import apap.tugas.sipas.repository.AsuransiDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AsuransiServiceImpl implements AsuransiService{
    @Autowired
    private AsuransiDB asuransiDB;

    @Override
    public Optional<AsuransiModel> getAsuransiByIDAsuransi(Long idAsuransi) {
        return asuransiDB.findByIdAsuransi(idAsuransi);
    }
}
