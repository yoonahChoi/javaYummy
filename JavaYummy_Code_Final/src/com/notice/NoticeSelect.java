package com.notice;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.Login;
import com.dao.BoardDAO;

import VOs.BoardVO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.ImageIcon;

public class NoticeSelect {

	private JFrame frame;
	private JTable table;
	private JTextField textTextNo;
	BoardDAO dao = new BoardDAO();
	ArrayList<BoardVO> boardList;
	static BoardVO result;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoticeSelect window = new NoticeSelect();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NoticeSelect() {
		initialize();
	}

	private void initialize() {
		String id = Login.staticId;
		int boardNo = 5;
		frame = new JFrame();
		frame.setBounds(100, 100, 606, 453);
		frame.setTitle("공지등록");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 70, 468, 247);
		frame.getContentPane().add(scrollPane);

		table = setTable(Login.staticId);
		scrollPane.setViewportView(table);

		textTextNo = new JTextField();
		textTextNo.setBounds(190, 334, 166, 38);
		frame.getContentPane().add(textTextNo);
		textTextNo.setColumns(10);
		
		JLabel btnNoticeSet = new JLabel("");
		btnNoticeSet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cnt = dao.updateNotice(boardNo, Integer.parseInt(textTextNo.getText()));
				if (cnt > 0) {
					JOptionPane.showMessageDialog(null, "공지 등록 완료", "공지 등록", JOptionPane.INFORMATION_MESSAGE);
				}
				frame.dispose();
			}
		});
		btnNoticeSet.setBounds(411, 334, 118, 38);
		frame.getContentPane().add(btnNoticeSet);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(NoticeSelect.class.getResource("/com/Images/NoticeSelect.png")));
		background.setBounds(0, 0, 588, 413);
		frame.getContentPane().add(background);
	}

	public JTable setTable(String id) {
		boardList = dao.selectMyBoard(id);

		String[] col = { "글번호", "제목", "내용", "작성일" };
		String[][] data = new String[boardList.size()][4];

		for (int i = 0; i < data.length; i++) {
			data[i][0] = boardList.get(i).getTextNo();
			data[i][1] = boardList.get(i).getTitle();
			data[i][2] = boardList.get(i).getText();
			data[i][3] = boardList.get(i).getDate();
		}

		DefaultTableModel model = new DefaultTableModel(data, col) {
			public boolean isCellEditable(int i, int c) {

				return false;

			}
		};

		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				textTextNo.setText(model2.getValueAt(selectedRowIndex, 0).toString());

			}
		});

		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.repaint();
		table.setRowHeight(30);

		return table;
	}
}
