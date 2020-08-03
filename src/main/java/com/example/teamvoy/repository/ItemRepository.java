package com.example.teamvoy.repository;

import com.example.teamvoy.domain.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i")
    List<Item> findAllByPageable(Pageable pageable);
}
