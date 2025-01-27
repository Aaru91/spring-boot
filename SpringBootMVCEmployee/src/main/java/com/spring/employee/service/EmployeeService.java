package com.spring.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.employee.employeemodel.EmployeeModel;
import com.spring.employee.entity.EmployeeEntity;
import com.spring.employee.repository.EmployeeRepository;

@Service
public class EmployeeService
{
	@Autowired
    EmployeeRepository employeeRepository;

    public void saveEmployeeData(EmployeeModel employeemodel) 
    {
        
        double da = 0.2 * employeemodel.getSalary();
        double hra = 0.3 * employeemodel.getSalary();
        double totalsalary = da + hra + employeemodel.getSalary();

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employeemodel.getId());
        employeeEntity.setName(employeemodel.getName());
        employeeEntity.setPhoneno(employeemodel.getPhoneno());
        employeeEntity.setGender(employeemodel.getGender());
        employeeEntity.setDoj(employeemodel.getDoj());
        employeeEntity.setDeptno(employeemodel.getDeptno());
        employeeEntity.setSalary(employeemodel.getSalary());
        employeeEntity.setDa(da);
        employeeEntity.setHra(hra);
        employeeEntity.setTotalsalary(totalsalary);

        employeeRepository.save(employeeEntity);
    }

	 public List<EmployeeEntity> getallemployee()
	 {
		 List<EmployeeEntity> employee = employeeRepository.findAll();
		 return employee;
	 }
	 
	 
	 public EmployeeEntity searchbyid(int id)
	 {
		 Optional<EmployeeEntity> optionaldata = employeeRepository.findById(id);
		 
		 if(optionaldata.isPresent())
		 {
			 EmployeeEntity employee = optionaldata.get();
			 return employee;
		 }
		 else
		 {
			 return null;
		 }
	 }
	 public void deletebyid (int id)
	 {
		 employeeRepository.deleteById(id);
	 }
}

