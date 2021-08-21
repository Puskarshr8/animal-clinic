package com.spring5app.animalclinic.rest.mapper;

public interface Mapper<T,F> {
	
	public T mapTo(F f);
	
	public F mapFrom(T t);

}
