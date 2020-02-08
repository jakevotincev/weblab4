package com.itmo.web.weblab4.Services.impl;

import com.itmo.web.weblab4.Repositories.PointRepository;
import com.itmo.web.weblab4.Services.PointService;
import com.itmo.web.weblab4.entities.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;

    @Autowired
    public PointServiceImpl(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Override
    public Point addPoint(Point point) {
        return pointRepository.saveAndFlush(point);
    }

    @Override
    public List<Point> getAll() {
        return pointRepository.findAll();
    }

    @Override
    public void deleteAll() {
        pointRepository.deleteAll();
    }
}