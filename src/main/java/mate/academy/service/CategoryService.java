package mate.academy.service;

import java.util.List;
import mate.academy.dto.CategoryDto;
import mate.academy.dto.CreateCategoryRequestDto;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto save(CreateCategoryRequestDto createCategoryRequestDto);

    CategoryDto updateById(Long id, CreateCategoryRequestDto createCategoryRequestDto);

    void deleteById(Long id);

}
