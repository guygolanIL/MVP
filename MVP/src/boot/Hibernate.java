package boot;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import presenter.Properties;

public class Hibernate {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setDefaults();
		 Configuration cfg = new Configuration();
		 SessionFactory sf = cfg.configure().buildSessionFactory();
		 Session session = sf.openSession();
	        // begin transaction
		 session.beginTransaction();
		 session.saveOrUpdate(prop);

	}

}
