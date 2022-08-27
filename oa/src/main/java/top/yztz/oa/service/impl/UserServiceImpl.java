package top.yztz.oa.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.yztz.oa.bean.News;
import top.yztz.oa.bean.User;
import top.yztz.oa.service.UserService;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(User user){
		entityManager.persist(user);
	}
	
	@Override
	public void delete(long id){
		User obj = entityManager.find(User.class, id);
		if(obj != null)
			entityManager.remove(obj);
	}

	@Override
	public void deleteBatch(List<Long> ids) {
		entityManager.createQuery("delete from User u where u.id in ?1")
				.setParameter(1, ids).executeUpdate();
	}

	@Override
	public void update(User user){
		entityManager.merge(user);
	}
	
	@Override
	public User getById(long id){
		return entityManager.find(User.class, id);
	}
	
//	@Override
//	public User getByUserId(String userId) {
//		return (User) entityManager.createQuery("from User u where u.userId=?")
//				.setParameter(0, userId).getSingleResult();
//	}
	
	@Override
	public List<User> getAllRecords(){
		return entityManager.createQuery("from User u order by u.id desc").getResultList();
	}
	
	@Override
	public List<User> getPageRecord(int pageIndex, int pageSize){
		if(pageIndex <= 0) return null;

		return entityManager.createQuery("from User u order by u.id asc")
				.setFirstResult((pageIndex - 1) * pageSize)
				.setMaxResults(pageSize).getResultList();
	}
	
	@Override
	public List<User> queryByName(String userName, int pageIndex, int pageSize) {
		return entityManager.createQuery("from User u where u.userName like ?0 order by u.id desc")
				.setParameter(0, "%"+userName+"%")
				.setFirstResult((pageIndex - 1) * pageSize)
				.setMaxResults(pageSize).getResultList();
	}
	
	@Override
	public String getRecordSize(){
		long size = (Long) entityManager.createQuery("select count(*) from User").getSingleResult();
		return String.valueOf(size);
	}
	
	@Override
	public String querySizeByName(String userName) {
		long size = (Long) entityManager.createQuery("select count(*) from User u where u.userName like ?0")
				.setParameter(0, "%"+userName+"%").getSingleResult();
		return String.valueOf(size);
	}

	@Override
	public User getByUserNameAndPassword(String userName, String password) {

		List<User> userList = entityManager.createQuery("from User u where u.userName = :name and u.password = :pwd")
				.setParameter("name", userName)
				.setParameter("pwd", password).getResultList();

		if(!CollectionUtils.isEmpty(userList)){
			return userList.get(0);
		}else{
			return null;
		}

	}

	@Override
	public User getByAccountAndPassword(String account, String password) {
		

		List<User> userList = entityManager.createQuery("from User u where u.account = ?0 and u.password = ?1")
				.setParameter(0, account)
				.setParameter(1, password).getResultList();
		if(!CollectionUtils.isEmpty(userList)){
			return userList.get(0);
		}else{
			return null;
		}

	}

	@Override
	public List<User> queryByPhone(String phone) {
		return entityManager.createQuery("from User u where u.phone like ?0")
				.setParameter(0, "%"+phone+"%").getResultList();
	}

	@Override
	public List<User> queryByName(String userName) {
		return entityManager.createQuery("from User u where u.userName like ?0")
				.setParameter(0, "%"+userName+"%").getResultList();
	}

	@Override
	public List<User> queryByNameAndPhone(String name, String phone) {
		return entityManager.createQuery("from User u where u.userName like ?0 and u.phone like ?1")
				.setParameter(0, "%"+name+"%")
				.setParameter(1, "%"+phone+"%").getResultList();
	}

	

	

	
}
