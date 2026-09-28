package mate.academy.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.CategoryDto;
import mate.academy.dto.CreateCategoryRequestDto;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.mapper.CategoryMapper;
import mate.academy.model.Category;
import mate.academy.repository.CategoryRepository;
import mate.academy.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto save(CreateCategoryRequestDto createCategoryRequestDto) {
        Category categoryEntity = categoryMapper.toEntityFromRequest(createCategoryRequestDto);
        Category savedCategory = categoryRepository.save(categoryEntity);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public CategoryDto updateById(Long id, CreateCategoryRequestDto createCategoryRequestDto) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Category was not found by id: " + id));

        category.setName(createCategoryRequestDto.name());
        category.setDescription(createCategoryRequestDto.description());

        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }
    }
}
