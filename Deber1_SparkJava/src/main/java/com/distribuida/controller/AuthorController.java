package com.distribuida.controller;

import com.distribuida.dao.AuthorDAO;
import com.distribuida.entity.Author;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.se.SeContainer;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class AuthorController {

    private static final ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();

    public static void pagesAuthor(SeContainer container) {

        Instance<AuthorDAO> authorInstance = container.select(AuthorDAO.class);
        AuthorDAO servicioAuthor = authorInstance.get();

        get("/authors", (req, res) -> {
                    List<Author> authors = servicioAuthor.findAll();
                    Map<String, Object> model = new HashMap<>();
                    model.put("authors", authors);
                    return engine.render(new ModelAndView(model, "list_authors"));
                }
        );

        get("/author/delete/:id", (req, res) -> {
                    Long id = Long.parseLong(req.params(":id"));
                    servicioAuthor.deleteAuthor(id);
                    res.redirect("/authors");
                    return null;
                }
        );

        get("/author/form_add", (req, res) -> {
                    Map<String, Object> model = new HashMap<>();
                    Author author = new Author();
                    model.put("author", author);
                    return engine.render(new ModelAndView(model, "add_author"));
                }
        );

        post("/author/add", (req, res) -> {
                    Author author = new Author();

                    String body = req.body();
                    System.out.println(body);
                    String[] cadena = body.split("&");

                    String[] datos = cadena[0].split("=");
                    author.setFirstName(datos[1]);

                    datos = cadena[1].split("=");
                    author.setLastName(datos[1]);
                    author.setId(null);
                    servicioAuthor.createAuthor(author);

                    res.redirect("/authors");
                    return null;
                }
        );

        get("/author/update_form/:id", (req, res) -> {
                    Author author = new Author();
                    Long id = Long.parseLong(req.params("id"));
                    author = servicioAuthor.findById(id);
                    Map<String, Object> model = new HashMap<>();
                    model.put("author", author);
                    return engine.render(new ModelAndView(model, "update_author"));
                }
        );


        post("/author/modificar", (req, res) -> {
                    Author author = new Author();

                    String body = req.body();
                    System.out.println(body);
                    String[] cadena = body.split("&");


                    String[] datos = cadena[0].split("=");
                    author.setId(Long.parseLong(datos[1]));

                    datos = cadena[1].split("=");
                    author.setFirstName(datos[1]);

                    datos = cadena[2].split("=");
                    author.setLastName(datos[1]);

                    servicioAuthor.updateAuthor(author.getId(), author);
                    res.redirect("/authors");
                    return null;
                }
        );
    }

}
