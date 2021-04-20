package ourhouse.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CustomEncoding implements Filter{

	private String encoding; // 인코딩 정보
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding") == null ? "utf-8" : config.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		System.out.println("request 인코딩 설정 : " + encoding);
		req.setCharacterEncoding(encoding);
		
		System.out.println("response 인코딩 설정 : " + encoding);
		resp.setCharacterEncoding(encoding);
		
		fc.doFilter(req, resp); // 다음 필터나 실행되야할 서블릿으로 넘김
	}

	@Override
	public void destroy() {}



}
