package top.yztz.oa.service;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import top.yztz.oa.bean.User;

import java.util.List;

public interface UserService {

	void add(User user);

	void delete(long id);

	void deleteBatch(List<Long> ids);

	void update(User user);

	User getById(long id);

	List<User> getAllRecords();
	
	List<User> queryByPhone(String phone);
	
	List<User> queryByName(String name);
	
	List<User> queryByNameAndPhone(String name, String phone);

	List<User> getPageRecord(int pageIndex, int pageSize);
	
	List<User> queryByName(String userName, int pageIndex, int pageSize);
	
	String querySizeByName(String userName);

	String getRecordSize();

	User getByUserNameAndPassword(String userName, String password);
	
	User getByAccountAndPassword(String account, String password);

}