package com.distribuida.db;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.*;


@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Author implements Serializable {
 public static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "author_id", nullable = false)
    private List<Book> books;
}


