package com;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Panels.BoardPanel;
import VOs.BoardVO;
import VOs.CommentsVO;
import VOs.MemberVO;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

public class BoardSelect {

	private JFrame frame;
	private JTable table;
	private JTextField textComments;
	GradeSystemDAO dao = new GradeSystemDAO();
	BoardDAO boardDAO = new BoardDAO();
	ArrayList<CommentsVO> commentsList;
	

	public static void main(String[] args) {
	
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardSelect window = new BoardSelect();
					
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BoardSelect() {
		initialize();
	}

	private void initialize() {
		String id = Login.staticId;
		BoardVO boardResult = BoardPanel.result;
		MemberVO result = dao.selectMember(id);
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setTitle("°Ô½Ã±Û");
		frame.getContentPane().setLayout(null);
		
		table = setTable(Integer.parseInt(boardResult.getBoardNo()),Integer.parseInt(boardResult.getTextNo()));
		JLabel lblNo = new JLabel(boardResult.getBoardNo());
		lblNo.setFont(new Font("±¼¸²", Font.BOLD, 12));
		lblNo.setBounds(117, 10, 82, 15);
		frame.getContentPane().add(lblNo);
		
		JLabel lblgetTitle = new JLabel(boardResult.getTitle());
		lblgetTitle.setBounds(90, 42, 463, 25);
		frame.getContentPane().add(lblgetTitle);
		
		JLabel lblgetText = new JLabel(boardResult.getText());
		lblgetText.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		lblgetText.setVerticalAlignment(SwingConstants.TOP);
		lblgetText.setBounds(90, 84, 463, 244);
		frame.getContentPane().add(lblgetText);
		
		MemberVO memVO = dao.selectMember(boardResult.getId()); 
		JLabel lblgetWriter = new JLabel(memVO.getName());
		lblgetWriter.setBounds(89, 333, 93, 15);
		frame.getContentPane().add(lblgetWriter);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 397, 467, 190);
		frame.getContentPane().add(scrollPane);
		
		textComments = new JTextField();
		textComments.setBounds(87, 596, 391, 21);
		frame.getContentPane().add(textComments);
		textComments.setColumns(10);
		
		JLabel lblComWriter = new JLabel(result.getName());
		lblComWriter.setBounds(90, 627, 72, 15);
		frame.getContentPane().add(lblComWriter);
		
		scrollPane.setViewportView(table);
		
		JLabel btnComUpdate = new JLabel("");
		btnComUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				table = setTable(Integer.parseInt(boardResult.getBoardNo()),Integer.parseInt(boardResult.getTextNo()));
				scrollPane.setViewportView(table);
			}
		});
		btnComUpdate.setBounds(421, 359, 132, 28);
		frame.getContentPane().add(btnComUpdate);
		
		JLabel btnComInsert = new JLabel("");
		btnComInsert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String comments = textComments.getText();
				int cnt = boardDAO.InsertComments(Integer.parseInt(boardResult.getBoardNo()),Integer.parseInt(boardResult.getTextNo()),id, comments);
				if(cnt>0) {
					JOptionPane.showMessageDialog(null, "´ñ±Û µî·Ï ¼º°ø", "´ñ±Û µî·Ï", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnComInsert.setBounds(483, 596, 70, 21);
		frame.getContentPane().add(btnComInsert);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(BoardSelect.class.getResource("/com/Images/BoardSelect2.png")));
		background.setBounds(0, 0, 588, 703);
		frame.getContentPane().add(background);
		frame.setBounds(100, 100, 604, 741);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public JTable setTable(int boardNo, int textNo) {
		commentsList = boardDAO.selectComments(boardNo, textNo);
		
		String[] col = { "´ñ±Û", "ÀÛ¼ºÀÚ", "ÀÛ¼ºÀÏ"};
		String[][] data = new String[commentsList.size()][3];

		for (int i = 0; i < data.length; i++) {
			data[i][0] = commentsList.get(i).getComment();
			data[i][1] = commentsList.get(i).getId();
			data[i][2] = commentsList.get(i).getDate();
		}

		DefaultTableModel model = new DefaultTableModel(data, col) {
			public boolean isCellEditable(int i, int c) {

				return false;

			}
		};

		table = new JTable(model);

		table.getColumnModel().getColumn(0).setPreferredWidth(500);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.repaint();
		table.setRowHeight(25);
		
		return table;
	}
}
