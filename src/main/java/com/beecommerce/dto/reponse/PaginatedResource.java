package com.beecommerce.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PaginatedResource<T> {
    private List<T> items;
    private int currentPage;
    private int totalPages;
    private long totalItems;
    private boolean hasNextPage;
}
