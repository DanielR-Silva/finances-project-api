package finances.api.infrastructure.configuration;

import finances.api.application.usecase.category.CreateCategoryTransactionUseCaseImpl;
import finances.api.application.usecase.category.DeleteCategoryTransactionUseCaseImpl;
import finances.api.application.usecase.category.GetCategoryTransactionByIdUseCaseImpl;
import finances.api.application.usecase.category.ListAllCategoryTransactionsUseCaseImpl;
import finances.api.application.usecase.category.UpdateCategoryTransactionUseCaseImpl;
import finances.api.domain.ports.input.category.CreateCategoryTransactionUseCase;
import finances.api.domain.ports.input.category.DeleteCategoryTransactionUseCase;
import finances.api.domain.ports.input.category.GetCategoryTransactionByIdUseCase;
import finances.api.domain.ports.input.category.ListAllCategoryTransactionsUseCase;
import finances.api.domain.ports.input.category.UpdateCategoryTransactionUseCase;
import finances.api.domain.ports.output.CategoryTransactionRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryTransactionUseCaseConfiguration {

    @Bean
    public CreateCategoryTransactionUseCase createCategoryTransactionUseCase(CategoryTransactionRepositoryPort categoryRepository) {
        return new CreateCategoryTransactionUseCaseImpl(categoryRepository);
    }

    @Bean
    public GetCategoryTransactionByIdUseCase getCategoryTransactionByIdUseCase(CategoryTransactionRepositoryPort categoryRepository) {
        return new GetCategoryTransactionByIdUseCaseImpl(categoryRepository);
    }

    @Bean
    public ListAllCategoryTransactionsUseCase listAllCategoryTransactionsUseCase(CategoryTransactionRepositoryPort categoryRepository) {
        return new ListAllCategoryTransactionsUseCaseImpl(categoryRepository);
    }

    @Bean
    public UpdateCategoryTransactionUseCase updateCategoryTransactionUseCase(CategoryTransactionRepositoryPort categoryRepository) {
        return new UpdateCategoryTransactionUseCaseImpl(categoryRepository);
    }

    @Bean
    public DeleteCategoryTransactionUseCase deleteCategoryTransactionUseCase(CategoryTransactionRepositoryPort categoryRepository) {
        return new DeleteCategoryTransactionUseCaseImpl(categoryRepository);
    }
}
