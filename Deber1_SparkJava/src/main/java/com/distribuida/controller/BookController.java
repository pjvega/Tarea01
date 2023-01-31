package com.distribuida.controller;

import com.distribuida.dao.AuthorDAO;
import com.distribuida.dao.BookDAO;
import com.distribuida.entity.Author;
import com.distribuida.entity.Book;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.se.SeContainer;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class BookController {

    private static final ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();

    public static void pagesBook(SeContainer container) {

        Instance<BookDAO> instanceBook = container.select(BookDAO.class);
        Instance<AuthorDAO> instanceAuthor = container.select(AuthorDAO.class);
        BookDAO bookDAO = instanceBook.get();
        AuthorDAO authorDAO = instanceAuthor.get();

        get("/books", (req, res) -> {
                    List<Book> books = bookDAO.findAll();
                    Map<String, Object> model = new HashMap<>();
                    model.put("books", books);
                    return engine.render(new ModelAndView(model, "list_books"));
                }
        );

        get("/book/borrar/:id", (req, res) -> {
                    int id = Integer.parseInt(req.params(":id"));
                    bookDAO.deleteBook(id);
                    res.redirect("/books");
                    return null;
                }
        );

        get("/book/form_add", (req, res) -> {
                    Map<String, Object> model = new HashMap<>();
                    List<Author> authors = authorDAO.findAll();
                    Book book = new Book();
                    model.put("book", book);
                    model.put("authors", authors);
                    return engine.render(new ModelAndView(model, "insert_book"));
                }
        );


        post("/book/add", (req, res) -> {
                    Book book = new Book();
                    Long id;
                    double price;
                    String body = req.body();
                    System.out.println(body);
                    String[] cadena = body.split("&");


                    String[] datos = cadena[0].split("=");

                    datos = cadena[0].split("=");
                    book.setIsbn(datos[1]);

                    datos = cadena[1].split("=");
                    book.setTitle(datos[1]);

                    datos = cadena[2].split("=");
                    price = Double.valueOf((datos[1]));
                    book.setPrice(price);

                    datos = cadena[3].split("=");
                    id = Long.parseLong(datos[1]);
                    Author author = new Author();
                    author.setId(id);
                    book.setAuthor(author);

                    bookDAO.createBook(book);
                    res.redirect("/books");
                    return null;
                }
        );

        get("/book/update_form/:id", (req, res) -> {
                    Book book;
                    List<Author> authors = authorDAO.findAll();
                    int id = Integer.parseInt(req.params(":id"));
                    book = bookDAO.findById(id);
                    Map<String, Object> model = new HashMap<>();
                    model.put("book", book);
                    model.put("authors", authors);
                    model.put("id_author_current", book.getAuthor().getId());
                    return engine.render(new ModelAndView(model, "update_book"));
                }
        );


        post("book/modificar", (req, res) -> {
                    Book book = new Book();

                    Long id;
                    double price;
                    String body = req.body();
                    System.out.println(body);
                    String[] cadena = body.split("&");

                    String[] datos = cadena[0].split("=");

                    datos = cadena[0].split("=");
                    book.setId(Integer.parseInt(datos[1]));

                    datos = cadena[1].split("=");
                    book.setIsbn(datos[1]);

                    datos = cadena[2].split("=");
                    book.setTitle(datos[1]);

                    datos = cadena[3].split("=");
                    price = Double.valueOf((datos[1]));
                    book.setPrice(price);

                    datos = cadena[4].split("=");
                    id = Long.parseLong(datos[1]);
                    Author author = new Author();
                    author.setId(id);
                    datos = cadena[5].split("=");
                    Long id_current_author = Long.parseLong(datos[1]);

                    if (id == 0) {
                        author.setId(id_current_author);
                    }

                    book.setAuthor(author);
                    bookDAO.updateBook(book.getId(), book);
                    res.redirect("/books");
                    return null;
                }
        );
    }

}
