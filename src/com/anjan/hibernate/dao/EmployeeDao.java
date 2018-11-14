package com.anjan.hibernate.dao;

import java.util.List;

import com.anjan.hibernate.bean.EmployeeBean;

public interface EmployeeDao {

	public void saveEmployee(EmployeeBean eb);
	public void updateEmployee(EmployeeBean eb);
	public void deleteEmployee(EmployeeBean eb);
	public EmployeeBean getBeanById(int id);
	public List<EmployeeBean> getAllEmployee();
	
	//Named Query
	public EmployeeBean getEmployeeByIdHQL(int id);
	public List<EmployeeBean> getAllEmployeeHQL();
	public List<Object[]> getAllEmployeeSQL();
	public List<Object[]> getEmployeeByIdSQL(int id);
	
}
