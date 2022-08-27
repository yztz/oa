package top.yztz.oa.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yztz.oa.bean.News;
import top.yztz.oa.service.NewsService;
import top.yztz.oa.utils.FileUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public void add(News news) {
        entityManager.persist(news);
    }

    @Override
    public void delete(long id) {
        News obj = entityManager.find(News.class, id);

        if (obj != null){
            FileUtils.deletePicture(obj.getPicName());
            entityManager.remove(obj);
        }
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        List<News> newsList = entityManager.createQuery("select n from News n where n.id in :ids")
                .setParameter("ids", ids).getResultList();

        for(News news : newsList) {
            FileUtils.deletePicture(news.getPicName());
            entityManager.remove(news);
        }
    }

    @Override
    public void update(News news) {
        News oldNews = entityManager.find(News.class, news.getId());
        if(!oldNews.getPicName().equals(news.getPicName())) {
            FileUtils.deletePicture(oldNews.getPicName());
        }
        entityManager.merge(news);
    }

    @Override
    public News getById(long id) {
        return entityManager.find(News.class, id);
    }

    @Override
    public void deleteByCID(long cid) {
        List<News> newsList = entityManager.createQuery("select n from News n where n.categoryID in :cid")
                .setParameter("cid", cid).getResultList();

        for(News news : newsList) {
            FileUtils.deletePicture(news.getPicName());
            entityManager.remove(news);
        }
    }

    @Override
    public List<News> getAllRecords() {
        return entityManager.createQuery("from News").getResultList();
    }

    @Override
    public int getRecordSize() {
        long size = (Long) entityManager.createQuery("select count(*) from News").getSingleResult();
        return (int) size;
    }

    @Override
    public List<News> getPageRecord(int pageIndex, int pageSize) {

        return entityManager.createQuery("from News u order by u.time desc")
                .setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).getResultList();
    }

    @Override
    public List<News> queryByName(String name, int pageIndex, int pageSize) {

        return entityManager.createQuery("from News u where u.name like ?0 order by u.time desc")
                .setParameter(0, "%" + name + "%")
                .setFirstResult((pageIndex - 1) * pageSize)
                .setMaxResults(pageSize).getResultList();
    }

    @Override
    public int querySizeByName(String number) {
        long size = (Long) entityManager.createQuery("select count(*) from News u where u.number like ?0").setParameter(0, "%" + number + "%").getSingleResult();
        return (int) size;
    }

    @Override
    public List<News> queryByCategory(long cid, int pageIndex, int pageSize) {
        return entityManager.createQuery("select n from News n where n.categoryID = ?1 order by n.time desc ")
                .setParameter(1, cid)
                .setFirstResult((pageIndex - 1) * pageSize)
                .setMaxResults(pageSize).getResultList();
    }

    @Override
    public int querySizeByCategory(long cid) {

        long size = (Long) entityManager.createQuery("select count(*) from News u where u.categoryID = ?0")
                .setParameter(0, cid).getSingleResult();
        return (int) size;
    }

    @Override
    public List<News> queryByNameAndCID(String name, long cid, int pageIndex, int pageSize) {

        return entityManager.createQuery("select * from News u where u.name like ?0 and u.categoryID = ?1 order by u.time asc")
                .setParameter(0, "%" + name + "%")
                .setParameter(1, cid)
                .setFirstResult((pageIndex - 1) * pageSize)
                .setMaxResults(pageSize).getResultList();
    }

    @Override
    public int querySizeByNameAndCID(String name, long cid) {
        long size = (Long) entityManager
                .createQuery("select count(*) from News u where u.name like ?0 and u.typeId = ?1")
                .setParameter(0, "%" + name + "%")
                .setParameter(1, cid).getSingleResult();
        return (int) size;
    }


}
