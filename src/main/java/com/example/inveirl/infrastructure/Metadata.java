package com.example.inveirl.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Embeddable
@EqualsAndHashCode
@Getter
@ToString
public class Metadata implements Serializable {

    @Column(name = "creation_date", updatable = false)
    private ZonedDateTime creationDate;

    @Column(name = "modification_date")
    private ZonedDateTime modificationDate;

    public Metadata() {
        this(ZonedDateTime.now());
    }

    public Metadata(final ZonedDateTime creationDate) {
        this.creationDate = creationDate;
        this.modificationDate = creationDate;
    }

    @PrePersist
    private void prePersist() {
        this.creationDate = ZonedDateTime.now();
        this.modificationDate = creationDate;
    }

    @PreUpdate
    private void preUpdate() {
        this.modificationDate = ZonedDateTime.now();
    }
}
