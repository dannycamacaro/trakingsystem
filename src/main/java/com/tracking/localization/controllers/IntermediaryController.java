package com.tracking.localization.controllers;

import com.tracking.localization.Services.IntermediaryServices;
import com.tracking.localization.entitys.IntermediaryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;


@Controller
public class IntermediaryController {
    @Autowired
    IntermediaryServices intermediaryServices;

    public IntermediaryEntity save(IntermediaryEntity intermediaryEntity) {
        intermediaryServices.save(intermediaryEntity);
        return intermediaryEntity;
    }


    public IntermediaryEntity update(IntermediaryEntity intermediaryEntity) {
        return null;
    }


    public Optional<IntermediaryEntity> findById(long id) {
        return intermediaryServices.findById(id);
    }


    public IntermediaryEntity findByEmail(String email) {
        return intermediaryServices.findByEmail(email);
    }

    public List<IntermediaryEntity> getAll() {
        return intermediaryServices.findAll();
    }
}
