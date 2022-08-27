package top.yztz.oa.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yztz.oa.bean.Category;
import top.yztz.oa.bean.User;
import top.yztz.oa.service.CategoryService;
import top.yztz.oa.service.NewsService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private NewsService newsService;
//	@Resource private SessionFactory sessionFactory;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(Category category){
		entityManager.persist(category);
	}
	
	@Override
	public void delete(long id){
		Category obj = entityManager.find(Category.class, id);
		if(obj != null)
			entityManager.remove(obj);
		newsService.deleteByCID(id);
	}
	
	@Override
	public void update(Category category){
		entityManager.merge(category);
	}
	
	@Override
	public Category getById(long id){
		return entityManager.find(Category.class, id);
	}

	@Override
	public List<Category> getByName(String name, int pageIndex, int pageSize) {
		return entityManager.createQuery("from Category c where c.name like ?0 order by u.id desc")
				.setParameter(0, "%"+name+"%")
				.setFirstResult((pageIndex - 1) * pageSize)
				.setMaxResults(pageSize).getResultList();
	}

	@Override
	public List<Category> getByName(String name) {
		return entityManager.createQuery("from Category c where c.name like ?0")
				.setParameter(0, "%"+name+"%").getResultList();
	}

	public List<Category> getAll() {
		return entityManager.createQuery("from Category").getResultList();
	}

	@Override
	public List<Category> getAllRecords(){
		return entityManager.createQuery("from Category u order by u.id desc").getResultList();
	}

	@Override
	public List<User> getPageRecord(int pageIndex, int pageSize){
		if(pageIndex <= 0) return null;

		return entityManager.createQuery("from Category c order by c.id asc")
				.setFirstResult((pageIndex - 1) * pageSize)
				.setMaxResults(pageSize).getResultList();
	}
	
	@Override
	public long getRecordSize(){
		long size = (Long) entityManager.createQuery("select count(*) from Category").getSingleResult();
		return size;
	}

	@Override
	public void deleteBatch(List<Long> ids) {
		entityManager.createQuery("delete from Category c where c.id in ?1")
				.setParameter(1, ids).executeUpdate();
		for(long id : ids) {
			newsService.deleteByCID(id);
		}
	}
	
}
