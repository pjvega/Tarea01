package com.distribuida.config;

import com.distribuida.db.Author;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DbConfig implements PanacheRepository<Author> {
}
