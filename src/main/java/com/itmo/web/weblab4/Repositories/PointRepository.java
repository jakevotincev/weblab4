package com.itmo.web.weblab4.Repositories;


import com.itmo.web.weblab4.entities.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point,Long> {
}
