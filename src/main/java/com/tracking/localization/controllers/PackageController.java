package com.tracking.localization.controllers;

import com.tracking.localization.Services.PackageServices;
import com.tracking.localization.entitys.PackageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PackageController {
    @Autowired
    PackageServices packageServices;

    public Optional<PackageEntity> findById(Long id) {
        return packageServices.findById(id);
    }

    public PackageEntity save(PackageEntity packageEntity) {
        return packageServices.save(packageEntity);
    }

    public PackageEntity update(PackageEntity packageEntity) {
        return packageServices.save(packageEntity);
    }

    public List<PackageEntity> getAll() {
        return packageServices.findAll();
    }
}
