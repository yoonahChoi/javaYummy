package com;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import VOs.MemberVO;

public class Login {

	private JFrame frame;
	private JTextField textId;
	private JTextField textPw;
	private JLabel btnLogin;
	private JLabel btnExit;
	public static String staticId;
	GradeSystemDAO dao = new GradeSystemDAO();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		initialize();
	}

	private void initialize() {
		String path = Main.class.getResource("").getPath();
		UIManager.put("OptionPane.background", new ColorUIResource(255, 255, 255));
		UIManager.put("Panel.background", new ColorUIResource(255, 255, 255));

		JLabel lblImage = new JLabel(new ImageIcon(path + "Images/Login.png"));
		lblImage.setBounds(0, 0, 525, 700);
		frame = new JFrame();
		frame.getContentPane().setLayout(null);

		textId = new JTextField();
		textId.setBackground(Color.WHITE);
		textId.setBounds(220, 432, 159, 27);
		frame.getContentPane().add(textId);
		textId.setColumns(10);

		textPw = new JTextField();
		textPw.setColumns(10);
		textPw.setBackground(Color.WHITE);
		textPw.setBounds(220, 481, 159, 27);
		frame.getContentPane().add(textPw);

		JLabel btnSign = new JLabel("");
		btnSign.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				SignIn.main(null);
			}
		});
		btnSign.setBounds(58, 574, 122, 46);
		frame.getContentPane().add(btnSign);

		btnLogin = new JLabel("");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MemberVO result;
				String id = textId.getText();
				String pw = textPw.getText();
				result = dao.selectMember(id);
				if (result == null) {
					JOptionPane.showMessageDialog(null, "로그인 실패", "로그인", JOptionPane.ERROR_MESSAGE);
				} else {
					if (pw.equals(result.getPw())) {
						JOptionPane.showMessageDialog(null, "로그인 성공", "로그인", JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
						staticId = result.getId();
						dao.updateLevel(dao.changeLevel());
						Main.main(null);
					} else {
						JOptionPane.showMessageDialog(null, "로그인 실패", "로그인", JOptionPane.ERROR_MESSAGE);
						textId.setText("");
						textPw.setText("");
					}
				}
			}
		});
		btnLogin.setBounds(204, 574, 117, 46);
		frame.getContentPane().add(btnLogin);

		btnExit = new JLabel("");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		btnExit.setBounds(347, 574, 117, 46);
		frame.getContentPane().add(btnExit);
		frame.getContentPane().add(lblImage);
		frame.setVisible(true);
		frame.setBounds(100, 100, 542, 738);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
