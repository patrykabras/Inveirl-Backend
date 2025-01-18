package com.example.inveirl.features.item.add;

import com.example.inveirl.infrastructure.enumeration.ItemType;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ItemAddServiceTest {

    private final ItemAddItemRepository repository = mock(ItemAddItemRepository.class);
    private final ItemAddService service = new ItemAddService(repository);

    @Test
    void shouldAddItem() {
        // given
        final ArgumentCaptor<ItemAddItemsEntity> argument = ArgumentCaptor.forClass(ItemAddItemsEntity.class);
        final ItemAddItemRequest request = ItemAddItemRequest.builder()
                                                             .name("Apple")
                                                             .description("Apple is a fruit")
                                                             .itemType(ItemType.FOOD)
                                                             .barCode("0792382370658")
                                                             .imageUrl("https://some-image-url.com/images/Apple.jpg")
                                                             .build();

        // when
        service.addItem(request);

        // then
        verify(repository).save(argument.capture());
        final ItemAddItemsEntity savedEntity = argument.getValue();

        assertThat(savedEntity).isNotNull()
                               .satisfies(entity -> {
                                   assertThat(entity.getDescription()).isEqualTo(request.getDescription());
                                   assertThat(entity.getType()).isEqualTo(request.getItemType());
                                   assertThat(entity.getBarCode()).isEqualTo(request.getBarCode());
                                   assertThat(entity.getImageUrl()).isEqualTo(request.getImageUrl());
                               });
    }
}