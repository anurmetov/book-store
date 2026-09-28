package mate.academy.service;


import java.util.List;
import mate.academy.dto.BookDto;
import mate.academy.model.Category;

public interface CategoryService {

    List<Category> getAllCategories();

    List<BookDto> getAllByCategoryId(Long id);
}
