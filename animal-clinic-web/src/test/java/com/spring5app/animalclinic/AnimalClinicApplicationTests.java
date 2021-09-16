package com.spring5app.animalclinic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@IfProfileValue(name ="spring.profiles.active", value ="local-dev")
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
public class AnimalClinicApplicationTests {

	@Test
	public void contextLoads() {
	}

}
