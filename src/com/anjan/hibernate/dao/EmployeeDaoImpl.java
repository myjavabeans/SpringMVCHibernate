package com.anjan.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.anjan.hibernate.bean.EmployeeBean;

/**
 * DAO Class
 * 
 * @author Anjan
 *
 */

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveEmployee(EmployeeBean eb) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(eb);
		tx.commit();
		session.close();
	}

	@Override
	public void updateEmployee(EmployeeBean eb) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(eb);
		tx.commit();
		session.close();
	}

	@Override
	public void deleteEmployee(EmployeeBean eb) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(eb);
		tx.commit();
		session.close();
	}

	@Override
	public EmployeeBean getBeanById(int id) {

		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(EmployeeBean.class);
		criteria.add(Restrictions.eq("id", id));

		EmployeeBean eb = (EmployeeBean) criteria.uniqueResult();

		session.close();
		return eb;

	}

	@Override
	public List<EmployeeBean> getAllEmployee() {

		Session session = sessionFactory.openSession();
		List<EmployeeBean> list = session.createQuery("from EmployeeBean").list();
		session.close();
		return list;

	}

	@Override
	public EmployeeBean getEmployeeByIdHQL(int id) {

		Session session = sessionFactory.openSession();

		Query query = session.getNamedQuery("HQL_GET_EMPLOYEE_BY_ID");
		query.setInteger("id", 3);

		EmployeeBean eb = (EmployeeBean) query.uniqueResult();

		session.close();

		return eb;
	}

	@Override
	public List<EmployeeBean> getAllEmployeeHQL() {

		Session session = sessionFactory.openSession();

		Query query = session.getNamedQuery("HQL_GET_ALL_EMPLOYEE");
		List<EmployeeBean> list = query.list();

		session.close();

		return list;
	}

	@Override
	public List<Object[]> getAllEmployeeSQL() {

		Session session = sessionFactory.openSession();

		Query query = session.getNamedQuery("SQL_GET_ALL_EMPLOYEE");
		List<Object[]> list = query.list();
		
		session.close();

		return list;

	}

	@Override
	public List<Object[]> getEmployeeByIdSQL(int id) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.getNamedQuery("SQL_GET_EMPLOYEE_BY_ID");
		query.setInteger("id", id);
		List<Object[]> list = query.list();
		
		session.close();
		
		return list;
	}

}
