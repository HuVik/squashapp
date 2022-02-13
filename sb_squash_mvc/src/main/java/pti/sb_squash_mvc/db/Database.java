package pti.sb_squash_mvc.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import pti.sb_squash_mvc.model.User;

public class Database {
	
	private SessionFactory sessionFactory;

    public Database(){

    	StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public User getUserById(int userId){

        User user = null;

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        user = session.get(User.class,userId);

        session.getTransaction().commit();
        session.close();

        return user;
    }





    public void close(){
        sessionFactory.close();
    }

}
