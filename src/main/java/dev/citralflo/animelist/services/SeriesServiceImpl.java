package dev.citralflo.animelist.services;

import dev.citralflo.animelist.model.Series;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SeriesServiceImpl implements SeriesRepository {

    private final dev.citralflo.animelist.repositories.SeriesRepository seriesRepository;

    public SeriesServiceImpl(dev.citralflo.animelist.repositories.SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    @Override
    public Set<Series> getSeries() {
        log.debug("I'm in the service");

        return new HashSet<>(seriesRepository.findAll());

    }
}
