package com.swaggerpetstore.services;

import com.github.javafaker.Faker;
import com.swaggerpetstore.dto.request.AddNewPetRequest;
import com.swaggerpetstore.dto.testDataDto.AddNewPetData;

import java.util.ArrayList;


public class AddPetRequestHelper {
    // This class is intended to assist with the creation of addNewPetRequest objects
    // and can include methods for validation, transformation, or other utility functions
    // related to adding a new pet request.


    public AddNewPetRequest createAddNewPetRequest(AddNewPetData data) {

        Faker fakerData = new Faker();
        int petId = fakerData.number().hashCode();
        // This method creates an AddNewPetRequest object based on the provided AddNewPetData.
        ArrayList<AddNewPetRequest.Tag> tags = new ArrayList<>();

        // If the data contains tags, we create Tag objects and add them to the tags list.
        AddNewPetRequest.Tag createTag = AddNewPetRequest.Tag.builder().id(data.getTags().get(0).getId()).name(data.getTags().get(0).getName()).build();
        tags.add(createTag);

        // If the data contains a category, we create a Category object.
        AddNewPetRequest.Category createCategory = AddNewPetRequest.Category.builder().id(data.getCategory().getId()).name(data.getCategory().getName()).build();
            //data.setId(petId);
        // Finally, we build the AddNewPetRequest object using the provided data and the created Category and Tag objects.
        return AddNewPetRequest.builder()
                .id(data.getId() != 0 ? data.getId() : petId)
                .name(data.getName())
                .status(data.getStatus())
                .category(createCategory)
                .tags(tags)
                .photoUrls(data.getPhotoUrls() != null ? data.getPhotoUrls() : new ArrayList<>())
                .build();
    }

    public AddNewPetRequest updatePetRequest(AddNewPetData data) {
        // This method creates an AddNewPetRequest object for updating a pet.

        ArrayList<AddNewPetRequest.Tag> tags = new ArrayList<>();

        // If the data contains tags, we create Tag objects and add them to the tags list.
        AddNewPetRequest.Tag createTag = AddNewPetRequest.Tag.builder().id(data.getTags().get(0).getId()).name(data.getTags().get(0).getName()).build();
        tags.add(createTag);

        // If the data contains a category, we create a Category object.
        AddNewPetRequest.Category createCategory = AddNewPetRequest.Category.builder().id(data.getCategory().getId()).name(data.getCategory().getName()).build();
        data.setName("German Shepherd");
        return AddNewPetRequest.builder()
                .id(data.getId())
                .name(data.getName())
                .status(data.getStatus())
                .category(createCategory)
                .tags(tags)
                .photoUrls(data.getPhotoUrls() != null ? data.getPhotoUrls() : new ArrayList<>())
                .build();
    }
}
