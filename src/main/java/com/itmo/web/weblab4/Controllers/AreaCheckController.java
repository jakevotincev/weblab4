package com.itmo.web.weblab4.Controllers;

import com.itmo.web.weblab4.Services.impl.PointServiceImpl;
import com.itmo.web.weblab4.entities.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("point")
public class AreaCheckController {
    @Autowired
    private PointServiceImpl pointService;

    @CrossOrigin
    @PostMapping()
    public Point create(@RequestBody Point point) {
            double x = point.getX();
            double y = point.getY();
            double r = point.getR();
            

            if (x >= 0 && y >= 0 && Math.sqrt(x * x + y * y) <= r) point.setHit(true);
            else if (x <= 0 && y >= 0 && y <= x + r / 2) point.setHit(true);
            else if (x <= 0 && y <= 0 && x >= -r / 2 && y >= -r) point.setHit(true);
            else point.setHit(false);


        return pointService.addPoint(point);
    }

    @CrossOrigin
    @GetMapping
    public List<Point> getAllPoints() {
        return pointService.getAll();
    }

    @CrossOrigin
    @DeleteMapping
    public void delete() {
        pointService.deleteAll();
    }


}
