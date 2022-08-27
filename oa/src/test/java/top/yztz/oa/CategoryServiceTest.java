package top.yztz.oa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yztz.oa.bean.Category;
import top.yztz.oa.service.CategoryService;
import top.yztz.oa.utils.PageUtils;
import top.yztz.oa.utils.UUIDUtils;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Test
	public void add() {

		for(int i=0; i<1; i++){
			
			Category Category = new Category();
			Category.setName("分类名称"+i);
			Category.setComment("备注"+i);
			categoryService.add(Category);
			
		}
	}
	
	@Test
	public void delete() {

		categoryService.delete(25);
	}
	
	@Test
	public void update() {

		Category Category = categoryService.getById(22);
		Category.setName("Hello额鹅鹅鹅");
		categoryService.update(Category);
	}
	
	@Test
	public void getById() {
		
		Category category = categoryService.getById(22);
		System.out.println(category.toString());

	}
	
	@Test
	public void getAll() {
		
		List<Category> categoryList = categoryService.getAllRecords();
		for(Category u : categoryList){
			System.out.println(u.toString());
		}

	}
	
	
	@Test
	public void getSize() {
		
		System.out.println(categoryService.getRecordSize());

	}
	
	
	@Test
	public void getUUID(){
		
		for(int i=0; i<52; i++){
			
			System.out.println(UUIDUtils.getUUID());
		}
		
		
	}
	
	
	@Test
	public void getPageSize(){
		
		int size = PageUtils.getTotalPage(10, 0);
		System.out.println(size);
		
	}
	
	
}
