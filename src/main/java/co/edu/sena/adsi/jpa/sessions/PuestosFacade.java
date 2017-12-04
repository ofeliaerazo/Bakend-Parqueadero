package co.edu.sena.adsi.jpa.sessions;

import co.edu.sena.adsi.jpa.entites.Puestos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jonat
 */
@Stateless
public class PuestosFacade extends AbstractFacade<Puestos> {

    @PersistenceContext(unitName = "co.edu.sena.adsi.jpa_Parqueadero_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PuestosFacade() {
        super(Puestos.class);
    }
    
}
