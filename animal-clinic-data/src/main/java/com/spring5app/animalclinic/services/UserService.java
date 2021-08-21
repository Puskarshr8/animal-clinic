package com.spring5app.animalclinic.services;

import com.spring5app.animalclinic.model.User;

public interface UserService extends CrudService<User,Long>{
	
	User findByUserName(String userName); 

}
