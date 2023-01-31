package com.distribuida.rest;
import com.distribuida.db.Author;
import com.distribuida.servicios.AuthorService;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorRest {
    @Inject
    private AuthorService authorService;
    @GET
    public List<Author> findAll(){
        return authorService.findAll();
    }

    @GET
    @Path("/{id}")
    public Author findById(@PathParam("id") Long id){
        return authorService.findById(id);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Author author){
        try{
            authorService.save(author);
        }catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al guardar en la base de datos"+ ex.getMessage()).build();
        }
        return Response.status(Response.Status.CREATED).build();
}

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")

    public Response update( @PathParam("id") long id,Author author) throws Exception {
        try {
            authorService.update(id, author);
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se actualizo correctament" + e.getMessage()).build();
        } return Response.status((Response.Status.OK)).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        authorService.delete(id);
    }
}
