package com.spring5app.animalclinic.model.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

@Embeddable
public class Phone {
	
	@Column(name = "phoneNumber", length = 20, nullable = false)
	@Size(min=1)
	@Size(max=20)
	private String number;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "phoneType", length = 40)
	private PhoneType type = PhoneType.HOME;
	
	public Phone()
	{
		
	}
	
	public Phone(String number, PhoneType type)
	{
		this.number = number;
		this.type = type;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}

	public void setType(PhoneType type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}
	
	public PhoneType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phone other = (Phone) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Phone [number=" + number + ", type=" + type + "]";
	}
}
