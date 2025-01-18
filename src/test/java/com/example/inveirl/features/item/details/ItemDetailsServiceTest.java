package com.example.inveirl.features.item.details;

import com.example.inveirl.infrastructure.Metadata;
import com.example.inveirl.infrastructure.enumeration.ItemType;
import com.example.inveirl.infrastructure.exceptions.ItemNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ItemDetailsServiceTest {

    private final ItemDetailsItemRepository repository = mock(ItemDetailsItemRepository.class);
    private final ItemDetailsService service = new ItemDetailsService(repository);

    @Test
    void shouldGetItemDetails() {
        // given
        final UUID itemId = UUID.randomUUID();
        final String name = "Apple";
        final String description = "Apple is a fruit";
        final ItemType type = ItemType.FOOD;
        final String barCode = "7423652082562";
        final String imageUrl = "https://some-image-url.com/images/Apple.jpg";
        final Metadata metadata = new Metadata();
        final ItemDetailsItemsEntity entity = ItemDetailsItemsEntity.builder()
                                                                    .id(itemId)
                                                                    .name(name)
                                                                    .description(description)
                                                                    .type(type)
                                                                    .barCode(barCode)
                                                                    .imageUrl(imageUrl)
                                                                    .metadata(metadata)
                                                                    .build();

        when(repository.findById(itemId)).thenReturn(Optional.of(entity));

        // when
        final ItemDetailsItemResponse response = service.getItemDetails(itemId);

        // then
        assertThat(response).isNotNull()
                            .satisfies(item -> {
                                assertThat(item.getId()).isEqualTo(itemId);
                                assertThat(item.getName()).isEqualTo(name);
                                assertThat(item.getDescription()).isEqualTo(description);
                                assertThat(item.getItemType()).isEqualTo(type);
                                assertThat(item.getBarCode()).isEqualTo(barCode);
                                assertThat(item.getImageUrl()).isEqualTo(imageUrl);
                                assertThat(item.getModificationDate()).isEqualTo(metadata.getModificationDate());
                                assertThat(item.getCreationDate()).isEqualTo(metadata.getCreationDate());
                            });
    }

    @Test
    void shouldThrowExceptionWhenItemNotFound() {
        // given
        final UUID itemId = UUID.randomUUID();
        when(repository.findById(itemId)).thenReturn(Optional.empty());

        // when
        final var exception = assertThatThrownBy(() -> {
            service.getItemDetails(itemId);
        });

        // then
        exception.isInstanceOf(ItemNotFoundException.class)
                 .hasMessage("Item with identifier '" + itemId + "' not found")
                 .hasNoCause();
    }
}