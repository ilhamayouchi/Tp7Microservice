package ma.wx.jaxrs.controllers;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.wx.jaxrs.entities.Compte;
import ma.wx.jaxrs.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Path("/banque")
public class CompteRestJaxRSAPI {

    @Autowired
    private CompteRepository compteRepository;

    @Path("/comptes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Compte> getComptes() {
        return compteRepository.findAll();
    }

    @Path("/comptes/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompte(@PathParam("id") Long id) {
        return compteRepository.findById(id)
                .map(c -> Response.ok(c).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/comptes")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Compte addCompte(Compte compte) {
        compte.setId(null);
        return compteRepository.save(compte);
    }

    @Path("/comptes/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCompte(@PathParam("id") Long id, Compte compte) {
        return compteRepository.findById(id)
                .map(existing -> {
                    existing.setSolde(compte.getSolde());
                    existing.setDateCreation(compte.getDateCreation());
                    existing.setType(compte.getType());
                    return Response.ok(compteRepository.save(existing)).build();
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/comptes/{id}")
    @DELETE
    public void deleteCompte(@PathParam("id") Long id) {
        compteRepository.deleteById(id);
    }
}