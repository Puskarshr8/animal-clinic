package com.spring5app.animalclinic.model.common;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;

@Embeddable
public class UserInfo {
	
	@Embedded
	private Name name = new Name();
	
	@ElementCollection(targetClass = Address.class)
	@Valid
	private List<Address> address = new ArrayList<>(3);
	
	@Embedded
	@Valid
	private Contact contact = new Contact();
	
	@Column(name = "dateOfBirth", columnDefinition = "DATE")
	private LocalDate dateOfBirth;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sex", length = 40)
	private Sex sex;

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
	
	public List<Address> getAddress()
	{
		return this.address;
	}
	
	public void setAddress(List<Address> address)
	{
		this.address = new ArrayList<>(address);
	}

	public void addAddress(Address address) {		
		
		this.address.add(address);
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}	

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
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
		UserInfo other = (UserInfo) obj;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.equals(other.contact))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sex != other.sex)
			return false;
		return true;
	}

	@Override
	public String toString() {
		final int maxLen = 5;
		return "UserInfo [name=" + name + ", address=" + (address != null ? toString(address, maxLen) : null)
				+ ", contact=" + contact + ", dateOfBirth=" + dateOfBirth + ", sex=" + sex + "]";
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
