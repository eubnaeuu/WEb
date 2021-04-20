package ourhouse.member.handler;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.MemberVO;
/**
 * 로그인한 상태 => 마이페이지 => 비밀번호 재설정 => 이메일 인증페이지
 * @author 김지현
 */
public class MyPageMailAuthHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/mypage/mypageMailAuth.jsp";
	private static final String LOGIN_PAGE = "/member/signin.do";

	static final String USER = "id@naver.com";
	static final String PASSWORD = "password";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession memSession = req.getSession(false);
		if(memSession == null) {
			return LOGIN_PAGE;
		}else {
			//로그인상태에서 세션에서 회원정보 받아서 메일인증 jsp에 현재 로그인한 회원의 이메일정보 뿌려주기
			MemberVO memVO = (MemberVO) memSession.getAttribute("session");
			if(memVO == null) {
				return LOGIN_PAGE;
			}else {
				if(req.getMethod().equals("GET")) {

					String memEmail = memVO.getMemEmail();

					req.setAttribute("memEmail", memEmail);

					return VIEW_PAGE;
				}else {//post일때
					//	1.받는사람, 메일제목, 내용
					//  사용자가 키값 "userMail"로 입력한 메일주소를 get
						String userMail = req.getParameter("userMail");

						String mail_EncodingType = "EUC-KR";

					//	2. Property에 SMTP 서버 정보를 설정
						Properties prop = new Properties();
						String host = "smtp.naver.com";
						int port = 465;

						// SMTP 서버명
						prop.put("mail.smtp.host", host);

						// SMTP 포트명
						prop.put("mail.smtp.port", port);

						// 권한 설정, SSL 연결
						prop.put("mail.smtp.auth", "true");
						prop.put("mail.smtp.ssl.enable", "true");
						prop.put("mail.smtp.ssl.trust", host);

						prop.put("mail.mime.charset", mail_EncodingType);

						// 세션 인스턴스화 (USER,PASSWORD)
						Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator(){
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(USER, PASSWORD);
							}
						});
					//	session.setDebug(true);

						//인증 번호 생성기
				        StringBuffer temp =new StringBuffer();
				        Random rnd = new Random();
				        for(int i=0;i<10;i++)
				        {
				            int rIndex = rnd.nextInt(3);
				            switch (rIndex) {
				            case 0:
				                // a-z
				                temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				                break;
				            case 1:
				                // A-Z
				                temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				                break;
				            case 2:
				                // 0-9
				                temp.append((rnd.nextInt(10)));
				                break;
				            }
				        }
				        String authKey = temp.toString();
				        System.out.println(authKey);

				        //메일 보내기
						try {
							if(!(userMail.equals(null))){
								MimeMessage message = new MimeMessage(session);

								// 발신자 (내꺼 네이버 아이디)
								message.setFrom(new InternetAddress(USER));

								// 수신자 메일주소 (사용자가 입력한 메일주소)
								message.addRecipient(Message.RecipientType.TO,  new InternetAddress(userMail));

								// subject (메일 제목)
								message.setSubject("우리의집에 오신걸 환영합니다 🏠WELCOME TO OURHOUSE🏠");

								// text (메일 내용)
								message.setText("인증 번호는 "+ authKey+ " 입니다.");

								// 메일 전송
								Transport.send(message);

								System.out.println("인증번호 전송 완료");
							}else{
								System.out.println("오류 - 메일 제목 또는 내용을 받아올 수 없습니다");
//								return;
							}
						}catch(AddressException e){
							e.printStackTrace();
						}catch(MessagingException e){
							e.printStackTrace();
						}

						//세션에 키값"authKey"로 위에서 생성한 인증키를 set
						HttpSession sessionAuthKey = req.getSession();
						sessionAuthKey.setAttribute("authKey", authKey);

						//req도 키값"authKey"에 위에서 생성한 인증키를 set
						req.setAttribute("authKey", authKey);

//						RequestDispatcher disp = req.getRequestDispatcher("/html/mail/authChk.jsp");
//						disp.forward(req, resp);

						String url = "/views/signup/authChk.jsp";
						return url;
				}
			}
		}
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
