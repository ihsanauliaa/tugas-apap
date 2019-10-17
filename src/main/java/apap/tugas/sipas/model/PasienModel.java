package apap.tugas.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="PASIEN")
public class PasienModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idPasien;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String namaPasien;

    @NotNull
    @Size(max = 255)
    @Column(name = "kode", nullable = false)
    private String kodePasien;

    @NotNull
    @Column(name = "nik", nullable = false)
    private Long nikPasien;

    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private Integer jenisKelamin;

    @NotNull
    @Size(max = 10)
    @Column(name = "tanggal_lahir", nullable = false)
    private String tanggalLahir;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempat_lahir", nullable = false)
    private String tempatLahir;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ec_id", referencedColumnName = "id")
    private EmergencyContactModel emergencyContactModel;

    @ManyToMany
    @JoinTable(
            name = "list_asuransi",
            joinColumns = @JoinColumn(name = "id_pasien"),
            inverseJoinColumns = @JoinColumn(name = "id_asuransi")
    )
    List<AsuransiModel> listAsuransi;

    @ManyToMany
    @JoinTable(
            name = "list_diagnosis",
            joinColumns = @JoinColumn(name = "id_pasien"),
            inverseJoinColumns = @JoinColumn(name = "id_penyakit")
    )
    List<DiagnosisPenyakitModel> listDiagnosisPenyakit;

    public EmergencyContactModel getEmergencyContactModel() {
        return emergencyContactModel;
    }

    public void setEmergencyContactModel(EmergencyContactModel emergencyContactModel) {
        this.emergencyContactModel = emergencyContactModel;
    }

    public Long getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(Long idPasien) {
        this.idPasien = idPasien;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getKodePasien() {
        return kodePasien;
    }

    public void setKodePasien(String kodePasien) {
        this.kodePasien = kodePasien;
    }

    public Long getNikPasien() {
        return nikPasien;
    }

    public void setNikPasien(Long nikPasien) {
        this.nikPasien = nikPasien;
    }

    public Integer getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Integer jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }
}
