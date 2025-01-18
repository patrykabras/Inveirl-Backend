package com.example.inveirl.features.item.list;

import com.example.inveirl.AbstractSpringIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("/get_items_user.sql")
class ItemListServiceIT extends AbstractSpringIntegrationTest {

    @Autowired
    private ItemListService service;

    @Test
    void getItems() {
        // given
        final ItemListFilterRequest request = ItemListFilterRequest.builder()
                                                                   .build();

        // when
        final List<ItemListItemResponse> response = service.getItems(request);

        // then
        assertThat(response).isNotNull()
                            .hasSize(2)
                            .first()
                            .extracting(ItemListItemResponse::getId)
                            .isEqualTo(UUID.fromString("7bd55304-d00c-4c52-990e-8a6631aa9b08"));
    }

    @Test
    void getItemsWithId() {
        // given
        final UUID id = UUID.fromString("7bd55304-d00c-4c52-990e-8a6631aa9b08");
        final ItemListFilterRequest request = ItemListFilterRequest.builder()
                                                                   .id(id)
                                                                   .build();

        // when
        final List<ItemListItemResponse> response = service.getItems(request);

        // then
        assertThat(response).isNotNull()
                            .hasSize(1)
                            .first()
                            .extracting(ItemListItemResponse::getId)
                            .isEqualTo(id);
    }

    @Test
    void getItemsWithNameContains() {
        // given
        final String nameContains = "Apple";
        final ItemListFilterRequest request = ItemListFilterRequest.builder()
                                                                   .nameContains(nameContains)
                                                                   .build();

        // when
        final List<ItemListItemResponse> response = service.getItems(request);

        // then
        assertThat(response).isNotNull()
                            .hasSize(1)
                            .first()
                            .extracting(ItemListItemResponse::getName)
                            .asString()
                            .contains(nameContains);
    }

    @Test
    void getItemsWithBarCode() {
        // given
        final String barCode = "7423652082562";
        final ItemListFilterRequest request = ItemListFilterRequest.builder()
                                                                   .barCode(barCode)
                                                                   .build();

        // when
        final List<ItemListItemResponse> response = service.getItems(request);

        // then
        assertThat(response).isNotNull()
                            .hasSize(1)
                            .first()
                            .extracting(ItemListItemResponse::getBarCode)
                            .isEqualTo(barCode);
    }
}