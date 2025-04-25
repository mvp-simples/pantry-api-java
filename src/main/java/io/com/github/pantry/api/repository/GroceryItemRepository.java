package io.com.github.pantry.api.repository;

import io.com.github.pantry.api.model.GroceryItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
    List<GroceryItem> findByUserId(Long userId, Pageable pageable);
    List<GroceryItem> findByUserId(Long userId);
}
