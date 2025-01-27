package com.spring.employee.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Employee")
public class EmployeeEntity 
{
	@Id
	private int id;
    private String name;
    private long phoneno;
    private String gender;
    private String doj;
    private int deptno;
    private double salary;
    private double da;
    private double hra;
    private double totalsalary;
	
}
