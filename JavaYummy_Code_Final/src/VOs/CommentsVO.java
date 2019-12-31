package VOs;

public class CommentsVO {
	private String boardNo;
	private String textNo;
	private String date;
	private String id;
	private String comment;
	
	public CommentsVO(String boardNo, String textNo, String date, String id, String comment) {
		this.boardNo = boardNo;
		this.textNo = textNo;
		this.date = date;
		this.id = id;
		this.comment = comment;
	}

	public String getBoardNo() {
		return boardNo;
	}

	public String getTextNo() {
		return textNo;
	}

	public String getDate() {
		return date;
	}

	public String getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public void setTextNo(String textNo) {
		this.textNo = textNo;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
}
