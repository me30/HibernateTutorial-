package com.commons.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.commons.entity.UserDetail;

public class HibernateMain {

	public static void main(String[] arg) {

		
		UserDetail user = new UserDetail();
		user.setUserName("Test User");
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
			
		session.save(user);
		
		//fetch user 
		user = (UserDetail) session.get(UserDetail.class, 1);
		
		session.getTransaction().commit();
		session.close();
		

		//other session
		//we only write update - session.getTransaction().commit();
		//then its execute update query .. but its not hit db coz we use - @org.hibernate.annotations.Entity(selectBeforeUpdate = true)
		session = sf.openSession();
		session.beginTransaction();
		
		session.update(user);
		
		session.getTransaction().commit();
		session.close();
		
	}
}
