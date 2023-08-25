package TestJUnit;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

import DAO.jUnitDAO;
import DTO.jUnitDTO;

public class jUnitTestCase {
	
	// DAO 클래스 끌고 올 수 있게 객체 생성 
	jUnitDAO dao = new jUnitDAO();
	
	// 로그 출력 객체 생성
	 private static final Logger logger = Logger.getLogger(jUnitTestCase.class.getName());

	@Test
	public void selectAllTest() { // 메소드명으로 한글을 많이 사용한다.
		// 목록의 개수가 5개이면 테스트 성공, 아니면 실패
		List<jUnitDTO> dtos = dao.allSelect();
		int result = dao.countDtos();
		assertEquals(result, dtos.size()); 	
		logger.info("selectAllTest 함수에 대한 테스트 실행 : " + result);
	}
	
	@Test
	public void selectOneTest() {
		jUnitDTO dto = dao.getOneDto(2);
		//assertEquals(2, dto.getId()); // 성공하는 테스트
		assertEquals(3, dto.getId()); // 실패하는 테스트
		logger.info("selectOneTest 함수에 대한 테스트 실행");
	}

	
	@Test
	public void insertDtoTest() {
		jUnitDTO dtoNew = new jUnitDTO();
		dtoNew.setId(dao.maxId());
		dtoNew.setContent("jUnitTbl 0" + dao.maxId());
		int result = dao.insert(dtoNew);
		assertEquals(1, result);
		logger.info("insertDtoTest 함수에 대한 테스트 실행");
	}
	
	
	/*
	@Test
	public void deleteDtoTest() {
		int id = 10;
		int result = dao.delete(id);
		assertEquals(1, result);
		logger.info("deleteDtoTest 함수에 대한 테스트 실행");
	}
	*/
}
