package apap.tugas.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "ASURANSI")
public class AsuransiModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idAsuransi;

    @NotNull
    @Size(max = 20)
    @Column(name = "nama", nullable = false)
    private String namaAsuransi;

    @NotNull
    @Size(max = 20)
    @Column(name = "jenis", nullable = false)
    private String jenisAsuransi;

    @ManyToMany(mappedBy = "listAsuransi")
    List<PasienModel> listPasien;

    public List<PasienModel> getListPasien() {
        return listPasien;
    }

    public void setListPasien(List<PasienModel> listPasien) {
        this.listPasien = listPasien;
    }

    public Long getIdAsuransi() {
        return idAsuransi;
    }

    public void setIdAsuransi(Long idAsuransi) {
        this.idAsuransi = idAsuransi;
    }

    public String getNamaAsuransi() {
        return namaAsuransi;
    }

    public void setNamaAsuransi(String namaAsuransi) {
        this.namaAsuransi = namaAsuransi;
    }

    public String getJenisAsuransi() {
        return jenisAsuransi;
    }

    public void setJenisAsuransi(String jenisAsuransi) {
        this.jenisAsuransi = jenisAsuransi;
    }
}
