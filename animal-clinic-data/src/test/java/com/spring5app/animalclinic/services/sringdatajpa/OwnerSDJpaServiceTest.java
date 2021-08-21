package com.spring5app.animalclinic.services.sringdatajpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.spring5app.animalclinic.model.Owner;
import com.spring5app.animalclinic.repositories.OwnerRepositories;
import com.spring5app.animalclinic.repositories.PetRepository;
import com.spring5app.animalclinic.repositories.PetTypeRepositoy;

@ExtendWith(value = MockitoExtension.class)
class OwnerSDJpaServiceTest {
	
	static final Long OWNER_ID = 1L;
	static final String LAST_NAME = "Smith";
	
	@Mock
	OwnerRepositories ownerRepository;
	
	@Mock
	PetRepository petResepository;
	
	@Mock
	PetTypeRepositoy petTypeRepository;
	
	@InjectMocks
	OwnerSDJpaService ownerSDJpaService;
	
	Owner returnOwner;
	
	@BeforeEach
	void setUp() throws Exception {
		returnOwner = new Owner();
		returnOwner.setId(OWNER_ID);
		returnOwner.setLastName(LAST_NAME);
	}

	@Test
	void testFindAll() {
		
		Set<Owner> returnOwnersSet = new HashSet<>();
		Owner owner1 = new Owner();
		owner1.setId(1L);
		Owner owner2 = new Owner();
		owner2.setId(2L);
		
		returnOwnersSet.add(owner1);
		returnOwnersSet.add(owner2);

		when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

		Set<Owner> owners = ownerSDJpaService.findAll();

		assertNotNull(owners);
		assertEquals(2, owners.size());
	}

	@Test
	void testFindById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = ownerSDJpaService.findById(1L);

        assertNotNull(owner);
	}

	@Test
	void testSave() {
		
		Owner ownerToSave = new Owner();
		
		ownerToSave.setId(1L);
		
        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = ownerSDJpaService.save(ownerToSave);

        assertNotNull(savedOwner);

        verify(ownerRepository).save(any());
	}

	@Test
	void testDelete() {
		
		ownerSDJpaService.delete(returnOwner);
        //default is 1 times
        verify(ownerRepository, times(1)).delete(any());
	}

	@Test
	void testDeleteById() {
		
		ownerSDJpaService.deleteById(1L);

	    verify(ownerRepository).deleteById(anyLong());
	}

	@Test
	void testFindByLastName() {
		when(ownerRepository.findByLastName(anyString())).thenReturn(returnOwner);
		Owner smith = ownerSDJpaService.findByLastName(LAST_NAME);
		assertEquals(LAST_NAME, smith.getLastName());
        verify(ownerRepository).findByLastName(any());
	}
	
	@Test
	void testFindByLastNameLike() {
		List<Owner> owners = new ArrayList<>();
		owners.add(returnOwner);
		when(ownerRepository.findByLastNameLike(anyString())).thenReturn(owners);
		List<Owner> result = ownerSDJpaService.findByLastNameLike(LAST_NAME);
		assertEquals(1,result.size());
		verify(ownerRepository).findByLastNameLike(anyString());
	}

}
