package com.swp391.bookverse.controller;
import com.swp391.bookverse.dto.APIResponse;
import com.swp391.bookverse.entity.Series;
import com.swp391.bookverse.service.SeriesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeriesController {
    SeriesService seriesService;

    @PostMapping
    public APIResponse<Series> addSeries(@RequestBody Series series) {
        APIResponse<Series> response = new APIResponse<>();
        response.setResult(seriesService.addSeries(series));
        return response;
    }

    @PutMapping("/{id}")
    public APIResponse<Series> updateSeries(@PathVariable Long id, @RequestBody Series series) {
        APIResponse<Series> response = new APIResponse<>();
        response.setResult(seriesService.updateSeries(id, series));
        return response;
    }

    @DeleteMapping("/{id}")
    public APIResponse<String> deleteSeries(@PathVariable Long id) {
        seriesService.deleteSeries(id);
        APIResponse<String> response = new APIResponse<>();
        response.setResult("Series with ID " + id + " deleted successfully.");
        return response;
    }

    @GetMapping("/{id}")
    public APIResponse<Series> getSeries(@PathVariable Long id) {
        APIResponse<Series> response = new APIResponse<>();
        response.setResult(seriesService.getSeries(id));
        return response;
    }

    @GetMapping
    public APIResponse<List<Series>> getAllSeries() {
        APIResponse<List<Series>> response = new APIResponse<>();
        response.setResult(seriesService.getAllSeries());
        return response;
    }

    @GetMapping("/search/{text}")
    public APIResponse<List<Series>> searchSeries(@PathVariable String text) {
        APIResponse<List<Series>> response = new APIResponse<>();
        response.setResult(seriesService.searchSeries(text));
        return response;
    }
}
