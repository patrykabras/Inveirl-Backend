package com.example.inveirl.features.inventory.add;

import com.example.inveirl.infrastructure.auth.jwt.AuthContextTestHelper;
import com.example.inveirl.infrastructure.enumeration.ItemType;
import com.example.inveirl.infrastructure.enumeration.QuantityType;
import com.example.inveirl.infrastructure.enumeration.RoleEnum;
import com.example.inveirl.infrastructure.exceptions.ItemNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class InventoryAddServiceTest {

    private static final int QUANTITY = 1;

    private final InventoryAddItemRepository itemRepository = mock(InventoryAddItemRepository.class);
    private final InventoryAddUsersInventoriesRepository inventoryRepository = mock(
            InventoryAddUsersInventoriesRepository.class);
    private final InventoryAddService service = new InventoryAddService(itemRepository, inventoryRepository);

    @BeforeEach
    void setUp() {
        AuthContextTestHelper.prepareAuthContext(RoleEnum.USER);
    }

    @Test
    void shouldAddItemToInventory() {
        // given
        final ArgumentCaptor<InventoryAddUsersInventoriesEntity> argument = ArgumentCaptor.forClass(
                InventoryAddUsersInventoriesEntity.class);

        final InventoryAddItemRequest request = prepareInventoryAddItemRequest();

        when(itemRepository.findById(request.getItemId())).thenReturn(Optional.of(prepareItemsEntity(request)));
        when(inventoryRepository.getByUserIdAndItemId(any(), any())).thenReturn(Optional.empty());

        // when
        service.addItemToInventory(request);

        // then
        verify(inventoryRepository).save(argument.capture());
        final InventoryAddUsersInventoriesEntity savedEntity = argument.getValue();

        assertThat(savedEntity).isNotNull()
                               .satisfies(entity -> {
                                   assertThat(entity.getItemId()).isEqualTo(request.getItemId());
                                   assertThat(entity.getQuantityType()).isEqualTo(request.getQuantityType());
                                   assertThat(entity.getQuantity()).isEqualTo(request.getQuantity());
                                   assertThat(entity.getExpirationDate()).isEqualTo(request.getExpirationDate());
                                   assertThat(entity.isExists()).isTrue();
                               });

    }

    @Test
    void shouldUpdateItemToInventory() {
        // given
        final ArgumentCaptor<InventoryAddUsersInventoriesEntity> argument = ArgumentCaptor.forClass(
                InventoryAddUsersInventoriesEntity.class);

        final InventoryAddItemRequest request = prepareInventoryAddItemRequest();
        final InventoryAddUsersInventoriesEntity inventoriesEntity = prepareInventoryAddUsersInventoriesEntity(request);

        when(itemRepository.findById(request.getItemId())).thenReturn(Optional.of(prepareItemsEntity(request)));
        when(inventoryRepository.getByUserIdAndItemId(any(), any())).thenReturn(Optional.of(inventoriesEntity));

        // when
        service.addItemToInventory(request);

        // then
        verify(inventoryRepository).save(argument.capture());
        final InventoryAddUsersInventoriesEntity savedEntity = argument.getValue();

        assertThat(savedEntity).isNotNull()
                               .satisfies(entity -> {
                                   assertThat(entity.getItemId()).isEqualTo(request.getItemId());
                                   assertThat(entity.getQuantityType()).isEqualTo(request.getQuantityType());
                                   assertThat(entity.getQuantity()).isEqualTo(QUANTITY + QUANTITY);
                                   assertThat(entity.getExpirationDate()).isEqualTo(request.getExpirationDate());
                                   assertThat(entity.isExists()).isTrue();
                               });

    }

    @Test
    void shouldThrowItemNotFoundException() {
        // given
        final InventoryAddItemRequest request = prepareInventoryAddItemRequest();

        when(itemRepository.findById(request.getItemId())).thenReturn(Optional.empty());

        // when
        final var exception = assertThatThrownBy(() -> service.addItemToInventory(request));

        // then
        exception.isInstanceOf(ItemNotFoundException.class)
                 .hasMessage("Item with identifier '" + request.getItemId() + "' not found")
                 .hasNoCause();
    }

    private static InventoryAddUsersInventoriesEntity prepareInventoryAddUsersInventoriesEntity(InventoryAddItemRequest request) {
        return InventoryAddUsersInventoriesEntity.builder()
                                                 .itemId(request.getItemId())
                                                 .quantityType(request.getQuantityType())
                                                 .quantity(request.getQuantity())
                                                 .expirationDate(request.getExpirationDate())
                                                 .isExists(true)
                                                 .build();
    }

    private static InventoryAddItemRequest prepareInventoryAddItemRequest() {
        return InventoryAddItemRequest.builder()
                                      .itemId(UUID.randomUUID())
                                      .quantityType(QuantityType.KILOGRAM)
                                      .quantity(QUANTITY)
                                      .expirationDate(ZonedDateTime.now())
                                      .build();
    }

    private static InventoryAddItemsEntity prepareItemsEntity(InventoryAddItemRequest request) {
        return InventoryAddItemsEntity.builder()
                                      .id(request.getItemId())
                                      .name("Apple")
                                      .description("Apple is a fruit")
                                      .type(ItemType.FOOD)
                                      .barCode("0792382370658")
                                      .imageUrl("https://some-image-url.com/images/Apple.jpg")
                                      .build();
    }

}