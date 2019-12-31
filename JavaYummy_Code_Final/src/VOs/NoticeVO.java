package VOs;

public class NoticeVO {
	private int boardNo;
	private int textNO;

	public NoticeVO(int boardNo, int textNO) {
		this.boardNo = boardNo;
		this.textNO = textNO;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public int getTextNO() {
		return textNO;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public void setTextNO(int textNO) {
		this.textNO = textNO;
	}

}
