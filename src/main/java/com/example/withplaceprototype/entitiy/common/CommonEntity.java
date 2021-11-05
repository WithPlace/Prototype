package com.example.withplaceprototype.entitiy.common;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@MappedSuperclass
public abstract class CommonEntity {

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean deletedFlags = false;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
