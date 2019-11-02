package com.qa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.model.User;

@Transactional(value = TxType.SUPPORTS)
public class UserRepoDB implements UserRepo {

	@PersistenceContext(unitName = "myPU")
	private EntityManager em;
	
	@Transactional(value = TxType.REQUIRED)
	public User createUser(User user) {
		user.setUserName();
		em.persist(user);
		return user;
	}

	public User readUser(int id) {
		User user = em.find(User.class, id);
		return user;
	}

	public User readUser(String userName) {
		TypedQuery<User> tQ = em.createQuery("select user from User user where userName = '" + userName + "'", User.class);
		User user = tQ.getSingleResult();
		return user;
	}
	
	public List<User> readAll() {
		TypedQuery<User> q = em.createQuery("Select use from User use", User.class);
		List<User> list = q.getResultList();
		return list;
	}

	@Transactional(value = TxType.REQUIRED)
	public User updateUser(int id, User newUser) {
		User user = readUser(id);
		user.setAll(newUser);
		return user;
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void deleteUser(int id) {
		em.remove(readUser(id));
	}

}
