package mate.academy.mapper;

import mate.academy.config.MapperConfig;
import mate.academy.dto.CategoryDto;
import mate.academy.dto.CreateCategoryRequestDto;
import mate.academy.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntityFromRequest(CreateCategoryRequestDto createCategoryRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    void updateCategoryFromRequest(CreateCategoryRequestDto createCategoryRequestDto,
                               @MappingTarget Category category);

}
