package com.example.inveirl.features.inventory.list;

import com.example.inveirl.AbstractSpringIntegrationTest;
import com.example.inveirl.infrastructure.auth.jwt.AuthContextTestHelper;
import com.example.inveirl.infrastructure.enumeration.RoleEnum;
import com.example.inveirl.infrastructure.page.PageRequestParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("/get_inventory_items_user.sql")
class InventoryListServiceIT extends AbstractSpringIntegrationTest {

    @Autowired
    private InventoryListService service;

    @Test
    void getInventoryItems() {
        // given
        AuthContextTestHelper.prepareAuthContext(UUID.fromString("12ce85c9-ac6c-47e6-a3f5-6ab263e04dee"),
                                                 RoleEnum.ADMIN);
        final InventoryListFilterRequest request = InventoryListFilterRequest.builder()
                                                                             .build();
        final PageRequestParams pageRequestParams = PageRequestParams.builder()
                                                                     .page(0)
                                                                     .size(10)
                                                                     .build();

        // when
        final Page<InventoryListResponse> response = service.getInventoryItems(request, pageRequestParams);

        // then
        final List<InventoryListResponse> content = response.getContent();
        assertThat(content).isNotNull()
                           .hasSize(2)
                           .first()
                           .extracting(InventoryListResponse::getItemId)
                           .isEqualTo(UUID.fromString("2debcd49-e660-46c6-ae58-778218852fb5"));
    }

    @Test
    void getInventoryItemsByItemNameContains() {
        // given
        AuthContextTestHelper.prepareAuthContext(UUID.fromString("f60d259b-924a-43b7-9b6e-6eab9479ee3e"),
                                                 RoleEnum.USER);
        final InventoryListFilterRequest request = InventoryListFilterRequest.builder()
                                                                             .itemNameContains("pple")
                                                                             .build();
        final PageRequestParams pageRequestParams = PageRequestParams.builder()
                                                                     .page(0)
                                                                     .size(10)
                                                                     .build();

        // when
        final Page<InventoryListResponse> response = service.getInventoryItems(request, pageRequestParams);

        // then
        final List<InventoryListResponse> content = response.getContent();
        assertThat(content).isNotNull()
                           .hasSize(1)
                           .first()
                           .extracting(InventoryListResponse::getName)
                           .isEqualTo("Apple");
    }
}