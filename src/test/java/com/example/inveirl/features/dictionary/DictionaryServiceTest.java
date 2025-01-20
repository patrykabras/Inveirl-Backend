package com.example.inveirl.features.dictionary;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class DictionaryServiceTest {

    private final DictionaryService service = new DictionaryService();

    @Test
    void shouldGetDictionary() {
        // given

        // when
        final Map<String, List<String>> response = service.getDictionary();

        // then
        assertThat(response).isNotNull()
                            .hasSize(2)
                            .containsKeys("ItemType", "QuantityType");
    }

}