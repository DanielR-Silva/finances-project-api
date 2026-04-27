package finances.api.application.usecase.category;

import finances.api.domain.ports.input.category.DeleteCategoryTraUseCase;
import finances.api.domain.ports.output.CategoryTransactionRepositoryPort;

import java.util.UUID;

public class DeleteCategoryTraUseCaseImpl implements DeleteCategoryTraUseCase {

    private final CategoryTransactionRepositoryPort categoryTraRepository;

    public DeleteCategoryTraUseCaseImpl(CategoryTransactionRepositoryPort categoryTraRepository) {
        this.categoryTraRepository = categoryTraRepository;
    }

    @Override
    public void execute(UUID id) {
        categoryTraRepository.deleteById(id);
    }
}
