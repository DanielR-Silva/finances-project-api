package finances.api.application.usecase.category;

import finances.api.domain.ports.inbound.category.DeleteCategoryTraUseCase;
import finances.api.domain.ports.outbound.CategoryTransactionRepositoryPort;

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
