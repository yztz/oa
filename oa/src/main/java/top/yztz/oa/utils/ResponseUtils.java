package top.yztz.oa.utils;



import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static top.yztz.oa.ErrCode.*;


public class ResponseUtils {

	public static void write(HttpServletResponse response,Object object) throws Exception
	{
		response.setContentType("text/plain;charset=utf-8");
		response.getWriter().print(object.toString());
		response.getWriter().flush();
		response.getWriter().close();
		
	}

	public static JSONObject success(String message, Object bean){
		JSONObject object = new JSONObject();
		object.put("success", true);
		object.put("message", message);
		object.put("data", bean);
		object.put("code", SUCCESS);

		return object;
	}

	
	public static JSONObject resultBean(boolean isSuccess, String message, Object bean, int code){
		
		JSONObject object = new JSONObject();
		object.put("success", isSuccess);
		object.put("message", message);
		object.put("data", bean);
		object.put("code", code);
		
		return object;
	}
	
	public static <T> JSONObject resultBean(boolean isSuccess, String message, List<T> list, int code){
		
		JSONObject object = new JSONObject();
		object.put("success", isSuccess);
		object.put("message", message);
		object.put("data", list);
		object.put("code", code);
		
		return object;
	}

	public static <T> JSONObject fail(String message, int code){

		JSONObject object = new JSONObject();
		object.put("success", false);
		object.put("message", message);
		object.put("code", code);

		return object;
	}
	
}
