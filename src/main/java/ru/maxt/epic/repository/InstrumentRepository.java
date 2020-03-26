package ru.maxt.epic.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.maxt.epic.entity.Instrument;

import java.util.List;

@Repository
public interface InstrumentRepository extends CrudRepository<Instrument, Integer>, JpaSpecificationExecutor {
    @Cacheable
    List<Instrument> findByIsin(String name);
}
