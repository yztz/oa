package top.yztz.oa;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import top.yztz.oa.bean.User;
import top.yztz.oa.service.UserService;
import top.yztz.oa.utils.PageUtils;
import top.yztz.oa.utils.TypeUtils;
import top.yztz.oa.utils.UUIDUtils;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;


	@Test
	public void add() {

		for(int i=0; i<20; i++){
			User user = new User();
			user.setPassword("123456");
			user.setUserName("用户名"+i);
			user.setPhone("8898989"+i);
			user.setUserType(TypeUtils.USER);
			userService.add(user);
			
		}
	}
	
	@Test
	public void delete() {

		userService.delete(1);
	}
	
	@Test
	public void update() {

		User user = userService.getById(2);
		user.setUserName("更新...");
		userService.update(user);
	}
	
	@Test
	public void getById() {
		
		User user = userService.getById(2);
		System.out.println(user.toString());

	}
	
	@Test
	public void getAll() {
		
		List<User> userList = userService.getAllRecords();
		for(User u : userList){
			System.out.println(u.toString());
		}

	}
	
	@Test
	public void getPage() {
		
		List<User> userList = userService.getPageRecord(2, 20);
		for(User u : userList){
			System.out.println(u.toString());
		}

	}
	
	@Test
	public void getSize() {
		
		System.out.println(userService.getRecordSize());

	}
	
	@Test
	public void getByUserNameAndPassword() {
		
		User user = userService.getByUserNameAndPassword("admin", "123456");
		if(user != null){
			System.out.println(user.toString());
		}else{
			System.out.println("不存在该用户............");
		}

	}
	
	@Test
	public void getUUID(){
		
		for(int i=0; i<52; i++){
			
			System.out.println(UUIDUtils.getUUID());
		}
		
		
	}
	
	@Test
	public void getPageUser(){
		
		List<User> userList = userService.queryByName("1", 2, 6);
		for(User u : userList){
			System.out.println(u.toString());
		}
		
		String size = userService.querySizeByName("1");
		System.out.println(size);
		
	}
	
	@Test
	public void getPageSize(){
		
		int size = PageUtils.getTotalPage(10, 0);
		System.out.println(size);
		
	}

}
