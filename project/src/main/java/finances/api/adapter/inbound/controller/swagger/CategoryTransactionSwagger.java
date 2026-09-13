package finances.api.adapter.inbound.controller.swagger;

import finances.api.shared.dto.request.CategoryTransactionRequestDTO;
import finances.api.shared.dto.request.CategoryTransactionUpdateRequestDTO;
import finances.api.shared.dto.response.CategoryTransactionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Category Transaction", description = "Operations related to category transaction management")
public interface CategoryTransactionSwagger {

    @PostMapping()
    @Operation(summary = "Create a new category transaction", description = "Creates a new category transaction with the provided information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category transaction created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryTransactionResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "Category transaction already exists"),
            @ApiResponse(responseCode = "500", description = "API internal error")
    })
    ResponseEntity<CategoryTransactionResponseDTO> createCategoryTransaction(@Valid @RequestBody CategoryTransactionRequestDTO categoryTransactionDto);

    @GetMapping()
    @Operation(summary = "Get all category transactions", description = "Retrieves a list of all category transactions.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category transactions retrieved successfully", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CategoryTransactionResponseDTO.class)))),
            @ApiResponse(responseCode = "204", description = "No category transactions found"),
            @ApiResponse(responseCode = "500", description = "API internal error")
    })
    ResponseEntity<List<CategoryTransactionResponseDTO>> getAllCategoryTransactions();

    @GetMapping("/{id}")
    @Operation(summary = "Get category transaction by ID", description = "Retrieves a category transaction by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category transaction retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryTransactionResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Category transaction not found"),
            @ApiResponse(responseCode = "500", description = "API internal error")
    })
    ResponseEntity<CategoryTransactionResponseDTO> getCategoryTransactionById(@PathVariable UUID id);

    @PutMapping("/{id}")
    @Operation(summary = "Update category transaction by ID", description = "Updates an existing category transaction with the provided information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category transaction updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryTransactionResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Category transaction not found"),
            @ApiResponse(responseCode = "500", description = "API internal error")
    })
    ResponseEntity<CategoryTransactionResponseDTO> updateCategoryTransaction(@PathVariable UUID id, @Valid @RequestBody CategoryTransactionUpdateRequestDTO categoryTransactionDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category transaction by ID", description = "Deletes a category transaction by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category transaction deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category transaction not found"),
            @ApiResponse(responseCode = "500", description = "API internal error")
    })
    ResponseEntity<Void> deleteCategoryTransaction(@PathVariable UUID id);
}
