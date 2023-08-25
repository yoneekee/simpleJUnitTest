package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.jUnitDTO;

public class jUnitDAO {

	// GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE quiz TO testuser;

	private Connection cn = null;

	public jUnitDAO() {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/testdb";
			String id = "testuser";
			String psw = "testpass";
			cn = DriverManager.getConnection(url, id, psw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public jUnitDTO getOneDto(int id) {
		jUnitDTO dto = new jUnitDTO();
		String sql = "SELECT * FROM jUnitTbl WHERE id = " + id;
		try (PreparedStatement pstmt = cn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				String content = rs.getString("content");
				dto.setId(id);
				dto.setContent(content);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	public int maxId() {
		int maxId = 0;
		String sql = "SELECT MAX(id) as id FROM jUnitTbl";
		try (PreparedStatement pstmt = cn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				maxId = rs.getInt("id");
				maxId++;
				return maxId;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxId;
	}
	
	public int countDtos() {
		int cnt = 0;
		String sql = "SELECT COUNT(*) as cnt FROM jUnitTbl";
		try (PreparedStatement pstmt = cn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				cnt = rs.getInt("cnt");
				return cnt;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	public List<jUnitDTO> allSelect() {
		List<jUnitDTO> dtos = new ArrayList<>();
		String sql = "SELECT * FROM jUnitTbl ORDER BY id ASC";
		try (PreparedStatement pstmt = cn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String content = rs.getString("content");
				jUnitDTO dto = new jUnitDTO();
				dto.setId(id);
				dto.setContent(content);
				dtos.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}

	public int insert(jUnitDTO dto) {
		int result = 0;
		String sql = "INSERT INTO jUnitTbl (id, content) VALUES (?, ?)";
		try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
			pstmt.setInt(1, dto.getId());
			pstmt.setString(2, dto.getContent());
			pstmt.executeUpdate();
			result = 1;
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int delete(int id) {
		int result = 0;
		String sql = "DELETE FROM jUnitTbl WHERE id=?";

		try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			result = 1;
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}
}
