package com.example.inveirl.infrastructure.page;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Schema(name = "PageRequest", description = "Page request")
public class PageRequestParams {

    @Parameter(description = "Page number", example = "0")
    private int page;
    @Parameter(description = "Page size", example = "10")
    private int size;
    @Parameter(description = "Sort column", example = "id")
    private String sortColumn;
    @Parameter(description = "Sort direction", example = "asc")
    private String sortDirection;

    public PageRequestParams() {
        this.page = 0;
        this.size = 10;
        this.sortColumn = "metadata.creationDate";
        this.sortDirection = Sort.Direction.ASC.name();
    }

    public Pageable toPageable() {
        Sort sort = resolveSort();
        return PageRequest.of(page, size, sort);
    }

    private Sort resolveSort() {
        if (sortDirection == null || sortColumn == null) {
            return Sort.unsorted();
        }
        return sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
               ? Sort.by(sortColumn)
                     .ascending()
               : Sort.by(sortColumn)
                     .descending();
    }
}
