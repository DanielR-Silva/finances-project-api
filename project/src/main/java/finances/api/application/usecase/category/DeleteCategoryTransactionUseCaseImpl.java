package finances.api.application.usecase.category;

import finances.api.domain.ports.input.category.DeleteCategoryTransactionUseCase;
import finances.api.domain.ports.output.CategoryTransactionRepositoryPort;

import java.util.UUID;

public class DeleteCategoryTransactionUseCaseImpl implements DeleteCategoryTransactionUseCase {

    private final CategoryTransactionRepositoryPort categoryTraRepository;

    public DeleteCategoryTransactionUseCaseImpl(CategoryTransactionRepositoryPort categoryTraRepository) {
        this.categoryTraRepository = categoryTraRepository;
    }

    @Override
    public void execute(UUID id) {
        categoryTraRepository.deleteById(id);
    }
}
