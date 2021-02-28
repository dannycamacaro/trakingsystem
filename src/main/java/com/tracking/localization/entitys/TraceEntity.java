package com.tracking.localization.entitys;

import javax.persistence.*;

@Entity
@Table(name = "TRACKING")
public class TraceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long productId;
    private String intermediary;
    private String location;

    public TraceEntity() {
    }

    public TraceEntity(long productId, String intermediary, String location) {
        this.intermediary = intermediary;
        this.location = location;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIntermediary() {
        return intermediary;
    }

    public void setIntermediary(String intermediary) {
        this.intermediary = intermediary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
