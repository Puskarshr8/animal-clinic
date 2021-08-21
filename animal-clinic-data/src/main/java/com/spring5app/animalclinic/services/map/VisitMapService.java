package com.spring5app.animalclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring5app.animalclinic.model.Visit;
import com.spring5app.animalclinic.services.VisitService;

@Service
@Profile({"default","map"})
public class VisitMapService extends AbstractMapService<Visit,Long> implements VisitService {

	@Override
	public Set<Visit> findAll() {
		return super.findAll();
	}

	@Override
	public Visit findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Visit save(Visit t) {
		
		if((t.getPet() == null) || (t.getPet().getId() == null))
		{
			throw new RuntimeException("Invalid Visit Entity.");
		}
		return super.save(t);
	}

	@Override
	public void delete(Visit t) {
		super.delete(t);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteByID(id);
	}

}
