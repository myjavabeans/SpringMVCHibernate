package com.anjan.servlet;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anjan.hibernate.bean.EmployeeBean;
import com.anjan.hibernate.bo.EmployeeBeanBO;

@Controller
public class MVCServlet {

	private EmployeeBeanBO employeeBeanBO;

	@Autowired(required = true)
	@Qualifier(value = "employeeBo")
	public void setPersonService(EmployeeBeanBO employeeBeanBO) {
		this.employeeBeanBO = employeeBeanBO;
	}

	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String defaultPage(Model model, Locale locale) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		
		return "index";
	}

	@RequestMapping()
	public String displayEmployeeData(@Validated EmployeeBean empBean, Model model) {

		
		
		EmployeeBean bean = employeeBeanBO.getBeanById(2);
		model.addAttribute("message", "Hello " + bean.getName());
		model.addAttribute("myName", empBean.getName());

		return "empData";
	}
}
