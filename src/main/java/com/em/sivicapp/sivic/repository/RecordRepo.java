package com.em.sivicapp.sivic.repository;

import com.em.sivicapp.sivic.model.Record;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepo extends CrudRepository<Record, Long> {
}
