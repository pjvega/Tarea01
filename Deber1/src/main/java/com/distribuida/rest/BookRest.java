package com.distribuida.rest;

import com.distribuida.db.Book;
import com.distribuida.servicios.BookService;
import io.helidon.dbclient.DbClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
@Path("/book")

public class BookRest {
        @Inject
        private BookService bookService;

        @GET
        @Path("/{id}")
        @Produces("application/json")
        public Book findOneById(@PathParam("id") int id) throws ExecutionException, InterruptedException {
                return this.bookService.encontrarLibro(id);
        }

        @GET
        @Produces("application/json")
        public List<?> findAll() throws ExecutionException, InterruptedException {
                return this.bookService.encontrarTodos();
        }

        @PUT
        @Path("/{id}")
        @Consumes("application/json")
        @Produces("application/json")
        public Map<String, Long> update(Book book) {
                return Map.of("rowsChanged", this.bookService.actualizar(book));
        }

        @POST
        @Consumes("application/json")
        @Produces("application/json")
        public Map<String, Long> save(Book book) {
                return Map.of("rowsChanged", this.bookService.guardar(book));
        }

        @DELETE
        @Path("/{id}")
        @Produces("application/json")
        public Map<String, Long> delete(@PathParam("id") int id) {
                return Map.of("rowsChanged", this.bookService.eliminar(id));
}

}