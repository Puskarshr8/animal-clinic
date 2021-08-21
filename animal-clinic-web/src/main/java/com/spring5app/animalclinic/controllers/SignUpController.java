package com.spring5app.animalclinic.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;

import com.spring5app.animalclinic.model.User;
import com.spring5app.animalclinic.model.common.Address;
import com.spring5app.animalclinic.model.common.Phone;
import com.spring5app.animalclinic.services.UserService;

@Controller
@RequestMapping("signUp")
public class SignUpController {

	private final UserService userService;

	public SignUpController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/signUpUser")
	public ModelAndView signUpUser() {
		
		final ModelAndView view = new ModelAndView();
		User user = new User();
		user.getUserInfo().addAddress(new Address());
		user.getUserInfo().getContact().addPhone(new Phone());
		view.setViewName("signUp/register");
		view.addObject(user);
		return view;
	}
	
	@PostMapping("/addAddress")
	public ModelAndView addAddress(@ModelAttribute("user") User user)
	{
		user.getUserInfo().addAddress(new Address());
		final ModelAndView view = new ModelAndView("signUp/register");
		view.addObject(user);
		return view;
	}
	
	@PostMapping("/addContact")
	public ModelAndView addContact(@ModelAttribute("user") User user)
	{
		final ModelAndView view = new ModelAndView("signUp/register");
		user.getUserInfo().getContact().addPhone(new Phone());
		view.addObject(user);
		return view;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		
		final ModelAndView view = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			view.setViewName("signUp/register");
			view.addObject(user);
			return view;
		}
		
		User existingUser = userService.findByUserName(user.getUserName());
		
		if(existingUser != null)
		{
			user.setId(existingUser.getId());
		}
		view.addObject(userService.save(user));
		view.setViewName("signUp/complete");
		return view;
	}
}
