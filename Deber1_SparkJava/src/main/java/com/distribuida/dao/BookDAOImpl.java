package com.distribuida.dao;


import com.distribuida.entity.Book;
import jakarta.enterprise.context.ApplicationScoped;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.*;

@ApplicationScoped
public class BookDAOImpl implements BookDAO {

    private final String PATH_URL = "http://localhost:4040/api/book";

    RestTemplate restTemplate;

    public BookDAOImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    @Override
    public List<Book> findAll() {

        var response = restTemplate
                .exchange(PATH_URL, GET, null, new ParameterizedTypeReference<List<Book>>() {
                });

        return response.getBody();
    }


    @Override
    public Book findById(int id) {
        ResponseEntity<Book> res = restTemplate.getForEntity(PATH_URL + "/" + id, Book.class);
        return res.getBody();
    }

    @Override
    public void deleteBook(int id) {
        restTemplate.delete(PATH_URL + "/" + id);
    }

    @Override
    public void updateBook(int id, Book book) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity entity = new HttpEntity(book, headers);
        restTemplate.exchange(PATH_URL + "/" + id, PUT, entity, Book.class);
    }

    @Override
    public void createBook(Book book) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity entity = new HttpEntity(book, headers);
        restTemplate.exchange(PATH_URL, POST, entity, Book.class);
    }

}
