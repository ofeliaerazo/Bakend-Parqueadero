package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entites.Puestos;
import co.edu.sena.adsi.jpa.sessions.PuestosFacade;
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
@Path("puestos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PuestosREST {
    
    @EJB
    private PuestosFacade puestosEJB;
    
    @GET
    public List<Puestos> finAll(){
        return puestosEJB.findAll();
    }
    
    @GET
    @Path("{id}")
    public Puestos find(@PathParam("id") Integer id){
        return puestosEJB.find(id);
    }
    
    @POST
    public void create(Puestos puestos){
        puestosEJB.create(puestos);
    }
    
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, Puestos puestos){
        puestosEJB.edit(puestos);
    }
}
