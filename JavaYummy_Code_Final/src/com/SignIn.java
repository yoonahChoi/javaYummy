package com;
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

public class SignIn {

	private JFrame frame;
	private JTextField textId;
	private JTextField textPw;
	private JTextField textPwck;
	private JTextField textName;
	private JLabel btnBack;
	private JLabel btnSignIn;
	private JLabel btnIdCheck;
	private boolean check = true;
	GradeSystemDAO dao = new GradeSystemDAO();
	private JTextField textTeam;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn window = new SignIn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SignIn() {
		initialize();
	}

	private void initialize() {
		String path = Main.class.getResource("").getPath();
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", new ColorUIResource(255, 255, 255));
		UI.put("Panel.background", new ColorUIResource(255, 255, 255));
		
		frame = new JFrame();
		JLabel lblImage = new JLabel(new ImageIcon(path +"Images/Sign2.png"));
		lblImage.setBounds(0, 0, 525, 700);
		frame.getContentPane().setLayout(null);

		textId = new JTextField();
		textId.setBounds(121, 151, 178, 29);
		frame.getContentPane().add(textId);
		textId.setColumns(10);

		textPw = new JTextField();
		textPw.setColumns(10);
		textPw.setBounds(121, 242, 178, 29);
		frame.getContentPane().add(textPw);

		textPwck = new JTextField();
		textPwck.setColumns(10);
		textPwck.setBounds(121, 331, 178, 29);
		frame.getContentPane().add(textPwck);

		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(121, 418, 178, 29);
		frame.getContentPane().add(textName);

		btnBack = new JLabel("");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Login.main(null);
			}
		});
		
		textTeam = new JTextField();
		textTeam.setBounds(121, 509, 178, 29);
		frame.getContentPane().add(textTeam);
		textTeam.setColumns(10);
		btnBack.setBounds(84, 564, 122, 45);
		frame.getContentPane().add(btnBack);

		btnSignIn = new JLabel("");
		btnSignIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = textId.getText();
				String pw = textPw.getText();
				String pwCheck = textPwck.getText();
				String name = textName.getText();
				String team = textTeam.getText();
				
				if(check) {
					JOptionPane.showMessageDialog(null, "중복확인을 해주세요", "중복확인", JOptionPane.INFORMATION_MESSAGE);
				}else {
					if (pw.equals(pwCheck)) {
						int cnt = dao.join(id, pw, name,Integer.parseInt(team));
						if (cnt > 0) {
							JOptionPane.showMessageDialog(null, "회원가입 성공", "회원가입", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
							Login.main(null);
						} else {
							JOptionPane.showMessageDialog(null, "회원가입 실패", "회원가입", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다.", "비밀번호", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnSignIn.setBounds(324, 564, 122, 45);
		frame.getContentPane().add(btnSignIn);

		btnIdCheck = new JLabel("");
		btnIdCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = textId.getText();
				check = dao.nameCon(id);
				if (check) {
					JOptionPane.showMessageDialog(null, "사용할 수 없는 아이디 입니다.", "중복확인", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "사용할 수 있는 아이디 입니다.", "중복확인", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnIdCheck.setBounds(364, 151, 82, 29);
		frame.getContentPane().add(btnIdCheck);
		frame.getContentPane().add(lblImage);
		frame.setVisible(true);
		frame.setBounds(100, 100, 542, 738);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
