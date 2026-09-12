package finances.api.application.usecase.category;

import finances.api.domain.exceptions.EntityNotFoundDomainException;
import finances.api.domain.model.CategoryTransaction;
import finances.api.domain.ports.input.category.GetCategoryTransactionByIdUseCase;
import finances.api.domain.ports.output.CategoryTransactionRepositoryPort;

import java.util.UUID;

public class GetCategoryTransactionByIdUseCaseImpl implements GetCategoryTransactionByIdUseCase {

    private final CategoryTransactionRepositoryPort categoryRepository;

    public GetCategoryTransactionByIdUseCaseImpl(CategoryTransactionRepositoryPort categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryTransaction execute(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundDomainException("Category not found with id: " + id));
    }
}

