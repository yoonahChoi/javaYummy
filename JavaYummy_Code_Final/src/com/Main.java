package com;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.dao.BoardDAO;
import com.dao.GradeSystemDAO;
import com.notice.NoticeInsert;
import com.notice.NoticeSelect;

import Panels.BoardPanel;
import Panels.MyInfoPanel;
import Panels.QuizPanel;
import Panels.RankingPanel;
import VOs.BoardVO;
import VOs.MemberVO;
import VOs.NoticeVO;

public class Main extends JFrame {

	private JPanel contentPane;
	private JPanel layerPanel;
	private CardLayout cl_layerPanel = new CardLayout(0, 0);
	GradeSystemDAO dao = new GradeSystemDAO();
	BoardDAO boardDAO = new BoardDAO();
	BoardVO notice;
	QuizPanel quizPanel = new QuizPanel();
	BoardPanel boardPanel = new BoardPanel();
	MyInfoPanel myInfoPanel = new MyInfoPanel();
	RankingPanel rankingPanel = new RankingPanel();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		String id = Login.staticId;
		String path = Main.class.getResource("").getPath();
		MemberVO result = dao.selectMember(id);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1290, 755);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel NavPanel = new JPanel();
		NavPanel.setBounds(1000, 91, 280, 353);

		contentPane.add(NavPanel);
		NavPanel.setLayout(null);

		JLabel btnQuiz = new JLabel("");
		btnQuiz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cl_layerPanel.show(layerPanel, "name1");
			}
		});
		btnQuiz.setBounds(27, 42, 222, 52);
		NavPanel.add(btnQuiz);

		JLabel btnBoard = new JLabel("");
		btnBoard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cl_layerPanel.show(layerPanel, "name2");
			}
		});
		btnBoard.setBounds(27, 115, 216, 52);
		NavPanel.add(btnBoard);

		JLabel btnMyInfo = new JLabel("");
		btnMyInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cl_layerPanel.show(layerPanel, "name3");
			}
		});
		btnMyInfo.setBounds(27, 181, 222, 52);
		NavPanel.add(btnMyInfo);

		JLabel btnRanking = new JLabel("");
		btnRanking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cl_layerPanel.show(layerPanel, "name4");
			}
		});
		btnRanking.setBounds(27, 252, 216, 52);
		NavPanel.add(btnRanking);

		JLabel lblImage2 = new JLabel(new ImageIcon(path + "Images/Nav.png"));
		lblImage2.setBounds(0, 0, 280, 353);
		NavPanel.add(lblImage2);

		// ¿©±â¼­ºÎÅÍ µî±Þº° ±×¸²º¯È¯

		layerPanel = new JPanel();
		layerPanel.setBounds(0, 91, 1000, 600);
		contentPane.add(layerPanel);
		layerPanel.setLayout(cl_layerPanel);

		layerPanel.add(quizPanel.getQuizPanel(), "name1");
		layerPanel.add(boardPanel.getBoardPanel(), "name2");
		layerPanel.add(myInfoPanel.getMyInfoPanel(), "name3");
		layerPanel.add(rankingPanel.getRankingPanel(), "name4");
		
		NoticeVO noticeVO = boardDAO.selectNotice();
		notice = boardDAO.selectBoard(noticeVO.getBoardNo(), noticeVO.getTextNO());
		
		JLabel lblNotice = new JLabel(notice.getText());
		lblNotice.setFont(new Font("±¼¸²", Font.BOLD, 15));
		lblNotice.setBounds(186, 30, 656, 41);
		contentPane.add(lblNotice);

		JLabel lblName = new JLabel(result.getName());
		lblName.setBounds(1045, 491, 68, 22);
		lblName.setFont(new Font("±¼¸²", Font.BOLD, 16));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblName);

		JLabel lblLevel = new JLabel(result.getLevel());
		lblLevel.setBounds(1157, 546, 68, 28);
		lblLevel.setFont(new Font("±¼¸²", Font.BOLD, 12));
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLevel);

		JLabel lblTeam = new JLabel(result.getTeamno());
		lblTeam.setBounds(1157, 596, 68, 28);
		lblTeam.setFont(new Font("±¼¸²", Font.BOLD, 12));
		lblTeam.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTeam);
		contentPane.setBounds(100, 100, 1295, 759);

		JPanel panel = new JPanel();
		panel.setBounds(1045, 523, 95, 95);
		contentPane.add(panel);

		JLabel btnNoticeInsert = new JLabel("");
		btnNoticeInsert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				NoticeInsert.main(null);
			}
		});
		btnNoticeInsert.setBounds(913, 30, 43, 41);
		contentPane.add(btnNoticeInsert);
		
		JLabel btnNoticeSet = new JLabel("");
		btnNoticeSet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NoticeSelect.main(null);
			}
		});
		btnNoticeSet.setBounds(957, 30, 43, 41);
		contentPane.add(btnNoticeSet);

		JLabel lblImage = new JLabel(new ImageIcon(Main.class.getResource("/com/Images/Main.png")));
		lblImage.setBounds(0, 0, 1280, 720);
		contentPane.add(lblImage);

		int changeLevel = dao.changeLevel();
		JLabel lblImage1 = null;
		if (changeLevel == 1) {
			lblImage1 = new JLabel(new ImageIcon(path + "Images/level1.png"));
		} else if (changeLevel == 2) {
			lblImage1 = new JLabel(new ImageIcon(path + "Images/level2.png"));
		} else if (changeLevel == 3) {
			lblImage1 = new JLabel(new ImageIcon(path + "Images/level3.png"));
		} else if (changeLevel == 4) {
			lblImage1 = new JLabel(new ImageIcon(path + "Images/level4.png"));
		} else if (changeLevel == 5) {
			lblImage1 = new JLabel(new ImageIcon(path + "Images/level5.png"));
		}
		panel.add(lblImage1);
	}
}
