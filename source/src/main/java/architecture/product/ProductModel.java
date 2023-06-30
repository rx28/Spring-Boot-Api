package architecture.product;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public final class ProductModel {
    private UUID id;
    private String description;
    private BigDecimal price;
}
