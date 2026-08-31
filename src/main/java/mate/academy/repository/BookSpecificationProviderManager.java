package mate.academy.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.BookDto;
import mate.academy.repository.providers.SpecificationProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationProviderManager implements SpecificationProviderManager<BookDto> {
    private final List<SpecificationProvider<BookDto>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<BookDto> getSpecificationProvider(String key) {
        return bookSpecificationProviders
                .stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Cant find correct specification for key: " + key));
    }
}
