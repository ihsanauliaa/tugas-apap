package apap.tugas.sipas.model.generator;

import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.repository.PasienDB;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Optional;

public class PasienIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return null;
    }

    @Override
    public boolean supportsJdbcBatchInserts() {
        return false;
    }
}
