package kr.or.ddit.common.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.service.ZipService;
import kr.or.ddit.common.vo.ZipVO;

@WebServlet("/ZipServlet")
public class ZipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		
		
		ZipService zipService = new ZipService();
		List<ZipVO> list = zipService.retrieveSidoList();
			
		req.setAttribute("list", list);
		RequestDispatcher  disp = req.getRequestDispatcher("/html/common/zipListResult.jsp");
		disp.forward(req, resp);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
