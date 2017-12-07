package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entites.Carros;
import co.edu.sena.adsi.jpa.entites.Parqueadero;
import co.edu.sena.adsi.jpa.entites.Puestos;
import co.edu.sena.adsi.jpa.sessions.CarrosFacade;
import co.edu.sena.adsi.jpa.sessions.ParqueaderoFacade;
import co.edu.sena.adsi.jpa.sessions.PuestosFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("parqueadero")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParqueaderoREST {

    private int idParqueadero = 1;

    @EJB
    private ParqueaderoFacade parqueaderoEJB;

    @EJB
    private PuestosFacade puestoEJB;

    @EJB
    private CarrosFacade carrosFacade;

    @GET
    public Parqueadero getParqueadero() {
        return parqueaderoEJB.find(idParqueadero);
    }
    
    
    @POST
    public Response entrar(
            @QueryParam("placa") String placa,
            @QueryParam("horaLlegada") int horaLlegada) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            Puestos puestos = null;
            Parqueadero parqueadero = parqueaderoEJB.find(idParqueadero);
            if (parqueadero.getHoraActual() == parqueadero.getHoraCierre()) {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El parqueadero se encuentra cerrado.")).build();
            }
            if (carrosFacade.find(placa) != null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El carro ya se encuentra parqueado.")).build();
            }
            for (Puestos puestos1 : parqueadero.getPuestosList()) {
                if (puestos1.getPlacaCarro() == null) {
                    puestos = puestos1;
                    break;
                }
            }
            if (puestos == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El parqueadero se encuentra lleno.")).build();
            } else {
                Carros carro = new Carros();
                carro.setHoraLlegada(horaLlegada);
                carro.setPlacaCarro(placa);
                carrosFacade.create(carro);
                puestos.setPlacaCarro(carro);
                puestoEJB.edit(puestos);
                return Response.status(Response.Status.OK).entity(gson.toJson("Se ha hubicado el carro en el puesto " + puestos.getNumeroPuesto())).build();
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error: " + e.getLocalizedMessage())).build();
        }

    }

    @DELETE
    public Response salir(
            @QueryParam("placa") String placa,
            @QueryParam("horaSalida") int horaSalida) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            Carros carro = carrosFacade.find(placa);
            Parqueadero parqueadero = parqueaderoEJB.find(idParqueadero);
            if (carro == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("No se ha encontrado el carro con placa " + placa)).build();
            } else if (horaSalida <= carro.getHoraLlegada()) {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("la hora de salida no puede ser menor o igual a " + carro.getHoraLlegada())).build();
            } else {
                Puestos puesto = carro.getPuestos();
                puesto.setPlacaCarro(null);
                double pago = parqueadero.getTarifa() * (horaSalida - carro.getHoraLlegada());
                parqueadero.setCaja(parqueadero.getCaja() + pago);
                parqueaderoEJB.edit(parqueadero);
                puestoEJB.edit(puesto);
                carrosFacade.remove(carro);
                return Response.status(Response.Status.OK).entity(gson.toJson("Valor a pagar: " + pago)).build();
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error: " + e)).build();
        }
    }

    @PUT
    @Path("cambiarTarifa")
    public Response cambiarTarifa(
            @QueryParam("tarifa") double tarifa) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            Parqueadero parqueadero = parqueaderoEJB.find(idParqueadero);
            parqueadero.setTarifa(tarifa);
            parqueaderoEJB.edit(parqueadero);
            return Response.status(Response.Status.OK).entity(gson.toJson("la nueva tarifa del parqueadero es " + tarifa)).build();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error: " + e.getLocalizedMessage())).build();
        }
    }

    @PUT
    @Path("avanzarHora")
    public Response avanzarHora() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            Parqueadero parqueadero = parqueaderoEJB.find(idParqueadero);
            if (parqueadero.getHoraActual() + 1 > parqueadero.getHoraCierre()) {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El parqueadero se encuentra cerrado.")).build();
            } else {
                parqueadero.setHoraActual(parqueadero.getHoraActual() + 1);
                parqueaderoEJB.edit(parqueadero);
            }
            return Response.status(Response.Status.OK).entity(gson.toJson("la nueva hora del parqueadero es " + parqueadero.getHoraActual())).build();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error: " + e.getLocalizedMessage())).build();
        }
    }

    @PUT
    @Path("disminuirHora")
    public Response disminuirHora() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            Parqueadero parqueadero = parqueaderoEJB.find(idParqueadero);
            if (parqueadero.getHoraActual() - 1 <= parqueadero.getHoraApertura()) {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El parqueadero se encuentra cerrado.")).build();
            } else {
                parqueadero.setHoraActual(parqueadero.getHoraActual() - 1);
                parqueaderoEJB.edit(parqueadero);
            }
            return Response.status(Response.Status.OK).entity(gson.toJson("la nueva hora del parqueadero es " + parqueadero.getHoraActual())).build();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error: " + e.getLocalizedMessage())).build();
        }
    }

    @GET
    @Path("puestosDisponibles")
    public Response getPuestosDisponibles() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            int numero = 0;
            Parqueadero parqueadero = parqueaderoEJB.find(idParqueadero);
            for (Puestos puesto : parqueadero.getPuestosList()) {
                if (puesto.getPlacaCarro() == null) {
                    numero++;
                }
            }
            return Response.status(Response.Status.OK).entity(gson.toJson("Puestos disponibles: " + numero)).build();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error: " + e.getLocalizedMessage())).build();
        }
    }

    @GET
    @Path("ganancias")
    public Response getGanancias() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            Parqueadero parqueadero = parqueaderoEJB.find(idParqueadero);
            return Response.status(Response.Status.OK).entity(gson.toJson("Dinero ganado: " + parqueadero.getCaja())).build();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error: " + e.getLocalizedMessage())).build();
        }
    }
    
    

}
