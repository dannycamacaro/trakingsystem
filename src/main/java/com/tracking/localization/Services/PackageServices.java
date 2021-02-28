package com.tracking.localization.Services;

import com.tracking.localization.entitys.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageServices extends JpaRepository<PackageEntity, Long> {


}
