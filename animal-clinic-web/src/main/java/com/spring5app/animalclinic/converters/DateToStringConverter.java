package com.spring5app.animalclinic.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;

public class DateToStringConverter implements Converter<LocalDate, String>{

	@Override
	public String convert(LocalDate source) {
		
		if(source == null)
		{
			return null;
		}
		
		return source.format(DateTimeFormatter.ISO_DATE);
	}
}
