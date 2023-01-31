package com.distribuida;

import com.distribuida.controller.AuthorController;
import com.distribuida.controller.BookController;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;


public class Servidor {

    public static void main(String[] args) {
        SeContainer container;
        container = SeContainerInitializer.newInstance().initialize();
        AuthorController.pagesAuthor(container);
        BookController.pagesBook(container);
    }

}
