package Panels;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.GradeSystemDAO;
import com.Main;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuizPanel extends JPanel {
	public JPanel quizPanel = new JPanel();
	private JTextField textAnswer;
	GradeSystemDAO dao = new GradeSystemDAO();
	private int number = 1;
	private String problemText = dao.selectQuiz(number).getText();
	public static int score = 0;
	public static boolean allsolved = false;

	boolean solved[] = { false, false, false, false, false, false, false };
	static boolean rightWrong[] = { false, false, false, false, false, false, false };

	public QuizPanel() {

		setLayout(null);
		String path = Main.class.getResource("").getPath();
		quizPanel.setBounds(0, 0, 1000, 600);
		quizPanel.setLayout(null);

		JLabel lblShowAnswer = new JLabel("");
		lblShowAnswer.setBounds(729, 330, 237, 124);
		quizPanel.add(lblShowAnswer);

		final JLabel lblShowProblem = new JLabel(problemText);

		JLabel btnNext = new JLabel("");
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (number < 7) {
					number++;
					String quiz = dao.selectQuiz(number).getText();
					String quiz_ln = "<html><body>";

					int begin = 0;
					int end = 800;
					while (true) {
						if (begin + end > quiz.length()) {
							quiz_ln += quiz.substring(begin, quiz.length()) + "</body></html>";
							break;
						} else {
							quiz_ln += quiz.substring(begin, begin + end) + "<br>";
							begin += end;
						}
					}

					lblShowProblem.setText(quiz_ln);
					if (!solved[number - 1]) {
						lblShowAnswer.setText("");
					} else {
						lblShowAnswer.setText(dao.selectQuiz(number).getAnswer());
					}
				} else {
					JOptionPane.showMessageDialog(null, "마지막 문제입니다!", "오류!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNext.setBounds(875, 504, 63, 63);
		quizPanel.add(btnNext);

		textAnswer = new JTextField();
		textAnswer.setColumns(10);
		textAnswer.setBounds(27, 511, 512, 56);
		quizPanel.add(textAnswer);

		JLabel btnCheckAnswer = new JLabel("");
		btnCheckAnswer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (!solved[number - 1]) {
					if (textAnswer.getText().equals(dao.selectQuiz(number).getAnswer())) {
						JOptionPane.showMessageDialog(null, "정답입니다!", "성공!", JOptionPane.INFORMATION_MESSAGE);
						solved[number - 1] = true;
						rightWrong[number - 1] = true;
						lblShowAnswer.setText(dao.selectQuiz(number).getAnswer());
						score += Integer.parseInt(dao.selectQuiz(number).getPoint());
						int yallSolved = 0;
						for (int i = 0; i < solved.length; i++) {
							if (solved[i]) {

								yallSolved++;

							}
						}
						if (yallSolved == 7) {
							JOptionPane.showMessageDialog(null, "점수가 정산됩니다", "암튼", JOptionPane.INFORMATION_MESSAGE);
							int something1 = dao.getScoreQuizScore_Score();
							dao.updateScoreQUIZScore_Score(score + something1);
							MyInfoPanel.lblTodayScore.setText(Integer.toString(QuizPanel.score));
							
						}

					} else {
						JOptionPane.showMessageDialog(null, "오답입니다!", "실패!", JOptionPane.ERROR_MESSAGE);
						solved[number - 1] = true;
						lblShowAnswer.setText(dao.selectQuiz(number).getAnswer());
						int yallSolved = 0;
						for (int i = 0; i < solved.length; i++) {
							if (solved[i]) {
								yallSolved++;
							}
						}
						if (yallSolved == 7) {
							JOptionPane.showMessageDialog(null, "점수가 정산됩니다", "암튼", JOptionPane.INFORMATION_MESSAGE);
							int something1 = dao.getScoreQuizScore_Score();
							dao.updateScoreQUIZScore_Score(score + something1);
							MyInfoPanel.lblTodayScore.setText(Integer.toString(QuizPanel.score));
							
						}

					}
				} else {
					JOptionPane.showMessageDialog(null, "이미 푸신 문제입니다!", "중복!", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnCheckAnswer.setBounds(563, 511, 142, 56);
		quizPanel.add(btnCheckAnswer);

		JLabel btnPrevious = new JLabel("");
		btnPrevious.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (number > 1) {
					number--;
					lblShowProblem.setText(dao.selectQuiz(number).getText());
					if (!solved[number - 1]) {
						lblShowAnswer.setText("");
					} else {
						lblShowAnswer.setText(dao.selectQuiz(number).getAnswer());
					}
				} else {
					JOptionPane.showMessageDialog(null, "첫번째 페이지 입니다!", "오류!", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnPrevious.setBounds(767, 504, 63, 63);
		quizPanel.add(btnPrevious);

		lblShowProblem.setBounds(94, 96, 480, 358);
		quizPanel.add(lblShowProblem);

		JLabel background = new JLabel(new ImageIcon(path + "Images/Quiz2.png"));
		background.setBounds(0, 0, 1000, 600);
		quizPanel.add(background);
	}

	public JPanel getQuizPanel() {
		return quizPanel;
	}

	public void makeProblems(int quizNumber) {
		JPanel panel = new JPanel();
		panel.setBounds(0, 53, 988, 547);
		quizPanel.add(panel);
		panel.setLayout(null);

		JLabel lblShowAnswer = new JLabel("");
		lblShowAnswer.setBounds(728, 280, 237, 124);
		panel.add(lblShowAnswer);

		textAnswer = new JTextField();
		textAnswer.setColumns(10);
		textAnswer.setBounds(25, 458, 512, 56);
		panel.add(textAnswer);

		JLabel btnCheckAnswer = new JLabel("");
		btnCheckAnswer.setBounds(563, 460, 142, 56);
		panel.add(btnCheckAnswer);

		JLabel btnPrevious = new JLabel("");
		btnPrevious.setBounds(767, 453, 63, 63);
		panel.add(btnPrevious);

		JLabel btnNext = new JLabel("");
		btnNext.setBounds(876, 452, 63, 63);
		panel.add(btnNext);

		String problemText = dao.selectQuiz(quizNumber).getText();

		JLabel lblShowProblem = new JLabel(problemText);
		lblShowProblem.setBounds(27, 45, 680, 358);
		panel.add(lblShowProblem);
	}
}