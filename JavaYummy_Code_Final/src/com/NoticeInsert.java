package com;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import VOs.MemberVO;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NoticeInsert {

	private JFrame frame;
	GradeSystemDAO dao = new GradeSystemDAO();
	BoardDAO boardDAO = new BoardDAO();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoticeInsert window = new NoticeInsert();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NoticeInsert() {
		initialize();
	}

	private void initialize() {
		String id = Login.staticId;
		int boardNo = 5;
		MemberVO result = dao.selectMember(id);
		frame = new JFrame();
		frame.setBounds(100, 100, 605, 452);
		frame.setTitle("공지글 등록");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTextField textTitle = new JTextField();
		textTitle.setBounds(75, 40, 469, 25);
		frame.getContentPane().add(textTitle);
		textTitle.setColumns(10);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(75, 82, 469, 245);
		frame.getContentPane().add(textArea);

		JLabel lblName = new JLabel(id);
		lblName.setFont(new Font("굴림", Font.BOLD, 17));
		lblName.setBounds(75, 340, 133, 25);
		frame.getContentPane().add(lblName);

		JLabel btnInsert = new JLabel("");
		btnInsert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String title = textTitle.getText();
				String text = textArea.getText();
				if ("admin".equals(id)) {
					int cnt = boardDAO.InsertBoard(boardNo, title, id, text);
					if (cnt > 0) {
						JOptionPane.showMessageDialog(null, "공지 등록 완료", "공지 등록", JOptionPane.INFORMATION_MESSAGE);
					}

					frame.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "공지 작성 권한이 없습니다.", "공지글 쓰기 실패", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnInsert.setBounds(425, 346, 119, 39);
		frame.getContentPane().add(btnInsert);

		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(NoticeInsert.class.getResource("/com/Images/NoticeInsert.png")));
		background.setBounds(0, 0, 588, 413);
		frame.getContentPane().add(background);
	}
}
