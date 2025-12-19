package com.swaggerpetstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddNewOrderResponse {
    private Long id;
    private Long petId;
    private Integer quantity;
    private String shipDate; // or use OffsetDateTime if desired
    private String status;
    private Boolean complete;
}
