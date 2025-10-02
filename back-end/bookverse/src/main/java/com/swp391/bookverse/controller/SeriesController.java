package com.swp391.bookverse.controller;

import com.swp391.bookverse.entity.Series;
import com.swp391.bookverse.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @GetMapping
    public List<Series> getAllSeries() {
        return seriesService.getAllSeries();
    }

    @GetMapping("/{id}")
    public Series getSeriesById(@PathVariable Long id) {
        return seriesService.getSeriesById(id);
    }

    @PostMapping
    public Series createSeries(@RequestBody Series series) {
        return seriesService.createSeries(series);
    }

    @PutMapping("/{id}")
    public Series updateSeries(@PathVariable Long id, @RequestBody Series series) {
        return seriesService.updateSeries(id, series);
    }

    @DeleteMapping("/{id}")
    public void deleteSeries(@PathVariable Long id) {
        seriesService.deleteSeries(id);
    }
}
