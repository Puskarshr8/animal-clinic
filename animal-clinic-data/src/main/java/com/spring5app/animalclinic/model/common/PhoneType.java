package com.spring5app.animalclinic.model.common;

import javax.persistence.EntityNotFoundException;

public enum PhoneType {
	
	WORK(1,"Work"),
	PERSONAL(2,"Personal"),
	HOME(3,"Home");
	
	private int id;
	private String name;
	
	PhoneType(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public static PhoneType getPhoneType(int value) throws EntityNotFoundException
	{
		for(PhoneType type : PhoneType.values())
		{
			if(type.id == value)
				return type;
		}
		
		throw new EntityNotFoundException("No Phone type found for " + value);
	}
}
