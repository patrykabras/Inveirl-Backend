package com.example.inveirl.admin.items.list;

import com.example.inveirl.infrastructure.Metadata;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "items")
@Entity(name = "AdminItemsListItemsEntity")
class AdminItemsListItemsEntity {

    @Id
    @Column(updatable = false, insertable = false)
    private UUID id;

    @Column(updatable = false, insertable = false)
    private String name;

    @Column(updatable = false, insertable = false)
    private String barCode;

    @Embedded
    @Builder.Default
    private Metadata metadata = new Metadata();

    public AdminItemsListItemResponse toItemDetailsItemResponse() {
        return AdminItemsListItemResponse.builder()
                                         .id(id)
                                         .name(name)
                                         .barCode(barCode)
                                         .creationDate(metadata.getCreationDate())
                                         .modificationDate(metadata.getModificationDate())
                                         .build();
    }
}
