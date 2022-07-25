package org.example;

import org.example.actions.PetActions;
import org.example.actions.UserActions;
import org.example.dto.Category;
import org.example.dto.Pet;
import org.example.dto.Tag;
import org.example.dto.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PetTest {
    private Pet pet;
    private PetActions petActions;

    @BeforeEach
    private void tearUp(){

        List<String> photoUrls = new ArrayList<>(Arrays.asList("photoUrls1", "photoUrls2"));

        Category category = Category.builder()
                                        .id(12L)
                                        .name("CatName")
                                        .build();

        List<Tag> tags = new ArrayList<>(){
            {
                add(Tag.builder()
                        .id(24L)
                        .name("TagName")
                        .build()

                );
            }
        };


        pet = Pet.builder()
                .id(999L)
                .category(category)
                .name("name")
                .photoUrls(photoUrls)
                .tags(tags)
                .status("available")
                .build();

        petActions = new PetActions(pet);
    }

    @AfterEach
    private void tearDown(){
        petActions.removePet();
    }

    /* Test Case #3
    Summary: Get pet information
    Precondition:
        - Create a new pet
    Steps:
        1) Get Pet information
    Expected result:
        - Pet information should be available. Status code 200 OK
    Post processing:
        - Remove the pet
    * */
    @Test
    public void getPetInformation() {

        petActions.createPet()
                .getPet();
    }

    /* Test Case #4
    Summary: Update pet status
    Precondition:
        - Create a new pet
    Steps:
        1) Set pet's status to "unavailable"
    Expected result:
        - New pet status should be "unavailable". Status code 200 OK
    Post processing:
        - Remove the pet
    * */
    @Test
    public void updatePetStatus() {

        petActions.createPet()
                .setUnavailablePetStatus();
    }
}
