package com.employee.model;

public class Employee {

	private long id;
	private String name;
	private String Dept_name;
	private long salary;
	private String address;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept_name() {
		return Dept_name;
	}
	public void setDept_name(String dept_name) {
		Dept_name = dept_name;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", Dept_name=" + Dept_name + ", salary=" + salary
				+ ", address=" + address + "]";
	}
	
	public boolean isValidForSearch() {
		if(id == 0 && (name ==null || name.isEmpty()) 
				&& (Dept_name ==null || Dept_name.isEmpty()) && salary == 0 
			&&( address==null || address.isEmpty()))
			
			return false;
		else 
			return true;
	}
}
