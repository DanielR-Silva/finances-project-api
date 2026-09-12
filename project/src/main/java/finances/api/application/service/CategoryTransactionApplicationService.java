package finances.api.application.service;

import finances.api.domain.model.CategoryTransaction;
import finances.api.domain.ports.input.category.CreateCategoryTransactionUseCase;
import finances.api.domain.ports.input.category.DeleteCategoryTransactionUseCase;
import finances.api.domain.ports.input.category.GetCategoryTransactionByIdUseCase;
import finances.api.domain.ports.input.category.ListAllCategoryTransactionsUseCase;
import finances.api.domain.ports.input.category.UpdateCategoryTransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Application Service (Facade) que agrupa os use cases relacionados ao agregado CategoryTransaction.
 * <p>
 * Mantém os controllers enxutos (uma única dependência) sem violar o ISP nos use cases,
 * já que cada porta de entrada continua representando uma única operação.
 */
@Service
@RequiredArgsConstructor
public class CategoryTransactionApplicationService {

    private final CreateCategoryTransactionUseCase createCategoryTransactionUseCase;
    private final GetCategoryTransactionByIdUseCase getCategoryTransactionByIdUseCase;
    private final ListAllCategoryTransactionsUseCase listAllCategoryTransactionsUseCase;
    private final UpdateCategoryTransactionUseCase updateCategoryTransactionUseCase;
    private final DeleteCategoryTransactionUseCase deleteCategoryTransactionUseCase;

    @Transactional
    public CategoryTransaction create(CategoryTransaction categoryTransaction) {
        return createCategoryTransactionUseCase.execute(categoryTransaction);
    }

    @Transactional(readOnly = true)
    public CategoryTransaction getById(UUID id) {
        return getCategoryTransactionByIdUseCase.execute(id);
    }

    @Transactional(readOnly = true)
    public List<CategoryTransaction> listAll() {
        return listAllCategoryTransactionsUseCase.execute();
    }

    @Transactional
    public CategoryTransaction update(CategoryTransaction categoryTransaction) {
        return updateCategoryTransactionUseCase.execute(categoryTransaction);
    }

    @Transactional
    public void delete(UUID id) {
        deleteCategoryTransactionUseCase.execute(id);
    }
}

