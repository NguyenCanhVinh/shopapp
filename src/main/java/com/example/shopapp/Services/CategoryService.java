package com.example.shopapp.Services;

import com.example.shopapp.models.Category;

import java.util.List;
import java.util.Optional;


public interface CategoryService {

  List<Category> findAll();

  List<Category> findAllById(Iterable<Long> longs);

  <S extends Category> S save(S entity);

  Optional<Category> findById(Long aLong);

  boolean existsById(Long aLong);

  void deleteById(Long aLong);
}
