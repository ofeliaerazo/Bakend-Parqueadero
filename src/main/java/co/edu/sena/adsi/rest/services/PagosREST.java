package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entites.Pagos;
import co.edu.sena.adsi.jpa.sessions.AutosFacade;
import co.edu.sena.adsi.jpa.sessions.PagosFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jonat
 */
@Path("pagos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagosREST {
    
    @EJB
    private PagosFacade pagosEJB;
    @EJB
    private AutosFacade autosEJB;
    
    @GET
    public List<Pagos> findAll(){
        return pagosEJB.findAll();
    }
    
    @GET
    @Path("id")
    public Pagos find(@PathParam("id") Integer id){
        return pagosEJB.find(id);
    }
    
    @POST
    public Response create(@QueryParam("placa") String placa,
                           @QueryParam("fecha_entrada") Date fecha_entrada){
        try {
            Pagos newPago = new Pagos();
            
            pagosEJB.create(newPago);
        } 
        catch (Exception e) {
            System.out.println("ERROR" + e);
        }
    }
    
    @PUT
    @Path("id")
    public void edit(@PathParam("id") Integer id, Pagos pagos){
        pagosEJB.edit(pagos);
    }
}
