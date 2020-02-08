package com.itmo.web.weblab4.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Table(schema = "s265076", name="points")
public class Point {
    @Id
    @GeneratedValue
    private long id;
    @NonNull
    private double x;
    @NonNull
    private double y;
    @NonNull
    private double r;
    @NonNull
    private boolean hit;
}