package com.spring5app.animalclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import com.spring5app.animalclinic.model.BaseEntity;

public abstract class AbstractMapService<T extends BaseEntity,ID extends Long> {
	
	protected Map<Long, T> map = new HashMap<>();
	
	Set<T> findAll(){
		return new HashSet<>(map.values());
	}
	
	T findById(ID id)
	{
		return map.get(id);
	}
	
	T save(T object)
	{
		if(object != null)
		{
			if(object.getId() == null)
			{
				object.setId(getNextId());
			}
			map.put(object.getId(),object);			
		}
		else
		{
			throw new RuntimeException("Object save cannot be null");
		}

		return object;
	}
	
	void deleteByID(ID id)
	{
		map.remove(id);
	}
	
	void delete(T object)
	{
		map.entrySet().removeIf(entry -> entry.getValue().equals(object));
	}
	
	private Long getNextId()
	{
		try
		{
			return Collections.max(map.keySet()) + 1;
		}
		catch(NoSuchElementException e)
		{ 
			return 1L;
		}
	}
	
	
	

}
