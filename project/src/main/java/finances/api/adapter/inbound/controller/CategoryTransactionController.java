package finances.api.adapter.inbound.controller;

import finances.api.application.service.CategoryTransactionApplicationService;
import finances.api.adapter.inbound.controller.mapper.CategoryTransactionMapper;
import finances.api.domain.model.CategoryTransaction;
import finances.api.shared.dto.request.CategoryTransactionRequestDTO;
import finances.api.shared.dto.request.CategoryTransactionUpdateRequestDTO;
import finances.api.shared.dto.response.CategoryTransactionResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category-transactions")
@RequiredArgsConstructor
public class CategoryTransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryTransactionController.class);

    private final CategoryTransactionApplicationService categoryService;
    private final CategoryTransactionMapper mapper;

    @PostMapping()
    public ResponseEntity<CategoryTransactionResponseDTO> createCategoryTransaction(@Valid @RequestBody CategoryTransactionRequestDTO categoryTransactionDto) {
        LOGGER.info("Creating category transaction");
        CategoryTransaction createdCategoryTransaction = categoryService.create(mapper.toDomain(categoryTransactionDto));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCategoryTransaction.getId())
                .toUri();
        LOGGER.info("Category transaction created with id: {}", createdCategoryTransaction.getId());
        LOGGER.debug("Category transaction: {}", createdCategoryTransaction);
        return ResponseEntity.created(location).body(mapper.toCategoryTransactionResponseDTO(createdCategoryTransaction));
    }

    @GetMapping
    public ResponseEntity<List<CategoryTransactionResponseDTO>> getAllCategoryTransactions() {
        LOGGER.info("Getting all category transactions");
        List<CategoryTransaction> categoryTransactions = categoryService.listAll();
        LOGGER.info("Category transactions count: {}", categoryTransactions.size());
        LOGGER.debug("Category transactions: {}", categoryTransactions);
        if (categoryTransactions.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(
                categoryTransactions.stream()
                        .map(mapper::toCategoryTransactionResponseDTO)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryTransactionResponseDTO> getCategoryTransactionById(@PathVariable UUID id) {
        LOGGER.info("Getting category transaction by id: {}", id);
        CategoryTransaction categoryTransaction = categoryService.getById(id);
        LOGGER.info("Category transaction found");
        LOGGER.debug("Category transaction: {}", categoryTransaction);
        return ResponseEntity.ok(mapper.toCategoryTransactionResponseDTO(categoryTransaction));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryTransactionResponseDTO> updateCategoryTransaction(@PathVariable UUID id, @Valid @RequestBody CategoryTransactionUpdateRequestDTO categoryTransactionDto) {
        LOGGER.info("Updating category transaction");
        CategoryTransaction updatedCategoryTransaction = categoryService.update(mapper.toUpdatedCategoryTransaction(id, categoryTransactionDto));
        LOGGER.info("Category transaction updated");
        LOGGER.debug("Category transaction: {}", updatedCategoryTransaction);
        return ResponseEntity.ok(mapper.toCategoryTransactionResponseDTO(updatedCategoryTransaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryTransaction(@PathVariable UUID id) {
        LOGGER.info("Deleting category transaction with id: {}", id);
        categoryService.delete(id);
        LOGGER.info("Deleted Category transaction");
        return ResponseEntity.noContent().build();
    }
}
