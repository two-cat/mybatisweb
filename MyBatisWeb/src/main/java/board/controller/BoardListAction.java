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
		//검색유형과 검색어 받아오기
		String findType=req.getParameter("findType");
		String findKeyword=req.getParameter("findKeyword");
		
		//[0] 현재 보여줄 페이지(cpage)값 받아오기
		String cpStr=req.getParameter("cpage");
		if(cpStr==null||cpStr.trim().isEmpty()) {
			cpStr="1"; //기본 페이지 1로 지정
		}
		int cpage = Integer.parseInt(cpStr.trim());//현재 보여줄 페이지
		
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		//[1]총 게시글 수 가져오기 [검색 포함]
		int totalCount = dao.getTotalCount(findType, findKeyword);
		
		//[2]한 페이지 당 몇개의 게시글을 보여줄 것인지 정하자=> 10개
		int pageSize =10; //한 페이지에 10개의 게시글 보여주기
		
		int pageCount=1;
		/* 총게시글수		pageSize		pageCount
		 * 
		 * 1 ~ 9,10		10				1
		 * 11~19,20		10				2
		 * 21~29,30		10				3
		 * 31~39,40		10				4
		 * 게시글수: 10,20,30,40...
		 * 	pageCount = totalCount/pageSize
		 * 게시글수: 1~9, 11~19, 21~29 ....
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
			cpage=1;//첫 페이지로 설정
		}
		if(cpage>pageCount) {
			cpage=pageCount;//마지막 페이지로 설정
		}
		//DB에서 해당 페이지의 목록을 가져오기 위한 연산
		int end = cpage * pageSize;
		int start = end - (pageSize-1);
		
		//게시글 목록 가져오기
		List<BoardVO> boardArr = dao.listBoard(start, end, findType, findKeyword);
		System.out.println(boardArr);
		
		req.setAttribute("totalCount", totalCount); 
		req.setAttribute("boardArr", boardArr);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("cpage", cpage);
		
		this.setViewPage("/board/boardList.jsp");

	}

}
