package com.tracking.localization.Services;

import com.tracking.localization.entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserServices extends JpaRepository<UserEntity, Long> {

    UserEntity findByLoginAndPassword(String login, String password);
}
