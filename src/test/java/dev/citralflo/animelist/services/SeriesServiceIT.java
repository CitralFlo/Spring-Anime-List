package dev.citralflo.animelist.services;

import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.converters.command2object.SeriesCommandToSeriesConverter;
import dev.citralflo.animelist.converters.object2command.SeriesToSeriesCommandConverter;
import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.repositories.SeriesRepository;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SeriesServiceIT {

    public static final String TITLE = "title";

    @Autowired
    SeriesService seriesService;

    @Autowired
    SeriesRepository seriesRepository;

    @Autowired
    SeriesCommandToSeriesConverter seriesCommandToSeriesConverter;

    @Autowired
    SeriesToSeriesCommandConverter seriesToSeriesCommandConverter;

    @Transactional
    @Test
    void testSaveOfName() throws Exception {

        Set<Series> series = seriesRepository.findAll();
        Series testSeries = series.iterator().next();
        SeriesCommand testSeriesCommand = seriesToSeriesCommandConverter.convert(testSeries);

        //when
        testSeriesCommand.setTitle(TITLE);
        SeriesCommand savedSeriesCommand = seriesService.saveSeriesCommand(testSeriesCommand);

        //then
        assert savedSeriesCommand != null;
        assertEquals(TITLE, savedSeriesCommand.getTitle());
        assertEquals(testSeries.getId(), savedSeriesCommand.getId());
        assertEquals(testSeries.getCharacters().size(), savedSeriesCommand.getCharacters().size());
        assertEquals(testSeries.getGenres().size(), savedSeriesCommand.getGenres().size());
    }
}
