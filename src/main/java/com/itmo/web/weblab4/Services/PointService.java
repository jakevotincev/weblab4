package com.itmo.web.weblab4.Services;

import com.itmo.web.weblab4.entities.Point;

import java.util.Collection;

public interface PointService {
    Point addPoint(Point point);
    Collection<Point> getAll();
    void deleteAll();


}
