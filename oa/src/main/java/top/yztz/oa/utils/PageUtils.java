package top.yztz.oa.utils;

public class PageUtils {
	
	public static int getTotalPage(int pageSize, int totalSize){
		
		int left = totalSize % pageSize;
		
		if(left > 0){
			return (totalSize / pageSize + 1);
		}else{
			return (totalSize / pageSize);
		}
		
	}

}
