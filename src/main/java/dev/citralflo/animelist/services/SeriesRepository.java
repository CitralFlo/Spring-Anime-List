package dev.citralflo.animelist.services;

import dev.citralflo.animelist.model.Series;
import java.util.Set;

public interface SeriesRepository {

    Set<Series> getSeries();
}
