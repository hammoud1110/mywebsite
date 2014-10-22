package com.mywebsite.models;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.mywebsite.entity.HibernateUtil;

public class ReminderBeanTest {

	public static ReminderBean getUserById(int id) {
		Session session = null;

		ReminderBean r = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			r = (ReminderBean) session.load(ReminderBean.class, id);
			Hibernate.initialize(r);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return r;
	}

	public static void main(String[] args) {
		ReminderBean r = new ReminderBean();
		ReminderBean r2 = new ReminderBean();
		ReminderBean r1 = new ReminderBean();
		r1.setTfName("houla");
		r.setTfName("Hammouda");
		r2.setTfName("Haitham");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.save(r);
		session.save(r1);
		session.save(r2);
		session.getTransaction().commit();
		session.close();
//		System.out.println(getUserById(1).getTfName());
	}

}
