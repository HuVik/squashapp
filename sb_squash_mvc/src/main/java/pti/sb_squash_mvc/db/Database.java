package pti.sb_squash_mvc.db;


import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import pti.sb_squash_mvc.model.Roles;
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
    
    
    public User getUserByNameAndPwd(String userName, String userPwd) {
    	User user = null;
    	
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	
    	Query selectQuery = session.createQuery("SELECT u FROM User u WHERE u.name=:userName AND u.pwd=:userPwd",User.class);
    	selectQuery.setParameter("userName", userName);
    	selectQuery.setParameter("userPwd", userPwd);
    	List<User> users = selectQuery.getResultList(); 
    	
    	
    	if(!users.isEmpty()) {
    		user = users.get(0);
    	}
    	
    	session.getTransaction().commit();
    	session.close();
		return user;
	}

    
    public void saveUser(User user) {
    	
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.save(user);
    	
    	session.getTransaction().commit();
    	session.close();
    	
    	
    }
    
    public void updateUser(User user) {
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.update(user);
    	
    	session.getTransaction().commit();
    	session.close();
    }
    

    


    public void close(){
        sessionFactory.close();
    }

	

}
