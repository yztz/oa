package top.yztz.oa.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class WebContextFilter implements Filter {

	@Override
	public void destroy() {}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Content-Type,Accept");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "Get,Post,Put,OPTIONSt");
//		httpServletResponse.setHeader("Access-Control-Allow-Headers", "Authentication");
		
		chain.doFilter(request, httpServletResponse);
	}

	

	

}
