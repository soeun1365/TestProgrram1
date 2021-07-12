package com.koreait.test1;

import static org.junit.Assert.assertEquals;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koreait.test1.config.BeanConfiguration;
import com.koreait.test1.dao.BoardDAO;
import com.koreait.test1.dto.BoardDTO;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {BeanConfiguration.class})

public class BoardTest {

	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void insertTest() {
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		int count = boardDAO.insertBoard("테스터", "테스트제목", "테스트내용");
		assertEquals("등록실패", 1, count);
	}
	
	int exist = 0;
	@Test
	public void selectTest() {
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		int bIdx = 9999;
		BoardDTO boardDTO = boardDAO.selectBybIdx(bIdx);
		if(boardDTO == null) {
			exist = 0;
		}else {
			exist = 1;
		}
		assertEquals("조회 실패", 1, exist);
	}
	
	@Test
	public void updateTest() {
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		int count = boardDAO.updateBoard("변경공지사항제목", "변경공지사항내용", 9999);
		assertEquals("변경 실패", 1, count);
	}
	
	@Test
	public void deleteTest() {
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		int count = boardDAO.deleteBoard(9999);
		assertEquals("삭제 실패", 1, count);
	}

}
