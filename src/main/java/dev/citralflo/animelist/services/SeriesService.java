package dev.citralflo.animelist.services;

import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.model.Series;
import java.util.Set;

public interface SeriesService {

    Set<Series> getSeries();

    Series getSeriesById(Long id);

    SeriesCommand saveSeriesCommand(SeriesCommand command);

    SeriesCommand getSeriesCommandById(Long id);

    boolean deleteSeriesById(Long id);
}
