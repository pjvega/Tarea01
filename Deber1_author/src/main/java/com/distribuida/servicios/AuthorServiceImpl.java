package com.distribuida.servicios;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AuthorServiceImpl implements AuthorService{
    @Inject
    private DbConfig dbConfig;
    @Override
    public Author findById(Long id) {
        return dbConfig.findById(id);
    }
    @Override
    public List<Author> findAll() {
        return dbConfig.findAll().stream().toList();
    }
    @Override
    @Transactional
    public void delete(Long id) {
        Author author = dbConfig.findById(id);
        if (author != null) {
            dbConfig.delete(author);
        }
    }

    @Override
    @Transactional
    public void update(Long id, Author author){
        Author authorDb = dbConfig.findById(id);
        if (authorDb != null) {
            authorDb.setFirstName(author.getFirstName());
            authorDb.setLastName(author.getLastName());
            dbConfig.persistAndFlush(authorDb);
}
}
    @Override
    @Transactional
    public void save(Author author) {
        dbConfig.persist(author);
    }
}
