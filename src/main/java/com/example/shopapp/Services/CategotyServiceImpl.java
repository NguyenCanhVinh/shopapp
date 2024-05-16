package com.example.shopapp.Services;

import com.example.shopapp.dto.CategoryDTO;
import com.example.shopapp.models.Category;
import com.example.shopapp.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategotyServiceImpl implements CategoryService{

  private final CategoryRepository categoryRepository;

//  public CategotyServiceImpl(CategoryRepository categoryRepository) {
//    this.categoryRepository = categoryRepository;
//  }

  @Override
  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  @Override
  public List<Category> findAllById(Iterable<Long> longs) {
    return categoryRepository.findAllById(longs);
  }

  @Override
  public <S extends Category> S save(Category category) {
    return (S) categoryRepository.save(category);
  }

  @Override
  public Optional<Category> findById(Long id) {
    return categoryRepository.findById(id);
  }

  @Override
  public boolean existsById(Long aLong) {
    return categoryRepository.existsById(aLong);
  }

  @Override
  public Category createrCategory(CategoryDTO categoryDTO){
    Category newCategory= Category
      .builder()
      .name(categoryDTO.getName())
      .build();
    return  categoryRepository.save(newCategory);
  }

  @Override
  public Category getCategoryById(long id){
    return categoryRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("category not found"));
  }

  @Override
  public Category updateCategory(long categoryId,
                                 CategoryDTO categoryDTO){
          Category category1= getCategoryById(categoryId);
          category1.setName(categoryDTO.getName());
          categoryRepository.save(category1);
          return  category1;
  }

  @Override
  public void deleteCategory(long id){
    categoryRepository.deleteById(id);
  }

}
