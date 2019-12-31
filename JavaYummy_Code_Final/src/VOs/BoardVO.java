package VOs;

public class BoardVO {
	private String boardNo;
	private String textNo;
	private String title;
	private String id;
	private String text;
	private String date;
	private String view;

	public BoardVO(String getBoardNo, String textNo2, String title, String id, String text, String date, String view2) {
		this.boardNo = getBoardNo;
		this.textNo = textNo2;
		this.title = title;
		this.id = id;
		this.text = text;
		this.date = date;
		this.view = view2;
	}

	public String getBoardNo() {
		return boardNo;
	}

	public String getTextNo() {
		return textNo;
	}

	public String getTitle() {
		return title;
	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public String getDate() {
		return date;
	}

	public String getView() {
		return view;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public void setTextNo(String textNo) {
		this.textNo = textNo;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setView(String view) {
		this.view = view;
	}

}
