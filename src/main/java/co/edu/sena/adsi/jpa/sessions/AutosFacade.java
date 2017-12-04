package co.edu.sena.adsi.jpa.sessions;

import co.edu.sena.adsi.jpa.entites.Autos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jonat
 */
@Stateless
public class AutosFacade extends AbstractFacade<Autos> {

    @PersistenceContext(unitName = "co.edu.sena.adsi.jpa_Parqueadero_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutosFacade() {
        super(Autos.class);
    }
    
}
