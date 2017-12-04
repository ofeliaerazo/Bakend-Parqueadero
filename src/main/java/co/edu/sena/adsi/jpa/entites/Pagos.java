package co.edu.sena.adsi.jpa.entites;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "pagos")
public class Pagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pagos")
    private Integer idPagos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_salida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagosIdPagos")
    private List<Puestos> puestosList;
    @JoinColumn(name = "id_autos", referencedColumnName = "id_autos")
    @ManyToOne(optional = false)
    private Autos idAutos;
    @JoinColumn(name = "tarifas_id_", referencedColumnName = "id_")
    @ManyToOne(optional = false)
    private Tarifas tarifasId;

    public Pagos() {
    }

    public Pagos(Integer idPagos) {
        this.idPagos = idPagos;
    }

    public Pagos(Integer idPagos, double valor, Date fechaEntrada, Date fechaSalida) {
        this.idPagos = idPagos;
        this.valor = valor;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public Integer getIdPagos() {
        return idPagos;
    }

    public void setIdPagos(Integer idPagos) {
        this.idPagos = idPagos;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @XmlTransient
    public List<Puestos> getPuestosList() {
        return puestosList;
    }

    public void setPuestosList(List<Puestos> puestosList) {
        this.puestosList = puestosList;
    }

    public Autos getIdAutos() {
        return idAutos;
    }

    public void setIdAutos(Autos idAutos) {
        this.idAutos = idAutos;
    }

    public Tarifas getTarifasId() {
        return tarifasId;
    }

    public void setTarifasId(Tarifas tarifasId) {
        this.tarifasId = tarifasId;
    }

    @Override
    public String toString() {
        return "co.edu.sena.adsi.jpa.entites.Pagos[ idPagos=" + idPagos + " ]";
    }
    
}
