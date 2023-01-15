package com.aliak.webapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="users")
@Data //Lombok'dan gelir ototmaik olarak getter setter yapar
public class User {
    @Id
    Long id;

    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    String userName;
    String password;
}
