package com.distribuida.dao;

import com.distribuida.entity.Author;

import java.util.List;

public interface AuthorDAO {

    List<Author> findAll();
    Author findById(Long id);
    void deleteAuthor(Long id);
    void updateAuthor(Long id, Author author);
    void createAuthor( Author author);


}
