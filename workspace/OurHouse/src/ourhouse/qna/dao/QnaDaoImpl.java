package ourhouse.qna.dao;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourhouse.util.SqlMapClientFactory;

public class QnaDaoImpl implements IQnaDao{
	
	private static IQnaDao qnaDao;
	
	private SqlMapClient smc;
	
	private QnaDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IQnaDao getInstance() {
		if (qnaDao == null) {
			qnaDao = new QnaDaoImpl();
		}
		return qnaDao;
		
	}
	
}