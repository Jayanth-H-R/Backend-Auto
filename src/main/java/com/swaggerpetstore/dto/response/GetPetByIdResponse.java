package com.swaggerpetstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPetByIdResponse {

    private int id;
    private AddNewPetResponse.Category category;
    private String name;
    private List<String> photoUrls;
    private List<AddNewPetResponse.Tag> tags;
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


