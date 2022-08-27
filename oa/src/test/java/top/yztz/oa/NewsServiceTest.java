package top.yztz.oa;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yztz.oa.bean.Category;
import top.yztz.oa.bean.News;
import top.yztz.oa.service.CategoryService;
import top.yztz.oa.service.NewsService;
import top.yztz.oa.utils.PageUtils;
import top.yztz.oa.utils.UUIDUtils;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceTest {

    @Autowired
    private NewsService newsService;
    @Autowired
    private CategoryService categoryService;

    @Test
    public void add() {
        Category category = new Category();
        category.setName("testNews");
        category.setComment("this is only comment");
        categoryService.add(category);
        category = categoryService.getByName("testNews").get(0);
        for (int i = 0; i < 20; i++) {
            News news = new News();
            news.setName("test news " + i);
            news.setPicName("图片名称" + i);
            news.setTime(new Date(System.currentTimeMillis() + i * 100));
            news.setCategoryID(category.getId());
            newsService.add(news);
        }
    }

    @Test
    public void delete() {
        newsService.delete(1);
    }

    @Test
    public void update() {

        News News = newsService.getById(2);
        newsService.update(News);
    }

    @Test
    public void getById() {

        News News = newsService.getById(2);
        System.out.println(News.toString());

    }

    @Test
    public void getAll() {

        List<News> NewsList = newsService.getAllRecords();
        for (News u : NewsList) {
            System.out.println(u.toString());
        }

    }


    @Test
    public void getSize() {

        System.out.println(newsService.getRecordSize());

    }


    @Test
    public void getUUID() {

        for (int i = 0; i < 52; i++) {

            System.out.println(UUIDUtils.getUUID());
        }


    }


    @Test
    public void getPageSize() {

        int size = PageUtils.getTotalPage(10, 0);
        System.out.println(size);

    }

    @Test
    public void queryByNumber() {

        List<News> newslist = newsService.queryByName("1", 2, 3);
        for (News n : newslist) {
            System.out.println(n.toString());
        }

    }

    @Test
    public void queryNumberSize() {
        int size = newsService.querySizeByName("1");
        System.out.println(size);
    }

    @Test
    public void queryBytypeID() {

        List<News> newslist = newsService.queryByCategory(2, 2, 3);
        for (News n : newslist) {
            System.out.println(n.toString());
        }

    }

    @Test
    public void querytypeIDSize() {
        int size = newsService.querySizeByCategory(2);
        System.out.println(size);
    }


    @Test
    public void queryByNumberAndType() {

        List<News> newslist = newsService.queryByNameAndCID("1", 12, 2, 6);
        for (News n : newslist) {
            System.out.println(n.toString());
        }
    }

    @Test
    public void queryByNumberAndTypeSize() {

        int size = newsService.querySizeByNameAndCID("1", 2);
        System.out.println(size);
    }


}
