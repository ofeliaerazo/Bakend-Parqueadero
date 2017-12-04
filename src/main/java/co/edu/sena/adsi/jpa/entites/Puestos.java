package co.edu.sena.adsi.jpa.entites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "puestos")
public class Puestos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_puestos")
    private Integer idPuestos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inventario_puestos")
    private int inventarioPuestos;
    @JoinColumn(name = "pagos_id_pagos", referencedColumnName = "id_pagos")
    @ManyToOne(optional = false)
    private Pagos pagosIdPagos;

    public Puestos() {
    }

    public Puestos(Integer idPuestos) {
        this.idPuestos = idPuestos;
    }

    public Puestos(Integer idPuestos, int inventarioPuestos) {
        this.idPuestos = idPuestos;
        this.inventarioPuestos = inventarioPuestos;
    }

    public Integer getIdPuestos() {
        return idPuestos;
    }

    public void setIdPuestos(Integer idPuestos) {
        this.idPuestos = idPuestos;
    }

    public int getInventarioPuestos() {
        return inventarioPuestos;
    }

    public void setInventarioPuestos(int inventarioPuestos) {
        this.inventarioPuestos = inventarioPuestos;
    }

    public Pagos getPagosIdPagos() {
        return pagosIdPagos;
    }

    public void setPagosIdPagos(Pagos pagosIdPagos) {
        this.pagosIdPagos = pagosIdPagos;
    }

    @Override
    public String toString() {
        return "co.edu.sena.adsi.jpa.entites.Puestos[ idPuestos=" + idPuestos + " ]";
    }
    
}
