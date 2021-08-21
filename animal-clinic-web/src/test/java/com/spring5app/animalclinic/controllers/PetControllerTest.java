package com.spring5app.animalclinic.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.spring5app.animalclinic.model.Owner;
import com.spring5app.animalclinic.model.Pet;
import com.spring5app.animalclinic.model.PetType;
import com.spring5app.animalclinic.services.OwnerService;
import com.spring5app.animalclinic.services.PetService;
import com.spring5app.animalclinic.services.PetTypeService;

@ExtendWith(value = MockitoExtension.class)
public class PetControllerTest {

	@Mock
	PetService petService;

	@Mock
	OwnerService ownerService;

	@Mock
	PetTypeService petTypeService;

	@InjectMocks
	PetController petController;

	MockMvc mockMvc;

	Owner owner;
	Set<PetType> petTypes;

	@BeforeEach
	void setUp() {
		owner = new Owner();
		owner.setId(1L);

		PetType dogType = new PetType();
		dogType.setId(1L);
		dogType.setName("Dog");
		
		PetType catType = new PetType();
		catType.setId(2L);
		catType.setName("Cat");
		
		petTypes = new HashSet<>();		
		petTypes.add(dogType);
		petTypes.add(catType);

		mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
	}

	@Test
	void initCreationForm() throws Exception {
		when(ownerService.findById(anyLong())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);

		mockMvc.perform(get("/owners/1/pets/new")).andExpect(status().isOk())
				.andExpect(model().attributeExists("owner")).andExpect(model().attributeExists("pet"))
				.andExpect(view().name("pets/createOrUpdatePetForm"));
	}

	@Test
	void processCreationForm() throws Exception {
		when(ownerService.findById(anyLong())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);

		mockMvc.perform(post("/owners/1/pets/new")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"));

		verify(petService).save(any());
	}

	@Test
	void initUpdateForm() throws Exception {
		when(ownerService.findById(anyLong())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);
		Pet pet = new Pet();
		pet.setId(2L);
		when(petService.findById(anyLong())).thenReturn(pet);

		mockMvc.perform(get("/owners/1/pets/2/edit")).andExpect(status().isOk())
				.andExpect(model().attributeExists("owner")).andExpect(model().attributeExists("pet"))
				.andExpect(view().name("pets/createOrUpdatePetForm"));
	}

	@Test
	void processUpdateForm() throws Exception {
		when(ownerService.findById(anyLong())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);

		mockMvc.perform(post("/owners/1/pets/2/edit")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"));

		verify(petService).save(any());
	}

	@Test
	void populatePetTypes() {
		// todo impl
	}

	@Test
	void findOwner() {
		// todo impl
	}

	@Test
	void initOwnerBinder() {
		// todo impl
	}

}
