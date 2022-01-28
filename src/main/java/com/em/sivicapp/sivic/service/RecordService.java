package com.em.sivicapp.sivic.service;

import com.em.sivicapp.sivic.model.Game;
import com.em.sivicapp.sivic.model.Record;
import com.em.sivicapp.sivic.repository.RecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

    @Autowired
    private RecordRepo recordRepo;

    @Value("${sivicapp.sivic.allowduplicaterecords}")
    private String allowDuplicateRecords;

    public Record save(Record r) {
        //
        return recordRepo.save(r);
    }
}
