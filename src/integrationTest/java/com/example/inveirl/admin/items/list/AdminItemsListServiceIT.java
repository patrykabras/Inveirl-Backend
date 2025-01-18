package com.example.inveirl.admin.items.list;

import com.example.inveirl.AbstractSpringIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("/get_items_admin.sql")
class AdminItemsListServiceIT extends AbstractSpringIntegrationTest {

    @Autowired
    private AdminItemsListService service;

    @Test
    void getItems() {
        // given
        final AdminItemsListFilterRequest request = AdminItemsListFilterRequest.builder()
                                                                               .build();

        // when
        final List<AdminItemsListItemResponse> response = service.getItems(request);

        // then
        assertThat(response).isNotNull()
                            .hasSize(2)
                            .first()
                            .extracting(AdminItemsListItemResponse::getId)
                            .isEqualTo(UUID.fromString("7bd55304-d00c-4c52-990e-8a6631aa9b08"));
    }

    @Test
    void getItemsWithId() {
        // given
        final UUID id = UUID.fromString("7bd55304-d00c-4c52-990e-8a6631aa9b08");
        final AdminItemsListFilterRequest request = AdminItemsListFilterRequest.builder()
                                                                               .id(id)
                                                                               .build();

        // when
        final List<AdminItemsListItemResponse> response = service.getItems(request);

        // then
        assertThat(response).isNotNull()
                            .hasSize(1)
                            .first()
                            .extracting(AdminItemsListItemResponse::getId)
                            .isEqualTo(id);
    }

    @Test
    void getItemsWithNameContains() {
        // given
        final String nameContains = "Apple";
        final AdminItemsListFilterRequest request = AdminItemsListFilterRequest.builder()
                                                                               .nameContains(nameContains)
                                                                               .build();

        // when
        final List<AdminItemsListItemResponse> response = service.getItems(request);

        // then
        assertThat(response).isNotNull()
                            .hasSize(1)
                            .first()
                            .extracting(AdminItemsListItemResponse::getName)
                            .asString()
                            .contains(nameContains);
    }

    @Test
    void getItemsWithBarCode() {
        // given
        final String barCode = "7423652082562";
        final AdminItemsListFilterRequest request = AdminItemsListFilterRequest.builder()
                                                                               .barCode(barCode)
                                                                               .build();

        // when
        final List<AdminItemsListItemResponse> response = service.getItems(request);

        // then
        assertThat(response).isNotNull()
                            .hasSize(1)
                            .first()
                            .extracting(AdminItemsListItemResponse::getBarCode)
                            .isEqualTo(barCode);
    }
}