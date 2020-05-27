package com.dao;

//최종 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.Login;

import VOs.AttRankVO;
import VOs.AttVO;
import VOs.MemberVO;
import VOs.QuizVO;
import VOs.ScoreGetQuizVO;
import VOs.ScoreGetRankVO;
import VOs.ScoreVO;

public class GradeSystemDAO {
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

	// 아이디 중복확인
	public boolean nameCon(String id) {
		getConn();

		String sql = "SELECT * FROM Member WHERE member_id =?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return false;
	}

	// 회원가입
	public int join(String id, String pw, String name, int team) {
		getConn();

		String sql = "INSERT INTO member(member_id, member_pw, member_name, member_teamno) VALUES(?, ?, ?, ?)";
		int cnt = 0;
		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, name);
			psmt.setInt(4, team);

			cnt = psmt.executeUpdate(); // 실행 행 수
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// 'select * from member where member_id = ?'
	public MemberVO selectMember(String id) {
		MemberVO memVO = null;
		getConn();
		String sql = "select * from member where member_id = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			if (rs.next()) {
				String memId = rs.getString(1);
				String pw = rs.getString(2);
				String name = rs.getString(3);
				String teamno = rs.getString(4);
				String level = rs.getString(5);

				memVO = new MemberVO(memId, pw, name, teamno, level);
			}
		} catch (SQLException e) {

		} finally {
			close();
		}

		return memVO;
	}

	// 'select * from att where member_id = ?'
	public AttVO selectAtt(String id) {
		AttVO attVO = null;

		getConn();
		String sql = "select * from att where member_id = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			if (rs.next()) {
				String memid = rs.getString(1);
				String att = rs.getString(2);
				String late = rs.getString(3);
				String leave = rs.getString(4);
				String out = rs.getString(5);
				String abs = rs.getString(6);

				attVO = new AttVO(memid, att, late, leave, out, abs);
			}
		} catch (SQLException e) {

		} finally {
			close();
		}

		return attVO;
	}

	// 'select * from quiz where quiz_number = ?'
	public QuizVO selectQuiz(int quizNum) {
		QuizVO quizVO = null;

		getConn();
		String sql = "select * from quiz where quiz_number = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, quizNum);
			rs = psmt.executeQuery();

			if (rs.next()) {
				String quizNo = rs.getString(1);
				String id = rs.getString(2);
				String type = rs.getString(3);
				String date = rs.getString(4);
				String point = rs.getString(5);
				String text = rs.getString(6);
				String subNo = rs.getString(7);
				String answer = rs.getString(8);

				quizVO = new QuizVO(quizNo, id, type, date, point, text, subNo, answer);
			}
		} catch (SQLException e) {

		} finally {
			close();
		}

		return quizVO;
	}

	// 'select * from score where member_id = ?'
	public ArrayList<ScoreVO> selectAllGrade(String id) {

		ArrayList<ScoreVO> list = new ArrayList<ScoreVO>();
		ScoreVO scVO;
		getConn();
		String sql = "select * from score where member_id = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String subNo = rs.getString(1);
				String memid = rs.getString(2);
				String sub = rs.getString(3);
				String score = rs.getString(4);
				String date = rs.getString(5);
				date = date.substring(0, 10);
				scVO = new ScoreVO(subNo, memid, sub, score, date);

				list.add(scVO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 출결 3순위
	public String[] selectTopThreeATTSGrade() {

		String[] list = new String[3];

		getConn();
		String sql = "select member_id, atts\r\n" + "from\r\n"
				+ "(select member_id, att_att-att_late-att_leave-att_outing-att_absent as atts from att order by atts desc)";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			for (int i = 0; i < 3; i++) {
				rs.next();
				String name = rs.getString(1);

				list[i] = name;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 성적 3순위
	public String[] selectTopThreeGrade() {

		String[] list = new String[3];

		getConn();
		String sql = "select member_id, sumscore from \r\n"
				+ "(select a.member_id, a.score_score+b.score_score+c.score_score as sumscore from \r\n"
				+ "(select  member_id, score_subject, score_score from score where score_subject = 'JAVA') a, \r\n"
				+ "(select member_id, score_subject, score_score from score where score_subject = 'DB') b, \r\n"
				+ "(select member_id, score_subject, score_score from score where score_subject = 'SQL') c \r\n"
				+ "where a.member_id=b.member_id and b.member_id=c.member_id order by sumscore desc)";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			for (int i = 0; i < 3; i++) {
				rs.next();
				String name = rs.getString(1);

				list[i] = name;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;

	}

	// 퀴즈 3순위
	public String[] selectTopThreeQuizGrade() {

		String[] list = new String[3];

		getConn();
		String sql = "SELECT member_id, score_score\r\n"
				+ "from(select member_id, score_score from score where score_subject='QUIZ' order by score_score desc)\r\n"
				+ "where rownum <4";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			for (int i = 0; i < 3; i++) {
				rs.next();
				String name = rs.getString(1);

				list[i] = name;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 총 합계 3순위
	public String[] selectFinalThreeGrade() {

		String[] list = new String[3];

		getConn();
		String sql = "select rownum, member_id, rankscore from (select member_id, avgscore+attscore+quizscore as rankscore from (SELECT a.member_id as member_id, a.avg_score as avgscore, b.atts as attscore, c.score_score quizscore from (select member_id, avg(score_score) as avg_score FROM score GROUP BY member_id order by avg_score desc) a, (select member_id, att_att-att_late-att_leave-att_outing-att_absent as atts from att order by atts desc) b,(select member_id, score_score from score where score_subject='QUIZ') c where a.member_id=b.member_id and b.member_id=c.member_id) order by rankscore desc)where rownum < 4";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			for (int i = 0; i < 3; i++) {
				rs.next();
				String name = rs.getString(2);

				list[i] = name;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;

	}

	// 개인 성적 등수
	public ArrayList<ScoreGetRankVO> selectGetRank(String id) {

		ArrayList<ScoreGetRankVO> list = new ArrayList<>();
		ScoreGetRankVO scVOGetrank;
		getConn();
		String sql_getRank = "select rownum, member_id, sumscore from (select rownum, a.member_id, a.score_score+b.score_score+c.score_score as sumscore from (select  member_id, score_subject, score_score from score where score_subject = 'JAVA') a, (select member_id, score_subject, score_score from score where score_subject = 'DB') b, (select member_id, score_subject, score_score from score where score_subject = 'SQL') c where a.member_id=b.member_id and b.member_id=c.member_id order by sumscore desc)";

		try {
			psmt = conn.prepareStatement(sql_getRank);
			// psmt2.setString(1, id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				scVOGetrank = new ScoreGetRankVO(rs.getString(1), rs.getString(2), rs.getString(3));
				if (rs.getString(2).equals(id)) {
					list.add(scVOGetrank);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	// 개인 퀴즈 등수
	public ArrayList<ScoreGetQuizVO> selectGetQuiz(String id) {

		ArrayList<ScoreGetQuizVO> list = new ArrayList<>();
		ScoreGetQuizVO scoreGetquizVO;
		getConn();
		String sql_getQuiz = "select member_id, subject_no, score_score, Rank() over(order by score_score desc) as Rank from score where Subject_no = 5";

		try {
			psmt = conn.prepareStatement(sql_getQuiz);
			rs = psmt.executeQuery();

			while (rs.next()) {
				scoreGetquizVO = new ScoreGetQuizVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				if (rs.getString(1).equals(id)) {

					list.add(scoreGetquizVO);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 개인 퀴즈 총 누적점수
	public ArrayList<ScoreGetQuizVO> selectGetQuizScore(String id) {

		ArrayList<ScoreGetQuizVO> list = new ArrayList<>();
		ScoreGetQuizVO scoreGetquizscoreVO;
		getConn();
		String sql_getQuiz = "select member_id, subject_no, score_score, Rank() over(order by score_score desc) as Rank from score where Subject_no = 5";

		try {
			psmt = conn.prepareStatement(sql_getQuiz);
			rs = psmt.executeQuery();

			while (rs.next()) {
				scoreGetquizscoreVO = new ScoreGetQuizVO(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4));
				if (rs.getString(1).equals(id)) {

					list.add(scoreGetquizscoreVO);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 출결등수
	public ArrayList<AttRankVO> selectAttRank(String id) {

		ArrayList<AttRankVO> list = new ArrayList<>();
		AttRankVO AttrankVO;
		getConn();
		String sql = "select rownum, member_id, atts from(select member_id, att_att-att_late-att_leave-att_outing-att_absent as atts from att order by atts desc)";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				AttrankVO = new AttRankVO(rs.getString(1), rs.getString(2), rs.getString(3));
				if (rs.getString(2).equals(id)) {

					list.add(AttrankVO);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 총합점수별 등급 계산
	public int changeLevel() {
		getConn();
		int level = 0;
		String sql = "select member_id, sumscore from (select rownum, a.member_id, a.score_score+b.score_score+c.score_score+d.score_score as sumscore from (select  member_id, score_subject, score_score from score where score_subject = 'JAVA') a, (select member_id, score_subject, score_score from score where score_subject = 'DB') b, (select member_id, score_subject, score_score from score where score_subject = 'SQL') c, (select member_id, score_subject, score_score from score where score_subject = 'QUIZ') d where a.member_id=b.member_id and b.member_id=c.member_id and c.member_id=d.member_id) where Member_Id = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, Login.staticId);
			rs = psmt.executeQuery();

			while (rs.next()) {

				int score = rs.getInt(2);
				if (score > 310) {
					level = 5;
				} else if (score > 290) {
					level = 4;
				} else if (score > 270) {
					level = 3;
				} else if (score > 250) {
					level = 2;
				} else {
					level = 1;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return level;
	}

	// 등급 업데이트
	public void updateLevel(int changeLevel) {

		getConn();
		String sql = "update member set member_level = ? where Member_Id = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, changeLevel);
			psmt.setString(2, Login.staticId);
			psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public int getScoreQuizScore_Score() {

		getConn();
		String sql = "select Score_Score from Score where Member_id = ? and Score_Subject = 'QUIZ'";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, Login.staticId);

			rs = psmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}

	public void updateScoreQUIZScore_Score(int score) {

		getConn();
		String sql = "update Score set Score_Score = ? where Member_Id = ? and Score_Subject = 'QUIZ'";

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, score);
			psmt.setString(2, Login.staticId);

			int cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public String getQuizQuiz_Subject(int i) {

		boolean list[] = { false, false, false, false };

		getConn();
		String sql = "select member_id,subject_no from quiz where Quiz_Number = ?";

		String finalReturn = "";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, i);

			rs = psmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(2) == 1) {
					list[0] = true;
				} else if (rs.getInt(2) == 2) {
					list[1] = true;
				} else if (rs.getInt(2) == 3) {
					list[2] = true;
				} else if (rs.getInt(2) == 4) {
					list[3] = true;
				}
			}

			if (list[0]) {
				finalReturn += " DB ";
			}
			if (list[1]) {
				finalReturn += " SQL ";
			}
			if (list[2]) {
				finalReturn += " JDBC ";
			}
			if (list[3]) {
				finalReturn += " JAVA ";
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close();
		}

		return finalReturn;
	}
}
