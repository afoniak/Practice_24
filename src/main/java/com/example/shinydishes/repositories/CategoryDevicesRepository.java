package com.example.shinydishes.repositories;

import com.example.shinydishes.models.CategoryDevices;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryDevicesRepository extends CrudRepository<CategoryDevices, Long> {
    List<CategoryDevices> findByType(String type);
}
