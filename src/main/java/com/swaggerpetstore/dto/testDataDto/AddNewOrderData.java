package com.swaggerpetstore.dto.testDataDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddNewOrderData {
	private Long id;
	private Long petId;
	private Integer quantity;
	private String shipDate; // or use OffsetDateTime if desired
	private String status;
	private Boolean complete;
}
