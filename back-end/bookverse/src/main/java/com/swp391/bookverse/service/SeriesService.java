package com.swp391.bookverse.service;

import com.swp391.bookverse.entity.Series;
import com.swp391.bookverse.exception.AppException;
import com.swp391.bookverse.exception.ErrorCode;
import com.swp391.bookverse.repository.SeriesRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeriesService {
    SeriesRepository seriesRepository;

    public Series addSeries(Series series) {
        return seriesRepository.save(series);
    }

    public Series updateSeries(Long id, Series series) {
         // Check if series exists before updating
        Series existingSeries = seriesRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SERIES_NOT_FOUND));
        
        // Update fields, keeping existing values if new ones are null
        if (series.getName() != null) {
            existingSeries.setName(series.getName());
        }
        if (series.getDescription() != null) {
            existingSeries.setDescription(series.getDescription());
        }
        if (series.getAuthor() != null) {
            existingSeries.setAuthor(series.getAuthor());
        }
        if (series.getImage() != null) {
            existingSeries.setImage(series.getImage());
        }
        if (series.getActive() != null) {
            existingSeries.setActive(series.getActive());
        }
        
        return seriesRepository.save(existingSeries);
    }

    public void deleteSeries(Long id) {
        // Check if series exists before deleting
        Series existingSeries = seriesRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SERIES_NOT_FOUND));
        
        seriesRepository.delete(existingSeries);
    }

    public Series getSeries(Long id) {
        return seriesRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SERIES_NOT_FOUND));
    }

    public List<Series> getAllSeries() {
        // Check if there are any series in the database
        if (seriesRepository.count() == 0) {
            throw new AppException(ErrorCode.NO_SERIES_STORED);
        }
        return seriesRepository.findAll();
    }

    public List<Series> searchSeries(String keyword) {
        return seriesRepository.findByNameContainingIgnoreCase(keyword);
    }
}
