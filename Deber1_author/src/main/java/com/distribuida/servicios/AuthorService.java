package com.distribuida.servicios;
import com.distribuida.db.Author;
import java.util.List;


public interface AuthorService {

    Author findById(Long id);
    List<Author> findAll();
    void delete(Long id);
    void update(Long id, Author author) throws Exception;
    //void update( Long id,Author author) throws Exception;
    void save(Author author);
}


