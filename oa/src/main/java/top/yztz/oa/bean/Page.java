package top.yztz.oa.bean;

public class Page {

	private int pageIndex;
	private int pageSize;
	private int totalPage;
	private Object data;
	
	public Page() {}

	public Page(int pageIndex, int pageSize, int totalPage, Object object) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.data = object;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Page [pageIndex=" + pageIndex + ", pageSize=" + pageSize + ", totalPage=" + totalPage + ", data=" + data
				+ "]";
	}
	
}
