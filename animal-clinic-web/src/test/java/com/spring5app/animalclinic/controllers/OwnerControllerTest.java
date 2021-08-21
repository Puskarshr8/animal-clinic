package com.spring5app.animalclinic.controllers;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.spring5app.animalclinic.model.Owner;
import com.spring5app.animalclinic.services.OwnerService;

@ExtendWith(value = MockitoExtension.class)
class OwnerControllerTest {

	@Mock
	OwnerService ownerservice;

	@InjectMocks
	OwnerController controller;

	Set<Owner> owners;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {

		owners = new HashSet<>();
		Owner owner1 = new Owner();
		owner1.setId(1L);
		Owner owner2 = new Owner();
		owner2.setId(2L);
		owners.add(owner1);
		owners.add(owner2);

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void testListOwners() throws Exception {
		Owner owner = new Owner();
		owner.setId(1L);
		when(ownerservice.findAll()).thenReturn(owners);

		mockMvc.perform(get("/owners/index")).andExpect(status().isOk()).andExpect(view().name("owners/index"))
				.andExpect(model().attribute("owners", hasSize(2)));
	}

	@Test
	void testFindOwners() throws Exception {
		mockMvc.perform(get("/owners/find")).andExpect(status().isOk()).andExpect(view().name("owners/findOwners"))
				.andExpect(model().attributeExists("owner"));

		verifyZeroInteractions(ownerservice);
	}
	
	@Test
	void testShowOwners() throws Exception {
		Owner owner = new Owner();
		owner.setId(1L);
		when(ownerservice.findById(anyLong())).thenReturn(owner);

		mockMvc.perform(get("/owners/123")).andExpect(status().isOk()).andExpect(view().name("owners/OwnerDetails"))
				.andExpect(model().attribute("owner", hasProperty("id",is(1L))));
	}
	
	@Test
	void testProcessFindFormReturnMany() throws Exception
	{
		when(ownerservice.findByLastNameLike(any())).thenReturn(owners.stream().collect(Collectors.toList()));
		
		mockMvc.perform(get("/owners/owners")).andExpect(status().isOk())
				.andExpect(view().name("owners/ownersList")).andExpect(model().attribute("selections", hasSize(2)));
	}
	
	@Test
	void testProcessFindFormReturnOne() throws Exception
	{
		Owner owner = new Owner();
		owner.setId(1L);
		List<Owner> ownerList = new ArrayList<>();
		ownerList.add(owner);
		when(ownerservice.findByLastNameLike(anyString())).thenReturn(ownerList);
		
		mockMvc.perform(get("/owners/owners"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"));
	}
	
	@Test
	void testProcessFindFormEmtpyReturnMany() throws Exception
	{
		when(ownerservice.findByLastNameLike(any())).thenReturn(owners.stream().collect(Collectors.toList()));
		
		mockMvc.perform(get("/owners/owners").param("lastName", "")).andExpect(status().isOk())
        .andExpect(view().name("owners/ownersList"))
        .andExpect(model().attribute("selections", hasSize(2)));

	}
	
	@Test
	void testinitCreationForm() throws Exception
	{
		mockMvc.perform(get("/owners/new")).andExpect(status().isOk())
        .andExpect(model().attributeExists("owner"))
        .andExpect(view().name("owners/createOrUpdateOwnerForm"));
	}
	
	@Test
	void testprocessCreationForm() throws Exception
	{
		Owner savedOwner = new Owner();
		savedOwner.setId(1L);
		when(ownerservice.save(any())).thenReturn(savedOwner);
				
		mockMvc.perform(post("/owners/new"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"))
        .andExpect(model().attributeExists("owner"));

		verify(ownerservice).save(any());		
	}
	
	@Test
	void testinitUpdateForm() throws Exception
	{
		Owner findOwner = new Owner();
		findOwner.setId(1L);
		when(ownerservice.findById(anyLong())).thenReturn(findOwner);
		
		mockMvc.perform(get("/owners/1/edit")).andExpect(status().isOk())
        .andExpect(model().attributeExists("owner"))
        .andExpect(view().name("owners/createOrUpdateOwnerForm"));
		
		verifyZeroInteractions(ownerservice);	
	}
	
	void testprocessUpdateForm() throws Exception
	{
		Owner savedOwner = new Owner();
		savedOwner.setId(1L);
		when(ownerservice.save(any())).thenReturn(savedOwner);
		
        mockMvc.perform(post("/owners/1/edit"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"))
        .andExpect(model().attributeExists("owner"));

        verify(ownerservice).save(ArgumentMatchers.any());
	}
	
	
	
	
}
