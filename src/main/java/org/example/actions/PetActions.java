package org.example.actions;


import io.restassured.module.jsv.JsonSchemaValidator;
import org.example.dto.Pet;
import org.example.services.PetApi;

import static org.hamcrest.Matchers.equalTo;

public class PetActions {

    private final Pet pet;
    private final PetApi petApi;

    public PetActions(Pet pet) {
        this.pet = pet;
        petApi = new PetApi();
    }

    public PetActions createPet () {
        petApi.createPet(pet)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/pet/CreatePet.json"));
        return this;
    }

    public PetActions getPet() {
        petApi.getPet(pet)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/pet/CreatePet.json"));
        return this;
    }

    public PetActions removePet() {
        petApi.removePet(pet);
        return this;
    }

    public  PetActions setUnavailablePetStatus (){
        petApi.setUnavailablePetStatus(pet);
        petApi.getPet(pet)
                .body("status", equalTo("unavailable"));
        return this;
    }
}
