package ourhouse.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import ourhouse.common.handler.NullHandler;
import ourhouse.common.handler.CommandHandler;
import ourhouse.util.FileUploadRequestWrapper;

public class WebController extends HttpServlet{ // *.do url 에 소환됨 (web.xml 에 매핑)
	
	private static Logger logger = Logger.getLogger(WebController.class);
	
	// 매핑 정보를 저장 (핸들러 객체 저장용 맵)
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<String, CommandHandler>();
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		// 1.web.xml(config 파일)의 init-param을 이용해 properties 파일 경로 가져오기
		String configFilePath = config.getInitParameter("handler-config");
		
		Properties handlerProp = new Properties();
		
		String configFileRealPath = config.getServletContext().getRealPath(configFilePath);
		
		// 2. properties 파일 읽기
		FileReader fr;
		
		try {
			fr = new FileReader(configFileRealPath);
			handlerProp.load(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 3. properties 파일 읽어 대응되는 핸들러 객체를 생성하여 hashmap에 저장하기
		for (Object key : handlerProp.keySet()) {
			String command = (String) key;
			
			try {
				Class<?> klass = Class.forName(handlerProp.getProperty(command)); // 클래스 자체의 정보
				CommandHandler handlerInstance = (CommandHandler) klass.newInstance(); // command (properties 좌변)에 해당하는 handler 클래스 객체 생성
				
				// 핸들러 객체를 map 에 등록 (key: url 주소, value: handler 객체)
				commandHandlerMap.put(command, handlerInstance);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		logger.info("CommandHandler 매핑정보\n " + commandHandlerMap); // { /board/delete.do=kr.or.ddit.board.handler.DeleteBoardHandler..., ...}	
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파일 업로드 처리를 위한 래퍼클래스 사용
		try {
			FileUploadRequestWrapper requestWrapper = new FileUploadRequestWrapper(req);
			process(requestWrapper, resp); // request가 multipart라면 파싱되어서, 아니라면 기존 기능 그대로
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		// url 자체를 하나의 command(명령)이라고 생각
		String command = req.getRequestURI();
		System.out.println("전 : command => " + command);
		
		if (command.indexOf(req.getContextPath()) == 0) {
			command = command.substring(req.getContextPath().length()); // 루트 포함이면 루트 이후만 남기르려는것같음
		}
		System.out.println("후 : command => " + command);
		
		CommandHandler handler = commandHandlerMap.get(command);
		
		if(handler == null) {
			handler = new NullHandler();
		}
		
		String viewPage = "";
		try {
			viewPage = handler.process(req, resp); // command 에 맞는 handler 에 일 시키기
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		// VIEW 화면 처리
		if (viewPage != null) { // view 정보가 존재하면...
			if (handler.isRedirect(req)) { // redirect 처리가 필요하면...
				resp.sendRedirect(viewPage);
			} else {
				RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, resp);
			}
		}
		
	}


	
	
}
