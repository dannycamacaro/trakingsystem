package com.tracking.localization.entitys;

import javax.persistence.*;

@Entity
@Table (name = "INTERMEDIARY")
public class IntermediaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String completeName;
    @Column
    private String email;
    @Column
    private String phoneNumber;

    public IntermediaryEntity() {
    }

    public IntermediaryEntity(String completeName, String email, String phoneNumber) {
        this.completeName = completeName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
