package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Country;
import com.example.demo.repo.CountryRepo;

@Service 
public class CountryService {

	@Autowired
	CountryRepo cRepo;
	
	public Iterable<Country> getCountryList()
	{
		return cRepo.findAll();
	}
	public void deleteCountry(int id)
	{
		cRepo.deleteById(id);
	}
	
	public Country getCountry(int id)
	{
		return cRepo.findById(id).get();
	}
	
	public void saveCountry(Country c)
	{
		cRepo.save(c);
	}
}
