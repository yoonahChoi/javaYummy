package Panels;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.BoardDAO;
import com.BoardDelete;
import com.BoardInsert;
import com.BoardSelect;
import com.BoardUpdate;
import com.Main;

import VOs.BoardVO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BoardPanel extends JPanel {
	public JPanel boardPanel = new JPanel();
	BoardDAO dao = new BoardDAO();
	private JTable table;
	public static int boardNo = 1;
	public static BoardVO result;
	ArrayList<BoardVO> boardList;

	public BoardPanel() {
		String path = Main.class.getResource("").getPath();
		table = setTable(1);
		boardPanel.setBounds(0, 0, 1000, 600);
		boardPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(264, 87, 688, 481));
		boardPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 27, 664, 376);
		panel.add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		JLabel btnInsert = new JLabel("");
		btnInsert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BoardInsert.main(null);
			}
		});
		btnInsert.setBounds(233, 424, 133, 36);
		panel.add(btnInsert);
		
		JLabel btnUpdate = new JLabel("");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BoardUpdate.main(null);
			}
		});
		btnUpdate.setBounds(388, 424, 133, 36);
		panel.add(btnUpdate);
		
		JLabel btnDelete = new JLabel("");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				BoardDelete.main(null);
			}
		});
		btnDelete.setBounds(543, 424, 133, 36);
		panel.add(btnDelete);
		
		JLabel boardBack = new JLabel(new ImageIcon(BoardPanel.class.getResource("/com/Images/BoardTable.png")));
		boardBack.setBounds(0, 0, 688, 481);
		panel.add(boardBack);
		
		JLabel btnCodeSub = new JLabel("");
		btnCodeSub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boardNo = 1;
				table = setTable(1);
				scrollPane.setViewportView(table);
				
			}
		});
		btnCodeSub.setBounds(49, 89, 183, 54);
		boardPanel.add(btnCodeSub);

		JLabel btnCodeDebate = new JLabel("");
		btnCodeDebate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boardNo = 2;
				table = setTable(2);
				scrollPane.setViewportView(table);
			}
		});
		btnCodeDebate.setBounds(49, 163, 183, 49);
		boardPanel.add(btnCodeDebate);

		JLabel btnFree = new JLabel("");
		btnFree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boardNo = 3;
				table = setTable(3);
				scrollPane.setViewportView(table);
			}
		});
		btnFree.setBounds(49, 232, 183, 54);
		boardPanel.add(btnFree);

		JLabel btnQnA = new JLabel("");
		btnQnA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boardNo = 4;
				table = setTable(4);
				scrollPane.setViewportView(table);
			}
		});
		btnQnA.setBounds(49, 308, 183, 54);
		boardPanel.add(btnQnA);
		
		JLabel background = new JLabel(new ImageIcon(path + "Images/Board.png"));
		background.setBounds(0, 0, 1000, 600);
		boardPanel.add(background);
	}

	public JPanel getBoardPanel() {

		return boardPanel;
	}

	public JTable setTable(int boardNo) {
		boardList = dao.selectBoard(boardNo);

		String[] col = { "글번호", "제목", "작성자", "작성일", "조회수" };
		String[][] data = new String[boardList.size()][5];

		for (int i = 0; i < data.length; i++) {
			data[i][0] = boardList.get(i).getTextNo();
			data[i][1] = boardList.get(i).getTitle();
			data[i][2] = boardList.get(i).getId();
			data[i][3] = boardList.get(i).getDate();
			data[i][4] = boardList.get(i).getView();
		}

		DefaultTableModel model = new DefaultTableModel(data, col) {
			public boolean isCellEditable(int i, int c) {

				return false;

			}
		};

		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				int selectedBoardNo = boardNo;
				int selectedTextNo = Integer.parseInt(model2.getValueAt(selectedRowIndex, 0).toString());
				int selectedView = Integer.parseInt(model2.getValueAt(selectedRowIndex, 4).toString());
				selectedView++;
				dao.updateView(selectedBoardNo, selectedTextNo, selectedView);
				result = dao.selectBoard(selectedBoardNo, selectedTextNo);
				BoardSelect.main(null);
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(500);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.repaint();
		table.setRowHeight(30);
		
		return table;
	}
}