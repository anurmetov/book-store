package mate.academy.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.model.Category;
import mate.academy.service.BookService;
import mate.academy.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final BookService bookService;


    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }


    @GetMapping("/{id}/books")
    public List<Category> getBookByCategoryId(@PathVariable Long id) {
        return bookService.get();
    }


    // Admin: POST addNewCategory, PUT updateCategoryById, DELETE deleteCategory
}
