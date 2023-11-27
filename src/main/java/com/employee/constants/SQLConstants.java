package com.employee.constants;

public class SQLConstants {
	//id , f_name ,dept_name,salary long,address
	
	
public static final String ADD_QUERY="insert into listemployees (id,f_name,dept_name,salary,address)values(?,?,?,?,?)";

public static final String LIST_EMPLOYEE="select id,f_name,dept_name,salary,address from listemployees LIMIT ? OFFSET ?"; 

public static final String GET_EMPLOYEE = "select id ,f_name,dept_name,salary,address from listemployees where id=?";

public static final String UPDATE_EMPLOYEE = "update listemployees set f_name=? ,dept_name=? ,salary=? ,address=?  where id=?";

public static final String DELETE_EMPLOYEE = "delete from listemployees where id=?";

public static final String SEARCH_EMPLOYEE ="select id ,f_name,dept_name,salary,address from listemployees where id!='0'";

public static final String GET_USER = "select username, password from emp_profile where username = ?";

public static final String INSERT_USER = "Insert into emp_profile (username, password) values(?, ?)";

public static final String GET_ROLES = "select role_name from emp_role";

public static final String GET_ROLES_FOR_USER = "select role from emp_role_map where username = ?";

public static final String INSERT_USER_ROLE = "Insert into emp_role_map (username, role) values(?, ?)";

public static final String GET_USER_SUBACTION="select rsm.sub_action from emp_profile e inner join emp_role_map erp on erp.username = e.username inner join roles_subaction_map rsm on rsm.role = erp.role where e.username=?;";

public static final String LIST_STUDENTS_COUNT = "select count(1) as total from listemployees";


}	
