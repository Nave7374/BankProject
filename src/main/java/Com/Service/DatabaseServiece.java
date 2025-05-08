package Com.Service;

import java.util.List;

import Com.entity.Account;
import Com.repository.DatabaseCredentials;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class DatabaseServiece implements DatabaseCredentials {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static DatabaseServiece obj;
	
	static {
		emf = Persistence.createEntityManagerFactory("naveen");
		em=emf.createEntityManager();
		obj = new DatabaseServiece();
	}

	public static DatabaseServiece getInstance() {
		return obj;
	}
	
	@Override
	public Account findbyUsername(String username) {
		String hql = "from Account where username = ?1";
		Query query = em.createQuery(hql);
		query.setParameter(1, username);
		List<Account> a = query.getResultList();
		if(a!=null)System.out.println(a.getFirst());
		try{
			return a==null?null:a.getFirst();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean Save(Account acc) {
		EntityTransaction et = null;
		et=em.getTransaction();
		et.begin();
		em.persist(acc);
		et.commit();
		return true;
	}

	@Override
	public void Update(Account acc) {
		EntityTransaction et=em.getTransaction();
		et.begin();
		em.merge(acc);
		et.commit();
	}
}