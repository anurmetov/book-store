package mate.academy.repository;

import mate.academy.repository.providers.SpecificationProvider;

public interface SpecificationProviderManager<T> {
    SpecificationProvider<T> getSpecificationProvider(String key);
}
