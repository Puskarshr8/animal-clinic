package com.spring5app.animalclinic.model.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.validation.Valid;

@Embeddable
public class Contact {
	
	@ElementCollection(targetClass = Phone.class)
	@Valid
	private List<Phone> phone = new ArrayList<>(3);
		
	public List<Phone> getPhone() {
		return this.phone;
	}
	
	public void addPhone(Phone phone) {
		this.phone.add(phone);
	}
	
	public void setPhone(List<Phone> phone)
	{
		this.phone = new ArrayList<>(phone);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		Contact other = (Contact) obj;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}
	@Override
	public String toString() {
		final int maxLen = 5;
		return "Contact [phone=" + (phone != null ? toString(phone, maxLen) : null) + "]";
	}
	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}
}
