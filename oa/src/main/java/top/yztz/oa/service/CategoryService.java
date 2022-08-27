package top.yztz.oa.service;


import top.yztz.oa.bean.Category;
import top.yztz.oa.bean.User;

import java.util.List;

public interface CategoryService {

	void add(Category category);

	void delete(long id);

	void update(Category category);

	Category getById(long id);

	List<Category> getAll();

	List<Category> getByName(String name);

	List<Category> getByName(String name, int pageIndex, int pageSize);

	void deleteBatch(List<Long> ids);

	List<Category> getAllRecords();

	long getRecordSize();

	List<User> getPageRecord(int pageIndex, int pageSize);


}