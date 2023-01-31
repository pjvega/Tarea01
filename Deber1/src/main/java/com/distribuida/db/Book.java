package com.distribuida.db;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int id;
    private String isbn;
    private String title;
    private double price;
    private Author author;
}