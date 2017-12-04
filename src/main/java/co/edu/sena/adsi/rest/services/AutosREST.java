package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entites.Autos;
import co.edu.sena.adsi.jpa.sessions.AutosFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jonat
 */
@Path("autos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutosREST {
    
    @EJB
    private AutosFacade autosEJB;
    
    @GET
    public List<Autos> finAll(){
        return autosEJB.findAll();
    }
    
    @GET
    @Path("{id}")
    public Autos find(@PathParam("id") Integer id){
        return autosEJB.find(id);
    }
    
    @POST
    public void create(Autos autos){
        autosEJB.create(autos);
    }
    
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, Autos autos){
        autosEJB.edit(autos);
    }
}
