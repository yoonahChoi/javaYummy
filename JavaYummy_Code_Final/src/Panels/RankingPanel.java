package Panels;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.GradeSystemDAO;
import com.Main;

public class RankingPanel extends JPanel {
	public JPanel rankingPanel = new JPanel();
	GradeSystemDAO dao = new GradeSystemDAO();

	public RankingPanel() {
		setLayout(null);
		String path = Main.class.getResource("").getPath();
		rankingPanel.setBounds(0, 0, 1000, 600);
		rankingPanel.setLayout(null);

		String[] topThreeATTS = dao.selectTopThreeATTSGrade();
		String[] FinalThreeGrade = dao.selectFinalThreeGrade();

		JLabel lblFinalRank1 = new JLabel(dao.selectMember(FinalThreeGrade[0]).getName());
		lblFinalRank1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblFinalRank1.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinalRank1.setBounds(458, 125, 82, 35);
		rankingPanel.add(lblFinalRank1);
		
		JLabel lblFinalRank2 = new JLabel(dao.selectMember(FinalThreeGrade[1]).getName());
		lblFinalRank2.setFont(new Font("Dialog", Font.BOLD, 18));
		lblFinalRank2.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinalRank2.setBounds(366, 170, 82, 35);
		rankingPanel.add(lblFinalRank2);

		JLabel lblFinalRank3 = new JLabel(dao.selectMember(FinalThreeGrade[2]).getName());
		lblFinalRank3.setFont(new Font("Dialog", Font.BOLD, 18));
		lblFinalRank3.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinalRank3.setBounds(551, 186, 82, 35);
		rankingPanel.add(lblFinalRank3);
		
		JLabel lblAttRank1 = new JLabel(dao.selectMember(topThreeATTS[0]).getName());
		lblAttRank1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAttRank1.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 13));
		lblAttRank1.setBounds(203, 394, 57, 26);
		rankingPanel.add(lblAttRank1);
		
		JLabel lblAttRank2 = new JLabel(dao.selectMember(topThreeATTS[1]).getName());
		lblAttRank2.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 13));
		lblAttRank2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAttRank2.setBounds(144, 426, 57, 27);
		rankingPanel.add(lblAttRank2);

		JLabel lblAttRank3 = new JLabel(dao.selectMember(topThreeATTS[2]).getName());
		lblAttRank3.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 13));
		lblAttRank3.setHorizontalAlignment(SwingConstants.CENTER);
		lblAttRank3.setBounds(266, 438, 55, 24);
		rankingPanel.add(lblAttRank3);

		String[] topThreeGrade = dao.selectTopThreeGrade();
		
		JLabel lblScoreRank1 = new JLabel(dao.selectMember(topThreeGrade[0]).getName());
		lblScoreRank1.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 13));
		lblScoreRank1.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreRank1.setBounds(469, 394, 55, 24);
		rankingPanel.add(lblScoreRank1);

		JLabel lblScoreRank2 = new JLabel(dao.selectMember(topThreeGrade[1]).getName());
		lblScoreRank2.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 13));
		lblScoreRank2.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreRank2.setBounds(410, 427, 55, 24);
		rankingPanel.add(lblScoreRank2);

		JLabel lblScoreRank3 = new JLabel(dao.selectMember(topThreeGrade[2]).getName());
		lblScoreRank3.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 13));
		lblScoreRank3.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreRank3.setBounds(530, 438, 55, 24);
		rankingPanel.add(lblScoreRank3);
		
		String[] topTTQuizGrade = dao.selectTopThreeQuizGrade();
		JLabel lblQuizRank1 = new JLabel(dao.selectMember(topTTQuizGrade[0]).getName());
		lblQuizRank1.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 13));
		lblQuizRank1.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuizRank1.setBounds(740, 394, 55, 24);
		rankingPanel.add(lblQuizRank1);

		JLabel lblQuizRank2 = new JLabel(dao.selectMember(topTTQuizGrade[1]).getName());
		lblQuizRank2.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 13));
		lblQuizRank2.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuizRank2.setBounds(680, 426, 55, 24);
		rankingPanel.add(lblQuizRank2);

		JLabel lblQuizRank3 = new JLabel(dao.selectMember(topTTQuizGrade[2]).getName());
		lblQuizRank3.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 13));
		lblQuizRank3.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuizRank3.setBounds(804, 438, 55, 24);
		rankingPanel.add(lblQuizRank3);

		JLabel background = new JLabel(new ImageIcon(path + "Images/Ranking.png"));
		background.setBounds(0, 0, 1000, 600);
		rankingPanel.add(background);
	}

	public JPanel getRankingPanel() {
		return rankingPanel;
	}

}