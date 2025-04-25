package io.com.github.pantry.api.service;

import io.com.github.pantry.api.dto.StatsResponse;
import io.com.github.pantry.api.model.GroceryItem;
import io.com.github.pantry.api.repository.GroceryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroceryItemService {

    private final GroceryItemRepository repository;

    public List<GroceryItem> getItemsByUser(Long userId, int skip, int take) {
        return repository.findByUserId(userId, PageRequest.of(skip / take, take));
    }

    public StatsResponse getStatsByUser(Long userId) {
        List<GroceryItem> allItems = repository.findByUserId(userId);

        double total = allItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        Set<String> visits = allItems.stream()
                .map(item -> item.getBoughtAt().toString())
                .collect(Collectors.toSet());

        return new StatsResponse(total, visits.size());
    }
}
