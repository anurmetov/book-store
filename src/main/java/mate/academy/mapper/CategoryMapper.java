package mate.academy.mapper;

import mate.academy.config.MapperConfig;
import mate.academy.dto.CategoryDto;
import mate.academy.dto.CreateCategoryRequestDto;
import mate.academy.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDTO);

    Category toEntityFromRequest(CreateCategoryRequestDto createCategoryRequestDto);

}
