package ru.maxt.epic.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.maxt.epic.entity.Quote;

@Repository
public interface QuotesRepository extends CrudRepository<Quote,Integer>, JpaSpecificationExecutor {
}
