package com.tracking.localization.Services;

import com.tracking.localization.entitys.IntermediaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IntermediaryServices extends JpaRepository<IntermediaryEntity, Long> {

    IntermediaryEntity findByEmail(String email);
}
