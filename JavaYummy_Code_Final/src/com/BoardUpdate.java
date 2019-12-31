package com;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Panels.BoardPanel;
import VOs.BoardVO;
import VOs.MemberVO;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardUpdate {

	private JFrame frame;
	GradeSystemDAO dao = new GradeSystemDAO();
	BoardDAO boardDAO = new BoardDAO();
	private JTextField textTitle;
	private JTextField textTextNo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardUpdate window = new BoardUpdate();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BoardUpdate() {
		initialize();
	}

	private void initialize() {
		String id = Login.staticId;
		int boardNo = BoardPanel.boardNo;
		MemberVO result = dao.selectMember(id);

		frame = new JFrame();
		frame.getContentPane().setBackground(Color.CYAN);
		frame.getContentPane().setLayout(null);

		JLabel lblNo = new JLabel(Integer.toString(boardNo));
		lblNo.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNo.setBounds(105, 22, 43, 21);
		frame.getContentPane().add(lblNo);

		textTitle = new JTextField();
		textTitle.setBounds(76, 56, 469, 30);
		frame.getContentPane().add(textTitle);
		textTitle.setColumns(10);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(76, 100, 469, 242);
		frame.getContentPane().add(textArea);

		textTextNo = new JTextField();
		textTextNo.setBounds(449, 19, 97, 24);
		frame.getContentPane().add(textTextNo);
		textTextNo.setColumns(10);

		JLabel lblWriter = new JLabel(result.getName());
		lblWriter.setBounds(77, 363, 78, 15);
		frame.getContentPane().add(lblWriter);
		
		JLabel btnUpdate = new JLabel("");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String title = textTitle.getText();
				String text = textArea.getText();
				int textNo = Integer.parseInt(textTextNo.getText());
				BoardVO result = boardDAO.selectBoard(boardNo, textNo);
				if (id.equals(result.getId())) {
					int cnt = boardDAO.updateBoard(boardNo, textNo, title, text);
					if (cnt > 0) {
						JOptionPane.showMessageDialog(null, "게시글 수정 완료", "게시글 수정 완료", JOptionPane.INFORMATION_MESSAGE);
					}
					frame.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "수정할 권한이 없습니다.", "게시글 수정 실패", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUpdate.setBounds(425, 363, 118, 40);
		frame.getContentPane().add(btnUpdate);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(BoardUpdate.class.getResource("/com/Images/BoardUpdate.png")));
		background.setBounds(0, 0, 588, 431);
		frame.getContentPane().add(background);
		frame.setBounds(100, 100, 605, 470);
		frame.setTitle("게시글수정");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
