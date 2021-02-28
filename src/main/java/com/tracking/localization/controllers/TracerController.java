package com.tracking.localization.controllers;

import com.tracking.localization.Services.TracerServices;
import com.tracking.localization.entitys.TraceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TracerController {
    @Autowired
    TracerServices tracerServices;

    public List<TraceEntity> findAllByPackageId(Long packageId) {
        List<TraceEntity> traceEntityList = tracerServices.findAllByProductId(packageId);
        return traceEntityList;
    }

    public void save(TraceEntity traceEntity) {
        tracerServices.save(traceEntity);
    }
}
