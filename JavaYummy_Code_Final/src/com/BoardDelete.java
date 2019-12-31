package com;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import VOs.BoardVO;
import javax.swing.ImageIcon;

public class BoardDelete {

	private JFrame frame;
	private JTable table;
	private JTextField textBoardNo;
	private JTextField textTextNo;
	public static BoardVO result;
	BoardDAO dao = new BoardDAO();
	ArrayList<BoardVO> boardList;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardDelete window = new BoardDelete();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BoardDelete() {
		initialize();
	}

	private void initialize() {
		String id = Login.staticId;

		frame = new JFrame();
		frame.setBounds(100, 100, 607, 470);
		frame.setTitle("게시글 삭제");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 35, 565, 344);
		frame.getContentPane().add(scrollPane);

		textBoardNo = new JTextField();
		textBoardNo.setBounds(125, 389, 97, 24);
		frame.getContentPane().add(textBoardNo);
		textBoardNo.setColumns(10);

		textTextNo = new JTextField();
		textTextNo.setBounds(344, 389, 97, 24);
		frame.getContentPane().add(textTextNo);
		textTextNo.setColumns(10);

		table = setTable(id);
		scrollPane.setViewportView(table);
		
		JLabel btnDelete = new JLabel("");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int boardNo = Integer.parseInt(textBoardNo.getText());
				int textNo = Integer.parseInt(textTextNo.getText());
				BoardVO result = dao.selectBoard(boardNo, textNo);
				if (id.equals(result.getId())) {
					dao.DeleteComments(boardNo, textNo);
					int cnt = dao.DeleteBoard(boardNo, textNo);
					if (cnt > 0) {
						JOptionPane.showMessageDialog(null, "게시글 삭제 완료", "게시글 삭제 완료", JOptionPane.INFORMATION_MESSAGE);
						frame.setVisible(false);
					}
				} else {
					JOptionPane.showMessageDialog(null, "삭제할 권한이 없습니다.", "게시글 삭제 실패", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDelete.setBounds(458, 382, 119, 40);
		frame.getContentPane().add(btnDelete);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(BoardDelete.class.getResource("/com/Images/BoardDelete.png")));
		background.setBounds(0, 0, 588, 431);
		frame.getContentPane().add(background);
	}

	public JTable setTable(String id) {
		boardList = dao.selectMyBoard(id);

		String[] col = { "게시판번호", "글번호", "제목", "작성자", "작성일", "조회수" };
		String[][] data = new String[boardList.size()][6];

		for (int i = 0; i < data.length; i++) {
			data[i][0] = boardList.get(i).getBoardNo();
			data[i][1] = boardList.get(i).getTextNo();
			data[i][2] = boardList.get(i).getTitle();
			data[i][3] = boardList.get(i).getId();
			data[i][4] = boardList.get(i).getDate();
			data[i][5] = boardList.get(i).getView();
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
				textBoardNo.setText(model2.getValueAt(selectedRowIndex, 0).toString());
				textTextNo.setText(model2.getValueAt(selectedRowIndex, 1).toString());
			}
		});

		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.repaint();
		table.setRowHeight(30);

		return table;
	}
}
