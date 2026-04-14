package com.coditas.NewJWTAssignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter

@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String token;
    @OneToOne(cascade = CascadeType.MERGE)
    private Users user;

    public RefreshToken(Long id, String username, String token) {
        this.id = id;
        this.username = username;
        this.token = token;
    }
}
