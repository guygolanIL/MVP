package boot;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import presenter.Properties;

public class HibernateTest {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setDefaults();
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
//		
//		Configuration cfg = new Configuration();
//		 
//		 SessionFactory sf;
//		 sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
//
//		 Session session = sf.openSession();
	        // begin transaction
		// session.beginTransaction();
		 session.saveOrUpdate(prop);

	}

}
