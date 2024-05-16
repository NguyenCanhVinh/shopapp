package com.example.shopapp.Services;

import com.example.shopapp.dto.CategoryDTO;
import com.example.shopapp.models.Category;

import java.util.List;
import java.util.Optional;


public interface CategoryService {

  List<Category> findAll();

  List<Category> findAllById(Iterable<Long> longs);

  <S extends Category> S save(Category category);

  Optional<Category> findById(Long aLong);

  boolean existsById(Long aLong);

  Category createrCategory(CategoryDTO category);

  Category getCategoryById(long id);

  Category updateCategory(long categoryId, CategoryDTO categoryDTO);

  void deleteCategory(long id);
}
