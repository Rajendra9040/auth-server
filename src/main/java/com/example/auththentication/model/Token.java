package com.example.auththentication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "token")
public class Token extends BaseModel {
    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "expiry")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date expiry;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
