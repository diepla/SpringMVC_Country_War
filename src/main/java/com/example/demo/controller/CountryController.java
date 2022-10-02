package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Country;
import com.example.demo.service.CountryService;

import java.util.*;

@Controller
public class CountryController {
	
	@Autowired
	CountryService cServ;

	@RequestMapping("/")
	public String listCountry(Model model)
	{
		Iterable<Country> list = cServ.getCountryList();
		model.addAttribute("country_list", list);
		return "index";
	}
	
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public String saveCountry(@ModelAttribute("country") Country country, Model model)
	{
		cServ.saveCountry(country);
		Iterable<Country> list = cServ.getCountryList();
		model.addAttribute("country_list", list);
		return "index";
	}
	
	@RequestMapping(value="back")
	public String goBack(Model model)
	{
		Iterable<Country> list = cServ.getCountryList();
		model.addAttribute("country_list", list);
		return "index";
	}
	
	@RequestMapping("/update/{id}")
	public ModelAndView editCountry(@PathVariable(name="id") int id)
	{
		Country c = cServ.getCountry(id);
		ModelAndView mv = new ModelAndView("edit_country");
		mv.addObject("country", c);
		return mv;
		
	}
	
	@RequestMapping("/view/{id}")
	public ModelAndView viewCountry(@PathVariable (name="id") int id)
	{
		Country c = cServ.getCountry(id);
		ModelAndView model = new ModelAndView("view_country");
		model.addObject("country", c);
		return model;
		
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteCountry(@PathVariable(name="id") int id)
	{
		cServ.deleteCountry(id);
		return "redirect:/";
	}
	
	@RequestMapping("/create")
	public String createCountry(Model model)
	{
		Country c = new Country();
		model.addAttribute("country", c);
		return "create_new";
	}
}

