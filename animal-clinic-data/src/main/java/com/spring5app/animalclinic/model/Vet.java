package com.spring5app.animalclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="vets")
public class Vet extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="vet_specialties", joinColumns = @JoinColumn(name="vet_id"), inverseJoinColumns = @JoinColumn(name="specialty_id")
	)
	private Set<Speciality> specialities = new HashSet<>();

	public Set<Speciality> getSpecialities() {
		return specialities;
	}

	public void setSpecialties(Set<Speciality> specialties) {
		this.specialities = specialties;
	}
}
