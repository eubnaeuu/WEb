package ourhouse.qna.service;

import ourhouse.qna.dao.IQnaDao;
import ourhouse.qna.dao.QnaDaoImpl;

public class QnaServiceImpl implements IQnaService{
	private static IQnaService qnaService;
	
	private IQnaDao qnaDao;
	
	private QnaServiceImpl() {
		qnaDao = QnaDaoImpl.getInstance();
	}
	
	public static IQnaService getInstance() {
		if (qnaService == null) {
			qnaService = new QnaServiceImpl();
		}
		return qnaService;
	}
}
