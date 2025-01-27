package com.spring.employee.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.employee.employeemodel.EmployeeModel;
import com.spring.employee.entity.EmployeeEntity;
import com.spring.employee.service.EmployeeService;


@Controller
public class EmployeeController
{
	@Autowired
	EmployeeService employeeService;
	
    
    @GetMapping ("/Employeeform")
	 public String getEmployeeForm()
	 {
        return "employee";
	 }
		  
  
	@PostMapping ("/submit_employee")
	public String sendEmployee(EmployeeModel employeeModel)
    {
		employeeService.saveEmployeeData(employeeModel);
	    return "done";
    }
	
	@GetMapping("/getallemployee")
	public String getallemployee(Model model)
	{
		List<EmployeeEntity> employee= employeeService.getallemployee();
		model.addAllAttributes(employee);
		
		return "EmployeeDetails";
	}
	
	@GetMapping("/getsearchform")
	public String getsearchform()
	{
		return "search";
	}
	
	@PostMapping("/searchbyid")
	public String searchbyid(@RequestParam int id, Model model)
	{
		EmployeeEntity employee = employeeService.searchbyid(id);
		
		model.addAttribute("Employee",employee);
		return "search";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteemployeebyid(@PathVariable int id)
	{
		employeeService.deletebyid(id);
		return "redirect:/getallemployee";
	}
	
}
