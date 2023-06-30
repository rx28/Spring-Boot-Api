package architecture.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@Document(collection = "products")
public final class Product {
    @NotNull
    private UUID id;

    @NotBlank
    private String description;

    @Min(0)
    private BigDecimal price;
}
