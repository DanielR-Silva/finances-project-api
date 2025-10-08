package finances.api.infrastructure.configuration;

import finances.api.application.usecase.category.CreateCategoryTraUseCaseImpl;
import finances.api.application.usecase.category.DeleteCategoryTraUseCaseImpl;
import finances.api.application.usecase.category.GetCategoryTraUseCaseImpl;
import finances.api.application.usecase.category.UpdateCategoryTraUseCaseImpl;
import finances.api.domain.ports.outbound.CategoryTransactionRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryTraUseCaseConfiguration {

    @Bean
    public CreateCategoryTraUseCaseImpl createCategoryTraUseCase(CategoryTransactionRepositoryPort categoryTraRepository) {
        return new CreateCategoryTraUseCaseImpl(categoryTraRepository);
    }

    @Bean
    public GetCategoryTraUseCaseImpl getCategoryTraUseCase(CategoryTransactionRepositoryPort categoryTraRepository) {
        return new GetCategoryTraUseCaseImpl(categoryTraRepository);
    }

    @Bean
    public UpdateCategoryTraUseCaseImpl updateCategoryTraUseCase(CategoryTransactionRepositoryPort categoryTraRepository) {
        return new UpdateCategoryTraUseCaseImpl(categoryTraRepository);
    }

    @Bean
    public DeleteCategoryTraUseCaseImpl deleteCategoryTraUseCase(CategoryTransactionRepositoryPort categoryTraRepository) {
        return new DeleteCategoryTraUseCaseImpl(categoryTraRepository);
    }
}
