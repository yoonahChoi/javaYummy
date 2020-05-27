package Panels;

//ÃÖÁ¾
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.Login;
import com.Main;
import com.myCalendar;
import com.dao.GradeSystemDAO;

import VOs.AttVO;
import VOs.MemberVO;
import VOs.ScoreVO;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyInfoPanel extends JPanel {
	public JPanel myInfoPanel = new JPanel();
	myCalendar cal = new myCalendar();
	GradeSystemDAO dao = new GradeSystemDAO();
	private JTable table;
	static JLabel lblTodayScore = null;
	String subjectToStudy = "";

	public MyInfoPanel() {
		String id = Login.staticId;
		MemberVO result = dao.selectMember(id);
		ArrayList<ScoreVO> gradList = dao.selectAllGrade(result.getId());
		String[] col = { "°ú¸ñ", "Á¡¼ö", "³¯Â¥" };
		String[][] data = new String[gradList.size()][3];
		AttVO attVO = dao.selectAtt(result.getId());

		String AttRank = dao.selectAttRank(Login.staticId).get(0).getRowNum();
		String QuizRank = dao.selectGetRank(Login.staticId).get(0).getRowNum();
		String ScoreRank = dao.selectGetQuiz(Login.staticId).get(0).getQuizRank();

		int attRank = Integer.parseInt(AttRank);
		int quizRank = Integer.parseInt(QuizRank);
		int scoreRank = Integer.parseInt(ScoreRank);
		int allAvg = (attRank + quizRank + scoreRank) / 3;

		// °ú¸ñº° ¼ºÀû Å×ÀÌºí
		for (int i = 0; i < data.length; i++) {
			data[i][0] = gradList.get(i).getSub();
			data[i][1] = gradList.get(i).getScore();
			data[i][2] = gradList.get(i).getDate();
		}

		setLayout(null);
		String path = Main.class.getResource("").getPath();
		myInfoPanel.setBounds(0, 0, 1000, 600);
		myInfoPanel.setLayout(null);

		JPanel calendarPanel = new JPanel();
		calendarPanel.add(cal.getcalendarPanel());
		calendarPanel.setBounds(10, 101, 643, 298);
		myInfoPanel.add(calendarPanel);

		JLabel lblAttRank = new JLabel(dao.selectAttRank(Login.staticId).get(0).getRowNum());
		lblAttRank.setFont(new Font("±¼¸²", Font.BOLD, 12));
		lblAttRank.setHorizontalAlignment(SwingConstants.CENTER);
		lblAttRank.setBounds(594, 439, 59, 28);
		myInfoPanel.add(lblAttRank);

		JLabel lblGrdRank = new JLabel(dao.selectGetRank(Login.staticId).get(0).getRowNum());
		lblGrdRank.setFont(new Font("±¼¸²", Font.BOLD, 12));
		lblGrdRank.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrdRank.setBounds(594, 472, 59, 28);
		myInfoPanel.add(lblGrdRank);

		JLabel lblQuizRank = new JLabel(dao.selectGetQuiz(Login.staticId).get(0).getQuizRank());
		lblQuizRank.setFont(new Font("±¼¸²", Font.BOLD, 12));
		lblQuizRank.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuizRank.setBounds(594, 504, 59, 28);
		myInfoPanel.add(lblQuizRank);

		JLabel lblAllRank = new JLabel(allAvg + "");
		lblAllRank.setFont(new Font("±¼¸²", Font.BOLD, 12));
		lblAllRank.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllRank.setBounds(594, 538, 59, 28);
		myInfoPanel.add(lblAllRank);

		JLabel lblIn = new JLabel(attVO.getAtt());
		lblIn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		lblIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIn.setBounds(24, 504, 52, 23);
		myInfoPanel.add(lblIn);

		JLabel lblLate = new JLabel(attVO.getLate());
		lblLate.setFont(new Font("±¼¸²", Font.BOLD, 12));
		lblLate.setHorizontalAlignment(SwingConstants.CENTER);
		lblLate.setBounds(109, 504, 52, 23);
		myInfoPanel.add(lblLate);

		JLabel lblEarlve = new JLabel(attVO.getLeave());
		lblEarlve.setFont(new Font("±¼¸²", Font.BOLD, 12));
		lblEarlve.setHorizontalAlignment(SwingConstants.CENTER);
		lblEarlve.setBounds(192, 504, 52, 23);
		myInfoPanel.add(lblEarlve);

		JLabel lblOut = new JLabel(attVO.getOut());
		lblOut.setFont(new Font("±¼¸²", Font.BOLD, 12));
		lblOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblOut.setBounds(278, 504, 52, 23);
		myInfoPanel.add(lblOut);

		JLabel lblAbs = new JLabel(attVO.getAbs());
		lblAbs.setFont(new Font("±¼¸²", Font.BOLD, 12));
		lblAbs.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbs.setBounds(365, 504, 52, 23);
		myInfoPanel.add(lblAbs);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(663, 101, 325, 140);
		myInfoPanel.add(scrollPane);

		table = new JTable(data, col);
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		scrollPane.setViewportView(table);

		lblTodayScore = new JLabel("");
		lblTodayScore.setFont(new Font("±¼¸²", Font.BOLD, 15));
		lblTodayScore.setBounds(820, 294, 109, 38);
		myInfoPanel.add(lblTodayScore);

		JLabel lblTotalScore = new JLabel(dao.selectGetQuizScore(Login.staticId).get(0).getQuizScore());
		lblTotalScore.setFont(new Font("±¼¸²", Font.BOLD, 15));
		lblTotalScore.setBounds(820, 335, 109, 38);
		myInfoPanel.add(lblTotalScore);

		JLabel lblNeedSt = new JLabel("");
		lblNeedSt.setBounds(728, 526, 205, 43);
		myInfoPanel.add(lblNeedSt);

		JLabel lblCheck1 = new JLabel("");
		lblCheck1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheck1.setBounds(728, 468, 30, 28);
		myInfoPanel.add(lblCheck1);

		JLabel lblCheck2 = new JLabel("");
		lblCheck2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheck2.setBounds(756, 468, 30, 28);
		myInfoPanel.add(lblCheck2);

		JLabel lblCheck3 = new JLabel("");
		lblCheck3.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheck3.setBounds(786, 468, 30, 28);
		myInfoPanel.add(lblCheck3);

		JLabel lblCheck4 = new JLabel("");
		lblCheck4.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheck4.setBounds(817, 468, 30, 28);
		myInfoPanel.add(lblCheck4);

		JLabel lblCheck5 = new JLabel("");
		lblCheck5.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheck5.setBounds(847, 468, 30, 28);
		myInfoPanel.add(lblCheck5);

		JLabel lblCheck6 = new JLabel("");
		lblCheck6.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheck6.setBounds(877, 468, 30, 28);
		myInfoPanel.add(lblCheck6);

		JLabel lblCheck7 = new JLabel("");
		lblCheck7.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheck7.setBounds(906, 468, 30, 28);
		myInfoPanel.add(lblCheck7);

		JLabel lblCheck[] = { lblCheck1, lblCheck2, lblCheck3, lblCheck4, lblCheck5, lblCheck6, lblCheck7 };

		JButton btnCheckButton = new JButton("\uD655\uC778");
		btnCheckButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < QuizPanel.rightWrong.length; i++) {
					if (QuizPanel.rightWrong[i]) {
						lblCheck[i].setText("O");

					} else if (!QuizPanel.rightWrong[i]) {
						lblCheck[i].setText("X");
						subjectToStudy += dao.getQuizQuiz_Subject(i);
					}
				}
				lblNeedSt.setText(subjectToStudy);
			}
		});
		btnCheckButton.setBounds(929, 398, 59, 28);
		myInfoPanel.add(btnCheckButton);
		JLabel background = new JLabel(new ImageIcon(path + "Images/MyInfo.png"));
		background.setBounds(0, 0, 1000, 600);
		myInfoPanel.add(background);
	}

	public JPanel getMyInfoPanel() {
		return myInfoPanel;
	}
}