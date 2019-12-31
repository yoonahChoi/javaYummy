package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import VOs.BoardVO;
import VOs.CommentsVO;
import VOs.NoticeVO;

public class BoardDAO {
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	public void getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "java";
		String password = "java";

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();

		}
	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public ArrayList<BoardVO> selectBoard(int boardNo) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO bdVO;

		getConn();

		String sql = "select * from board where board_boardNo = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String getBoardNo = rs.getString(1);
				String textNo = rs.getString(2);
				String title = rs.getString(3);
				String id = rs.getString(4);
				String text = rs.getString(5);
				String date = rs.getString(6);
				String view = rs.getString(7);
				date = date.substring(0, 10);
				bdVO = new BoardVO(getBoardNo, textNo, title, id, text, date, view);

				list.add(bdVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public BoardVO selectBoard(int boardNo, int textNo) {
		BoardVO bdVO = null;

		getConn();

		String sql = "select * from board where board_boardNo = ? and board_textNo = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.setInt(2, textNo);
			rs = psmt.executeQuery();

			if (rs.next()) {
				String getBoardNo = rs.getString(1);
				String gettextNo = rs.getString(2);
				String title = rs.getString(3);
				String id = rs.getString(4);
				String text = rs.getString(5);
				String date = rs.getString(6);
				String view = rs.getString(7);
				date = date.substring(0, 10);
				bdVO = new BoardVO(getBoardNo, gettextNo, title, id, text, date, view);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return bdVO;
	}

	public int InsertBoard(int boardNo, String title, String id, String text) {
		int cnt = 0;
		String sql = null;
		getConn();

		switch (boardNo) {
		case 1:
			sql = "insert into board values (?, seq_codesub.NEXTVAL, ?, ?, ?, SYSDATE, 0)";
			break;
		case 2:
			sql = "insert into board values (?, seq_debate.NEXTVAL, ?, ?, ?, SYSDATE, 0)";
			break;
		case 3:
			sql = "insert into board values (?, seq_free.NEXTVAL, ?, ?, ?, SYSDATE, 0)";
			break;
		case 4:
			sql = "insert into board values (?, seq_qna.NEXTVAL, ?, ?, ?, SYSDATE, 0)";
			break;
		case 5:
			sql = "insert into board values (?, seq_notice.NEXTVAL, ?, ?, ?, SYSDATE, 0)";
		}

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.setString(2, title);
			psmt.setString(3, id);
			psmt.setString(4, text);

			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	public ArrayList<CommentsVO> selectComments(int boardNo, int textNo) {
		ArrayList<CommentsVO> list = new ArrayList<CommentsVO>();
		CommentsVO comVO = null;

		getConn();

		String sql = "select * from comments where board_boardNo = ? and board_textNo = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.setInt(2, textNo);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String getBoardNo = rs.getString(1);
				String gettextNo = rs.getString(2);
				String date = rs.getString(3);
				String id = rs.getString(4);
				String comments = rs.getString(5);
				date = date.substring(0, 10);

				comVO = new CommentsVO(getBoardNo, gettextNo, date, id, comments);

				list.add(comVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public int InsertComments(int boardNo, int textNo, String id, String comments) {
		int cnt = 0;

		getConn();

		String sql = "insert into comments values (?, ?, SYSDATE, ?, ?)";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.setInt(2, textNo);
			psmt.setString(3, id);
			psmt.setString(4, comments);

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	public void updateView(int boardNo, int textNo, int view) {
		getConn();

		String sql = "update board set board_view = ? where board_boardno = ? and board_textno = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, view);
			psmt.setInt(2, boardNo);
			psmt.setInt(3, textNo);

			psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public int updateBoard(int boardNo, int textNo, String title, String text) {
		int cnt = 0;
		getConn();

		String sql = "update board set board_title = ?, board_text = ? where board_boardno = ? and board_textno = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, text);
			psmt.setInt(3, boardNo);
			psmt.setInt(4, textNo);

			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cnt;
	}

	public ArrayList<BoardVO> selectMyBoard(String id) {

		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO boardVO = null;

		getConn();

		String sql = "select * from board where member_id = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String boardNo = rs.getString(1);
				String textNo = rs.getString(2);
				String title = rs.getString(3);
				String getId = rs.getString(4);
				String text = rs.getString(5);
				String date = rs.getString(6);
				String view = rs.getString(7);
				date = date.substring(0, 10);

				boardVO = new BoardVO(boardNo, textNo, title, getId, text, date, view);

				list.add(boardVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public void DeleteComments(int boardNo, int textNo) {

		getConn();

		String sql = "delete from comments where board_boardno = ? and board_textno = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.setInt(2, textNo);

			psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public int DeleteBoard(int boardNo, int textNo) {
		int cnt = 0;

		getConn();

		String sql = "delete from board where board_boardno = ? and board_textno = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.setInt(2, textNo);

			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cnt;
	}

	public NoticeVO selectNotice() {
		NoticeVO noticeVO = null;

		getConn();

		String sql = "select * from notice";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				int boardNo = rs.getInt(1);
				int textNo = rs.getInt(2);
				noticeVO = new NoticeVO(boardNo, textNo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return noticeVO;
	}

	public int updateNotice(int boardNo, int textNo) {

		int cnt = 0;

		getConn();

		String sql = "update notice set board_no = ?, text_no = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.setInt(2, textNo);

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;

	}

}
