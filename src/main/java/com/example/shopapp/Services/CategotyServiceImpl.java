package com.example.shopapp.Services;

import com.example.shopapp.models.Category;
import com.example.shopapp.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategotyServiceImpl implements CategoryService{

  private final CategoryRepository categoryRepository;

  public CategotyServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  @Override
  public List<Category> findAllById(Iterable<Long> longs) {
    return categoryRepository.findAllById(longs);
  }

  @Override
  public <S extends Category> S save(S entity) {
    return categoryRepository.save(entity);
  }

  @Override
  public Optional<Category> findById(Long aLong) {
    return categoryRepository.findById(aLong);
  }

  @Override
  public boolean existsById(Long aLong) {
    return categoryRepository.existsById(aLong);
  }

  @Override
  public void deleteById(Long aLong) {
    categoryRepository.deleteById(aLong);
  }
}
