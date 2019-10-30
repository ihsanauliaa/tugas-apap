package apap.tugas.sipas.service;

import apap.tugas.sipas.model.AsuransiModel;
import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.model.EmergencyContactModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.repository.AsuransiDB;
import apap.tugas.sipas.repository.DiagnosisPenyakitDB;
import apap.tugas.sipas.repository.EmergencyContactDB;
import apap.tugas.sipas.repository.PasienDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class PasienServiceImpl implements PasienService {
    @Autowired
    private PasienDB pasienDB;

    @Autowired
    private EmergencyContactDB emergencyContactDB;

    @Autowired
    private AsuransiDB asuransiDB;

    @Override
    public void addPasien(PasienModel pasienModel) {
        pasienDB.save(pasienModel);
    }

    @Override
    public void addEmergencyContact(EmergencyContactModel emergencyContactModel) {
        emergencyContactDB.save(emergencyContactModel);
    }

    @Override
    public List<PasienModel> getPasienList() {
        return pasienDB.findAll();
    }

    @Override
    public EmergencyContactModel getEmergencyContactModel() {
        return null;
    }

    @Override
    public Optional<PasienModel> getPasiendByIdPasien(Long idPasien) {
        return pasienDB.findByIdPasien(idPasien);
    }

    @Override
    public Optional<PasienModel> getPasienByNIKPasien(Long nikPasien) {
        return pasienDB.findByNikPasien(nikPasien);
    }

    @Override
    public List<AsuransiModel> getAsuransiList() {
        return asuransiDB.findAll();
    }

    @Override
    public List<DiagnosisPenyakitModel> getDiagnosisPenyakitList() {
        return null;
    }

    @Override
    public PasienModel changePasien(PasienModel pasienModel) {
        PasienModel targetPasien = pasienDB.findByNikPasien(pasienModel.getNikPasien()).get();
        EmergencyContactModel targetEC = pasienDB.findByNikPasien(pasienModel.getNikPasien()).get().getEmergencyContactModel();
        try {
            targetPasien.setNamaPasien(pasienModel.getNamaPasien());
            targetPasien.setNikPasien(pasienModel.getNikPasien());
            targetPasien.setJenisKelamin(pasienModel.getJenisKelamin());
            targetPasien.setTanggalLahir(pasienModel.getTanggalLahir());
            targetPasien.setTempatLahir(pasienModel.getTempatLahir());
            generateCode(targetPasien);
            targetEC.setNamaEC(pasienModel.getEmergencyContactModel().getNamaEC());
            targetEC.setNikEC(pasienModel.getEmergencyContactModel().getNikEC());
            targetEC.setHpEC(pasienModel.getEmergencyContactModel().getHpEC());
            pasienDB.save(targetPasien);
            emergencyContactDB.save(targetEC);
            return targetPasien;
        } catch (NullPointerException | ParseException nullException) {
            return null;
        }
    }

    @Override
    public void deletePasien(PasienModel pasienModel) {
        pasienDB.delete(pasienModel);
    }

    @Override
    public PasienModel generateCode(PasienModel pasien) throws ParseException {
        String generatedCode = "";

        int yearPlus = Calendar.getInstance().get(Calendar.YEAR) + 5;
        String year = Integer.toString(yearPlus);
        generatedCode += year;

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(pasien.getTanggalLahir());
        DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        String tanggalLahir = dateFormat.format(date);
        generatedCode += tanggalLahir;

        int jenisKelamin = pasien.getJenisKelamin();
        String jenisKelaminStr = Integer.toString(jenisKelamin);
        generatedCode += jenisKelaminStr;

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random r = new Random();
        StringBuilder randoms = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            randoms.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        generatedCode += randoms;
        for (PasienModel pasienIn : getPasienList()) {
            if (pasienIn.getKodePasien().equals(generatedCode)) {
                generateCode(pasien);
            }
        }
        pasien.setKodePasien(generatedCode);
        return pasien;

    }
}
