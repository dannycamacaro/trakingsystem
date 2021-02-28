package com.tracking.localization.Services;

import com.tracking.localization.entitys.TraceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TracerServices extends JpaRepository<TraceEntity, Long> {

    List<TraceEntity> findAllByProductId(Long numberProduct);

}
