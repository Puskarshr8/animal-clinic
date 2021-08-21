package com.spring5app.animalclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.spring5app.animalclinic.model.Owner;

class OwnerMapServiceTest {
	
	OwnerMapService ownerMapService;
	static final Long OWNER_ID = 1L;
	static final String LAST_NAME = "Smith";

	@BeforeEach
	void setUp() throws Exception {
		
		ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
		Owner owner = new Owner();
		owner.setId(OWNER_ID);
		owner.setLastName(LAST_NAME);
		ownerMapService.save(owner);
	}

	@Test
	void testFindByLastName() {
		Owner owner = ownerMapService.findByLastName(LAST_NAME);
		assertNotNull(owner);
		assertEquals(OWNER_ID,owner.getId());
	}

	@Test
	void testFindAll() {
		Set<Owner> owners = ownerMapService.findAll();
		assertEquals(1, owners.size());
	}

	@Test
	void testFindByIdID() {
		Owner owner = ownerMapService.findById(OWNER_ID);
		assertEquals(OWNER_ID, owner.getId());
	}

	@Test
	void testSaveExistingID() {
		Long existingID = 2L;
		Owner owner = new Owner();
		owner.setId(existingID);
		ownerMapService.save(owner);
		assertEquals(existingID, owner.getId());
	}

	@Test
	void testDeleteByID() {
		ownerMapService.deleteByID(OWNER_ID);
		assertNull(ownerMapService.findById(OWNER_ID));
	}

	@Test
	void testDeleteT() {
		ownerMapService.delete(ownerMapService.findById(OWNER_ID));
		assertNull(ownerMapService.findById(OWNER_ID));
	}

}
