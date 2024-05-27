package dev.citralflo.animelist.services;

import dev.citralflo.animelist.model.Series;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SeriesServiceImpl implements SeriesService {

    private final dev.citralflo.animelist.repositories.SeriesRepository seriesRepository;

    public SeriesServiceImpl(dev.citralflo.animelist.repositories.SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    @Override
    public Set<Series> getSeries() {
        log.debug("I'm in the service");

        return new HashSet<>(seriesRepository.findAll());

    }

    @Override
    public Series getSeriesById(Long id) {

        Optional<Series> seriesOptional = seriesRepository.findById(id);

        if (seriesOptional.isPresent()) {
            return seriesOptional.get();
        } else {
            throw new RuntimeException("Series not found");
        }
    }
}
