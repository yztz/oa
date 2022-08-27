package top.yztz.oa.service;

import top.yztz.oa.bean.News;

import java.util.List;

public interface NewsService {

	void add(News news);

	void delete(long id);

	void deleteBatch(List<Long> ids);

	void update(News news);

	News getById(long id);

	void deleteByCID(long cid);


	List<News> getAllRecords();
	
	List<News> getPageRecord(int pageIndex, int pageSize);
	int getRecordSize();
	
	List<News> queryByName(String number, int pageIndex, int pageSize);
	int querySizeByName(String number);
	
	List<News> queryByCategory(long cid, int pageIndex, int pageSize);
	int querySizeByCategory(long cid);
	
	List<News> queryByNameAndCID(String name, long cid, int pageIndex, int pageSize);
	int querySizeByNameAndCID(String name, long cid);
	
	
}