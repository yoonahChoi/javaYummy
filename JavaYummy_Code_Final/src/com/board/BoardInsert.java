package com.board;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.Login;
import com.dao.BoardDAO;
import com.dao.GradeSystemDAO;

import Panels.BoardPanel;
import VOs.MemberVO;

import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardInsert {

	private JFrame frame;
	GradeSystemDAO dao = new GradeSystemDAO();
	BoardDAO boardDAO = new BoardDAO();
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardInsert window = new BoardInsert();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BoardInsert() {
		initialize();
	}

	private void initialize() {
		String id = Login.staticId;
		int boardNo = BoardPanel.boardNo;
		MemberVO result = dao.selectMember(id);
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.setTitle("게시글등록");
		frame.setBounds(100, 100, 707, 519);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextField textTitle = new JTextField();
		textTitle.setBounds(86, 52, 556, 25);
		frame.getContentPane().add(textTitle);
		textTitle.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(98, 91, 422, 178);
		frame.getContentPane().add(textArea);
		
		JLabel lblName = new JLabel(result.getName());
		lblName.setFont(new Font("굴림", Font.BOLD, 17));
		lblName.setBounds(86, 417, 133, 25);
		frame.getContentPane().add(lblName);
		
		JLabel lblNo = new JLabel(Integer.toString(boardNo));
		lblNo.setFont(new Font("굴림", Font.BOLD, 20));
		lblNo.setBounds(120, 15, 37, 21);
		frame.getContentPane().add(lblNo);
		
		JLabel btnInsert = new JLabel("");
		btnInsert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String title = textTitle.getText();
				String text = textArea.getText();
				int cnt = boardDAO.InsertBoard(boardNo, title, id, text);
				if(cnt>0) {
					JOptionPane.showMessageDialog(null, "게시글 등록 완료", "게시글 등록", JOptionPane.INFORMATION_MESSAGE);
				}
				frame.setVisible(false);
			}
		});
		btnInsert.setBounds(514, 420, 128, 43);
		frame.getContentPane().add(btnInsert);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(BoardInsert.class.getResource("/com/Images/BoardInsert.png")));
		background.setBounds(0, 0, 690, 480);
		frame.getContentPane().add(background);
	}
}
