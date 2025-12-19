package com.swaggerpetstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPetByIdRequest {

	private int id;
	private AddNewPetRequest.Category category;
	private String name;
	private List<String> photoUrls;
	private List<AddNewPetRequest.Tag> tags;
	private String status;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Category {
		private int id;
		private String name;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Tag {
		private int id;
		private String name;
	}
}
