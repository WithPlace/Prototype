package com.example.withplaceprototype.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    private String regBy;

    @Column(name = "regDate", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime regDate;

    private String uptBy;

    @Column(name = "uptdate")
    @UpdateTimestamp
    private LocalDateTime uptdate;

}