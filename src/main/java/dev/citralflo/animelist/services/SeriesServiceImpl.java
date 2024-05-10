package dev.citralflo.animelist.services;

import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.repositories.SeriesRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

// Postponed to later date
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;

    public SeriesServiceImpl(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    @Override
    public Set<Series> getSeries() {

        return new HashSet<>(seriesRepository.findAll());

    }
}
