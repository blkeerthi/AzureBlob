package com.example.azureblob.Azureblob.Artifact;

import com.example.azureblob.Azureblob.wizard.Wizard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ArtifactServiceTest {

    @Mock
    ArtifactRepository artifactRepository;

    @InjectMocks
    ArtifactService artifactService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindByIdSuccess() {
        //Given. Arrange inputs and targets. Define the behavior of Mock object artifactRepository
        Artifact a1 = new Artifact();
        a1.setId("1250808601744904191");
        a1.setName("Deluminator");
        a1.setDescription("A Deluminator is a device invented by Albus Dumbledore that resembles a cigarette lighter. It is used to remove or absorb (as well as return) the light from any light source to provide cover to the user.");
        a1.setImageUrl("imageUrl");

        Wizard w = new Wizard();
        w.setId(2);
        w.setName("Keerthi");
        a1.setOwner(w);

        given(artifactRepository.findById("1250808601744904191")).willReturn(Optional.of(a1));

        //When. Act on the target behavior.When steps should cover the method to be tested.
        Artifact retartifact =artifactService.findById("1250808601744904191");

        //Then. Assert expect outcomes.
        assertThat(retartifact.getId()).isEqualTo(a1.getId());
        assertThat(retartifact.getName()).isEqualTo(a1.getName());
        assertThat(retartifact.getDescription()).isEqualTo(a1.getDescription());
        assertThat(retartifact.getImageUrl()).isEqualTo(a1.getImageUrl());
        verify(artifactRepository,times(1)).findById("1250808601744904191");
    }

    @Test
    void testFindByIdNotFound(){
        //Given
        given(artifactRepository.findById(Mockito.any(String.class))).willReturn(Optional.empty());

        //When
        Throwable throwable =  catchThrowable(()->{
                    Artifact retartifact =artifactService.findById("1250808601744904191");
                });

        //Then
       assertThat(throwable)
                .isInstanceOf(ArtifactNotFoundException.class)
                .hasMessage("Could not find artifact with Id1250808601744904191 :(");
        verify(artifactRepository,times(1)).findById("1250808601744904191");
    }

}