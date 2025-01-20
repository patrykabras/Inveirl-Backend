package com.example.inveirl.features.dictionary;

import com.example.inveirl.infrastructure.enumeration.ItemType;
import com.example.inveirl.infrastructure.enumeration.QuantityType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
class DictionaryService {

    public Map<String, List<String>> getDictionary() {
        Map<String, List<String>> dictionary = new HashMap<>();
        dictionary.put("ItemType",
                       Arrays.stream(ItemType.values())
                             .toList()
                             .stream()
                             .map(Enum::name)
                             .toList());
        dictionary.put("QuantityType",
                       Arrays.stream(QuantityType.values())
                             .toList()
                             .stream()
                             .map(Enum::name)
                             .toList());
        return dictionary;
    }
}
