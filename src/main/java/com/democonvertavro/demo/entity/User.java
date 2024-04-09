package com.democonvertavro.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "users")
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String city;
    private Integer code;
}
