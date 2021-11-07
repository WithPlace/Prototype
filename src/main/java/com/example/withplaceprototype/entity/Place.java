package com.example.withplaceprototype.entity;

import com.example.withplaceprototype.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Place extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="placeSeq")
    private Long seq;

    // TODO-02 구현 필요
}
