package com.swaggerpetstore.dto.response;

import com.swaggerpetstore.dto.request.AddNewPetRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddNewPetResponse {
	private int id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private List<Tag> tags;
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
