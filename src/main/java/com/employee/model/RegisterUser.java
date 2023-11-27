package com.employee.model;
public class RegisterUser extends User{

	private String confirmPassword;

	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public boolean isSamePassword() {
		if(confirmPassword != null && confirmPassword.equals(this.getPassword())) {
			return true;
		}
		return false;
	}
	public boolean isValidPassword() {
		if(this.getPassword().length() >= 8 
			&&this.getPassword().matches(".*[A-Z].*")
			&&this.getPassword().matches(".*[a-z].*")
			&&this.getPassword().matches(".*\\d.*")
			&&this.getPassword().matches(".*[!@#$%^><?|/?].*"))
		{
			return true;
		}
		return false;
	}
}
 