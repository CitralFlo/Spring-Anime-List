package dev.citralflo.animelist.repositories;

import dev.citralflo.animelist.model.Series;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;

public interface SeriesRepository extends CrudRepository<Series, Long> {

    Set<Series> findAll();

}
