package com.swp391.bookverse.service.impl;

import com.swp391.bookverse.entity.Series;
import com.swp391.bookverse.repository.SeriesRepository;
import com.swp391.bookverse.service.SeriesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;

    public SeriesServiceImpl(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    @Override
    public List<Series> getAllSeries() {
        return seriesRepository.findAll();
    }

    @Override
    public Series getSeriesById(Long id) {
        return seriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Series not found with id: " + id));
    }

    @Override
    public Series createSeries(Series series) {
        return seriesRepository.save(series);
    }

    @Override
    public Series updateSeries(Long id, Series series) {
        Series existingSeries = getSeriesById(id);
        existingSeries.setName(series.getName());
        existingSeries.setDescription(series.getDescription());
        return seriesRepository.save(existingSeries);
    }

    @Override
    public void deleteSeries(Long id) {
        Series existingSeries = getSeriesById(id);
        seriesRepository.delete(existingSeries);
    }
}