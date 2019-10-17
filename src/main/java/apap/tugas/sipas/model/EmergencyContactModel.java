package apap.tugas.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "EMERGENCY_CONTACT")
public class EmergencyContactModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idEC;

    public PasienModel getPasienModel() {
        return pasienModel;
    }

    public void setPasienModel(PasienModel pasienModel) {
        this.pasienModel = pasienModel;
    }

    @NotNull
    @Size(max = 20)
    @Column(name = "nik", nullable = false)
    private String nikEC;

    @NotNull
    @Size(max = 35)
    @Column(name = "nama", nullable = false)
    private String namaEC;

    @NotNull
    @Column(name = "no_hp", nullable = false)
    private String hpEC;

    @OneToOne(mappedBy = "emergencyContactModel", fetch = FetchType.EAGER)
    private PasienModel pasienModel;

    public Long getIdEC() {
        return idEC;
    }

    public void setIdEC(Long idEC) {
        this.idEC = idEC;
    }

    public String getNikEC() {
        return nikEC;
    }

    public void setNikEC(String nikEC) {
        this.nikEC = nikEC;
    }

    public String getNamaEC() {
        return namaEC;
    }

    public void setNamaEC(String namaEC) {
        this.namaEC = namaEC;
    }

    public String getHpEC() {
        return hpEC;
    }

    public void setHpEC(String hpEC) {
        this.hpEC = hpEC;
    }
}
