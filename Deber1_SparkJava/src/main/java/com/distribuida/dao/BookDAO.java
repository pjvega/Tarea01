package com.distribuida.dao;

import com.distribuida.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> findAll();
    Book findById(int id);
    void deleteBook(int id);
    void updateBook(int id, Book book);
    void createBook( Book book);


}
