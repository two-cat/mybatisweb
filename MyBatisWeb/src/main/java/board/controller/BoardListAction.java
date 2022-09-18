package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardListAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//�˻������� �˻��� �޾ƿ���
		String findType=req.getParameter("findType");
		String findKeyword=req.getParameter("findKeyword");
		
		//[0] ���� ������ ������(cpage)�� �޾ƿ���
		String cpStr=req.getParameter("cpage");
		if(cpStr==null||cpStr.trim().isEmpty()) {
			cpStr="1"; //�⺻ ������ 1�� ����
		}
		int cpage = Integer.parseInt(cpStr.trim());//���� ������ ������
		
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		//[1]�� �Խñ� �� �������� [�˻� ����]
		int totalCount = dao.getTotalCount(findType, findKeyword);
		
		//[2]�� ������ �� ��� �Խñ��� ������ ������ ������=> 10��
		int pageSize =10; //�� �������� 10���� �Խñ� �����ֱ�
		
		int pageCount=1;
		/* �ѰԽñۼ�		pageSize		pageCount
		 * 
		 * 1 ~ 9,10		10				1
		 * 11~19,20		10				2
		 * 21~29,30		10				3
		 * 31~39,40		10				4
		 * �Խñۼ�: 10,20,30,40...
		 * 	pageCount = totalCount/pageSize
		 * �Խñۼ�: 1~9, 11~19, 21~29 ....
		 *  pageCount = totalCount/pageSize +1
		 * */
		/*
		if(totalCount%pageSize==0) {
			pageCount = totalCount/pageSize;
		}else {
			pageCount = totalCount/pageSize+1;
		}
		*/
		pageCount=(totalCount-1)/pageSize +1;
		
		if(cpage<1) {
			cpage=1;//ù �������� ����
		}
		if(cpage>pageCount) {
			cpage=pageCount;//������ �������� ����
		}
		//DB���� �ش� �������� ����� �������� ���� ����
		int end = cpage * pageSize;
		int start = end - (pageSize-1);
		
		//�Խñ� ��� ��������
		List<BoardVO> boardArr = dao.listBoard(start, end, findType, findKeyword);
		System.out.println(boardArr);
		
		req.setAttribute("totalCount", totalCount); 
		req.setAttribute("boardArr", boardArr);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("cpage", cpage);
		
		this.setViewPage("/board/boardList.jsp");

	}

}
