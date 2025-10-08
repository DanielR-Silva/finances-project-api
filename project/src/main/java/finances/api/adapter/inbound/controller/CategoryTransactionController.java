package finances.api.adapter.inbound.controller;

import finances.api.domain.model.CategoryTransaction;
import finances.api.domain.ports.inbound.category.CreateCategoryTraUseCase;
import finances.api.domain.ports.inbound.category.DeleteCategoryTraUseCase;
import finances.api.domain.ports.inbound.category.GetCategoryTraUseCase;
import finances.api.domain.ports.inbound.category.UpdateCategoryTraUseCase;
import finances.api.shared.dto.request.CategoryTraUpdateRequestDTO;
import finances.api.shared.dto.request.CategoryTransactionRequestDTO;
import finances.api.shared.dto.response.CategoryTransactionResponseDTO;
import finances.api.shared.mapper.CategoryTransactionMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    private final CreateCategoryTraUseCase createCategoryTraUseCase;
    private final GetCategoryTraUseCase getCategoryTraUseCase;
    private final UpdateCategoryTraUseCase updateCategoryTraUseCase;
    private final DeleteCategoryTraUseCase deleteCategoryTraUseCase;
    private final CategoryTransactionMapper mapper;

    @PostMapping()
    public ResponseEntity<CategoryTransactionResponseDTO> createCategoryTransaction(@Valid @RequestBody CategoryTransactionRequestDTO categoryTransactionDto) {
        CategoryTransaction createdCategoryTransaction = createCategoryTraUseCase.execute(mapper.toDomain(categoryTransactionDto));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCategoryTransaction.getId())
                .toUri();
        return ResponseEntity.created(location).body(mapper.toCategoryTransactionResponseDTO(createdCategoryTransaction));
    }

    @GetMapping
    public ResponseEntity<List<CategoryTransactionResponseDTO>> getAllCategoryTransactions() {
        List<CategoryTransaction> categoryTransactions = getCategoryTraUseCase.getAllCategory();
        if (categoryTransactions.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(
                categoryTransactions.stream()
                        .map(mapper::toCategoryTransactionResponseDTO)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryTransactionResponseDTO> getCategoryTransactionById(@PathVariable UUID id) {
        CategoryTransaction categoryTransaction = getCategoryTraUseCase.getById(id);
        return ResponseEntity.ok(mapper.toCategoryTransactionResponseDTO(categoryTransaction));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryTransactionResponseDTO> updateCategoryTransaction(@PathVariable UUID id, @RequestBody CategoryTraUpdateRequestDTO categoryTransactionDto) {
        CategoryTransaction updatedCategoryTransaction = updateCategoryTraUseCase.execute(mapper.toUpdatedCategoryTransaction(id, categoryTransactionDto));
        return ResponseEntity.ok(mapper.toCategoryTransactionResponseDTO(updatedCategoryTransaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryTransaction(@PathVariable UUID id) {
        deleteCategoryTraUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
