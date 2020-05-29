package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CarRepository carRepository;

	@RequestMapping("/")
	public String listCars(Model model) {
		model.addAttribute("cars", carRepository.findAll());
		//model.addAttribute("car", carRepository.findById(id).get());

		return "index";
	}

	@GetMapping("/addCategory")
	public String catagoryForm(Model model) {
		model.addAttribute("category", new Category());
		return "categoryForm";
	}
	@PostMapping("/processCat")
	public String processCat(Category category) {
		categoryRepository.save(category);
		return "redirect:/addCar";
	}

	@GetMapping("/addCar")
	public String carForm(Model model) {
		model.addAttribute("car", new Car());
		model.addAttribute("allCategory", categoryRepository.findAll());
		return "carForm";
	}

	@PostMapping("/processCar")
	public String processCar(Car car) {
		carRepository.save(car);
		return "redirect:/";
	}

	@RequestMapping("/update/{id}")
	public String updateCar(@PathVariable("id") long id, Model model){
		model.addAttribute("car", carRepository.findById(id).get());
		return "redirect:/addCar";
	}

	@RequestMapping("/delete/{id}")
	public String deleteCategory(@PathVariable("id") long id){
		Category category = categoryRepository.findById(id).get();
		categoryRepository.delete(category);
		return "redirect:/";
	}

	@RequestMapping("/deleteCar/{id}")
	public String deleteCar(@PathVariable("id") long id) {
		carRepository.deleteById(id);
		return "redirect:/";
	}

	@RequestMapping("/detail/{id}")
	public String showCourse(@PathVariable("id") long id, Model model) {
		model.addAttribute("car", carRepository.findById(id).get());
		return "detail";
	}





}























