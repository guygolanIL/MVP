package boot;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import presenter.Properties;

public class PropertiesGenerate {

	public static void main(String[] args) throws FileNotFoundException {
	
		Properties p = new Properties();
		p.setMaxThreads(10);
		p.setMazeMaxAxisX(15);
		p.setMazeMaxAxisY(15);
		p.setMazeMaxAxisZ(15);
		p.setSolveAlgorithm("BFS");
		p.setGenerateAlgorithm("MyMaze3dGenerator");
		
//		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		session.save(p);
//		session.getTransaction().commit();
		
		
		FileOutputStream file;
		try {
			file = new FileOutputStream("properties.xml");
			java.beans.XMLEncoder xe1 = new java.beans.XMLEncoder(file);
			xe1.writeObject(p);
			xe1.flush();
			xe1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
