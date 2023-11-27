package com.employee.util;

import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.dao.EmployeeDao;
import com.employee.dao.EmployeeDaoImpl;
import com.employee.model.Employee;

public class CommonUtil {

	public static String encodePassword(String password) { 

        String encryptedpassword = null; 

        try   
        {  
            MessageDigest m = MessageDigest.getInstance("MD5");    
            m.update(password.getBytes());  
                
            byte[] bytes = m.digest();  
               
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
 
            encryptedpassword = s.toString();  
        }   
        catch (NoSuchAlgorithmException e)   
        {  
            System.out.println(e.getMessage());
        }  
          
   
        return encryptedpassword; 
	}
	
	public static boolean matches(String password, String enctyptedPassword) {
		return enctyptedPassword.equals(encodePassword(password));
	}
	
	public static boolean verifyAction(HttpServletRequest request, HttpServletResponse response) {
		
		List<String> subActions = (List<String>) request.getSession().getAttribute("subActions");
		
		String subAction = request.getParameter("subAction");
		
		String SUCCESS_VIEW = "commonemp/error.jsp";
		for(String s: subActions) {
			if(s.equals(subAction))
				return true;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(SUCCESS_VIEW);
		try {
			view.forward(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		return false;
		
	}
	
	
//	private static void generateCsvFile()
//    {
//           try
//           {
//        	    String fileName = "EmployeeList.csv";
//        	    EmployeeDao employeeDao = new EmployeeDaoImpl();
//        	    List<Employee> employees = employeeDao.getEmployees();
//                FileWriter writer = new FileWriter(fileName);
//
//                writer.append("Email");
//                writer.append(',');
//                writer.append("Name");
//                writer.append('\n');
//
////                for (User user in users) {
////                     writer.append(user.getEmail());
////                     writer.append(',');
////                     writer.append(user.getName());
////                     writer.append('\n');
////                }
//
//                writer.flush();
//                writer.close();
//           } catch(IOException e) {
//                 e.printStackTrace();
//           } 
//      }
	
}


