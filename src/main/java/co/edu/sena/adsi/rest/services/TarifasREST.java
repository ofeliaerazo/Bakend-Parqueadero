package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entites.Tarifas;
import co.edu.sena.adsi.jpa.sessions.TarifasFacade;
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
@Path("tarifas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarifasREST {
    
    @EJB
    private TarifasFacade tarifasEJB;
    
    @GET
    public List<Tarifas> finAll(){
        return tarifasEJB.findAll();
    }
    
    @GET
    @Path("{id}")
    public Tarifas find(@PathParam("id") Integer id){
        return tarifasEJB.find(id);
    }
    
    @POST
    public void create(Tarifas tarifas){
        tarifasEJB.create(tarifas);
    }
    
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, Tarifas tarifas){
        tarifasEJB.edit(tarifas);
    }
}
