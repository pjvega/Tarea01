package com.distribuida.db;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    public static final long serialVersionUID = 1L;
    @Id
     private Long id;
     private String isbn;
     private String title;
     private Double price;
}
