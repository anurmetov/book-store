package mate.academy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.BookDtoWithoutCategoryIds;
import mate.academy.dto.CategoryDto;
import mate.academy.dto.CreateCategoryRequestDto;
import mate.academy.service.BookService;
import mate.academy.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
@Tag(name = "Category Management", description = "API for managing categories")

public class CategoryController {

    private final CategoryService categoryService;
    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Get all categories", description = "Get all categories from DB")
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}/books")
    @Operation(summary = "Get list of books by Category Id",
            description = "Get list of books by Category Id from DB")
    public List<BookDtoWithoutCategoryIds> getBookByCategoryId(@PathVariable Long id) {
        return bookService.findAllBooksByCategoryId(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new category",
            description = "Add new category to DB")
    public CategoryDto createCategory(@Valid @RequestBody
                                          CreateCategoryRequestDto createCategoryRequestDto) {
        return categoryService.save(createCategoryRequestDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update existing category by Id",
            description = "Update existing category by Id in DB")
    public CategoryDto updateCategory(@Valid @RequestBody
                                          CreateCategoryRequestDto
                                                  createCategoryRequestDto,
                                   @PathVariable Long id) {
        return categoryService.updateById(id, createCategoryRequestDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete existing category by Id",
            description = "Delete existing category by Id from DB")

    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
