package pti.sb_squash_mvc.db;


import java.util.List;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import pti.sb_squash_mvc.model.Location;
import pti.sb_squash_mvc.model.Match;
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
    
    public List<User> getAllUser(){
    	
    	Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query selectQuery = session.createQuery("SELECT u FROM User u",User.class);
    	List<User> users = selectQuery.getResultList(); 
    	
    	
    	
        session.getTransaction().commit();
        session.close();
    	
    	return users;
    }
    
    public Location getLocationById(int locationId) {
    	
    	Location location = null;
    	
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	location = session.get(Location.class, locationId);
    	
    	session.getTransaction().commit();
    	session.close();
    	
    	return location;
    }
    
    public Location getLocationByAddress(String address) {
    	
    	Location location = null;
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	
    	Query selectQuery = session.createQuery("SELECT l FROM Location l WHERE l.address=:address", Location.class);
    	selectQuery.setParameter("address", address);
    	List<Location> locations = selectQuery.getResultList();
    	
    	if(!locations.isEmpty()) {
    		location = locations.get(0);
    	}
    	
    	session.getTransaction().commit();
    	session.close();
    	
    	return location;
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
    
    public List<Match> getAllMatch() {
    	
    	Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query selectQuery = session.createQuery("SELECT m FROM Match m",Match.class);
    	List<Match> matches = selectQuery.getResultList(); 
    	
    	
    	
        session.getTransaction().commit();
        session.close();
    	
    	
    	return matches;
    }

    
    public void saveUser(User user) {
    	
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.save(user);
    	
    	session.getTransaction().commit();
    	session.close();
    	
    	
    }
    
    public void saveLocation(Location location) {
    	
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.save(location);
    	
    	session.getTransaction().commit();
    	session.close();
    	
    }
    
    public void saveMatch(Match match) {
    	
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.save(match);
    	
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
    
    
    

    public List<Match> getAllMacthByDate(){
    	
    	Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query selectQuery = session.createQuery("SELECT m FROM Match m ORDER BY date DESC",Match.class);
    	List<Match> matches = selectQuery.getResultList(); 
    	
    	
    	
        session.getTransaction().commit();
        session.close();
    	
    	
    	return matches;
    	
    	
    }


    public void close(){
        sessionFactory.close();
    }

	

}
