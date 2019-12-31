package VOs;

public class QuizVO {
	private String quizNo;
	private String id;
	private String type;
	private String date;
	private String point;
	private String text;
	private String subNo;
	private String answer;
	
	public QuizVO(String quizNo, String id, String type, String date, String point, String text, String subNo,
			String answer) {
		super();
		this.quizNo = quizNo;
		this.id = id;
		this.type = type;
		this.date = date;
		this.point = point;
		this.text = text;
		this.subNo = subNo;
		this.answer = answer;
	}
	
	public String getQuizNo() {
		return quizNo;
	}
	public String getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public String getDate() {
		return date;
	}
	public String getPoint() {
		return point;
	}
	public String getText() {
		return text;
	}
	public String getSubNo() {
		return subNo;
	}
	public String getAnswer() {
		return answer;
	}
	public void setQuizNo(String quizNo) {
		this.quizNo = quizNo;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setSubNo(String subNo) {
		this.subNo = subNo;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
