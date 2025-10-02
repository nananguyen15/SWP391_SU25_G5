package com.swp391.bookverse.service;

import com.swp391.bookverse.entity.Series;
import java.util.List;

public interface SeriesService {
    List<Series> getAllSeries();
    Series getSeriesById(Long id);
    Series createSeries(Series series);
    Series updateSeries(Long id, Series series);
    void deleteSeries(Long id);
}