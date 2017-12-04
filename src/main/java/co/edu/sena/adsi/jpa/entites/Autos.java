package co.edu.sena.adsi.jpa.entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "autos")
public class Autos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_autos")
    private Integer idAutos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "placa")
    private String placa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAutos")
    private List<Pagos> pagosList;

    public Autos() {
    }

    public Autos(Integer idAutos) {
        this.idAutos = idAutos;
    }

    public Autos(Integer idAutos, String placa) {
        this.idAutos = idAutos;
        this.placa = placa;
    }

    public Integer getIdAutos() {
        return idAutos;
    }

    public void setIdAutos(Integer idAutos) {
        this.idAutos = idAutos;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @XmlTransient
    public List<Pagos> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pagos> pagosList) {
        this.pagosList = pagosList;
    }

    @Override
    public String toString() {
        return "co.edu.sena.adsi.jpa.entites.Autos[ idAutos=" + idAutos + " ]";
    }
    
}
