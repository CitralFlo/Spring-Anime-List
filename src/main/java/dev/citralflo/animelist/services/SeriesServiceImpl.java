package dev.citralflo.animelist.services;

import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.converters.command2object.SeriesCommandToSeriesConverter;
import dev.citralflo.animelist.converters.object2command.SeriesToSeriesCommandConverter;
import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.repositories.SeriesRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;
    private final SeriesToSeriesCommandConverter seriesToSeriesCommandConverter;
    private final SeriesCommandToSeriesConverter seriesCommandToSeriesConverter;

    public SeriesServiceImpl(SeriesRepository seriesRepository, SeriesToSeriesCommandConverter seriesToSeriesCommandConverter, SeriesCommandToSeriesConverter seriesCommandToSeriesConverter) {
        this.seriesRepository = seriesRepository;
        this.seriesToSeriesCommandConverter = seriesToSeriesCommandConverter;
        this.seriesCommandToSeriesConverter = seriesCommandToSeriesConverter;
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
        }
        else {
            throw new RuntimeException("Series not found");
        }
    }

    @Override
    @Transactional
    public SeriesCommand saveSeriesCommand(SeriesCommand command) {
        Series detachedSeries = seriesCommandToSeriesConverter.convert(command);

        assert detachedSeries != null;
        Series savedSeries = seriesRepository.save(detachedSeries);
        log.debug("Saved SeriesId:" + savedSeries.getId());
        return seriesToSeriesCommandConverter.convert(savedSeries);
    }

    @Override
    @Transactional
    public SeriesCommand getSeriesCommandById(Long id) {
        return seriesToSeriesCommandConverter.convert(getSeriesById(id));
    }

    @Override
    @Transactional
    public boolean deleteSeriesById(Long id) {
        Optional<Series> optionalSeries = seriesRepository.findById(id);

        if (optionalSeries.isEmpty()) {
            return true;
        }

        Series series = optionalSeries.get();

        series.getGenres().clear();
        series.getCharacters().forEach(character -> character.setVoiceActor(null));

        seriesRepository.delete(series);
        return true;
    }
}
