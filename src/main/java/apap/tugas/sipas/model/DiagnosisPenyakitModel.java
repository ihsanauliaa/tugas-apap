package apap.tugas.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "DIAGNOSIS_PENYAKIT")
public class DiagnosisPenyakitModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idPenyakit;

    @NotNull
    @Size(max = 5)
    @Column(name = "kode", nullable = false)
    private String kodePenyakit;

    @NotNull
    @Size(max = 20)
    @Column(name = "nama", nullable = false)
    private String namaPenyakit;

    @ManyToMany(mappedBy = "listDiagnosisPenyakit")
    List<PasienModel> listPasien;

    public Long getIdPenyakit() {
        return idPenyakit;
    }

    public void setIdPenyakit(Long idPenyakit) {
        this.idPenyakit = idPenyakit;
    }

    public String getKodePenyakit() {
        return kodePenyakit;
    }

    public void setKodePenyakit(String kodePenyakit) {
        this.kodePenyakit = kodePenyakit;
    }

    public String getNamaPenyakit() {
        return namaPenyakit;
    }

    public void setNamaPenyakit(String namaPenyakit) {
        this.namaPenyakit = namaPenyakit;
    }
}
