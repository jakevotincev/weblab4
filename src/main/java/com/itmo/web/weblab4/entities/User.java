package com.itmo.web.weblab4.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(schema = "s265076", name = "Usr")
public class User {
    @Id
    @NonNull
    private String login;

    @NonNull
    private String password;
}
