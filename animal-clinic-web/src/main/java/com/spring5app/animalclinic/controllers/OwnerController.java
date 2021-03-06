package com.spring5app.animalclinic.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring5app.animalclinic.model.Owner;
import com.spring5app.animalclinic.services.OwnerService;

@RequestMapping("owners")
@Controller
public class OwnerController {
	
	private final OwnerService ownerService;
	
	
	public OwnerController(OwnerService ownerService)
	{
		this.ownerService = ownerService;
	}
	
	@GetMapping("/new")
	public String initCreationForm(Model model)
	{
		model.addAttribute(new Owner());
		return "owners/createOrUpdateOwnerForm";
	}
	
	 @GetMapping("/{ownerId}/edit")
	 public String initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model)
	 {
		 model.addAttribute(ownerService.findById(ownerId));
		 
		 return "owners/createOrUpdateOwnerForm";		 
	 }
	
	@PostMapping("/new")
	public String processCreationOwnerForm(@Valid Owner owner, BindingResult result)
	{
		if(result.hasErrors())
		{
			return "owners/createOrUpdateOwnerForm";
		}
		else
		{
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}
	
	@PostMapping("/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId)
	{
		if(result.hasErrors())
		{
			return "owners/createOrUpdateOwnerForm";
		}
		else
		{
			owner.setId(ownerId);
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId(); 
		}	
	}
	
	
	@RequestMapping({"","/index","/owners/index"})
	public String listOwners(Model model)
	{
		model.addAttribute("owners", ownerService.findAll());
		return "owners/index";		
	}
	
	@RequestMapping({"/find"})
	public String findOwners(Model model)
	{
		model.addAttribute("owner", new Owner());
		return "owners/findOwners";
	}
	
	 @GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId)
	{
		ModelAndView mav = new ModelAndView("owners/OwnerDetails");
		mav.addObject(ownerService.findById(ownerId));
		return mav;
	}
	 
	 @GetMapping("/owners")
	 public String processFindForm(Owner owner, BindingResult result, Model model)
	 {
		 if(owner.getLastName() == null)
		 {
			 owner.setLastName("");
		 }
		 
		 List<Owner> owners = ownerService.findByLastNameLike("%"+ owner.getLastName() +"%");
		 
		 if(owners.isEmpty())
		 {
			 result.rejectValue("lastName", "notFound", "not found");
			 return "owners/findOwners";
		 }else if (owners.size() == 1) {
	            // 1 owner found
	            owner = owners.get(0);
	            return "redirect:/owners/" + owner.getId();
	        } else {
	            // multiple owners found
	            model.addAttribute("selections", owners);
	            return "owners/ownersList";
	        }
		 
	 }
}
