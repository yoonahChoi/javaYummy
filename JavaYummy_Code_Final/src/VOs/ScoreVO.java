package VOs;

public class ScoreVO {
	private String subNo;
	private String id;
	private String sub;
	private String score;
	private String date;

	public ScoreVO(String subNo, String id, String sub, String score, String date) {
		this.subNo = subNo;
		this.id = id;
		this.sub = sub;
		this.score = score;
		this.date = date;
	}

	public String getSubNo() {
		return subNo;
	}

	public String getId() {
		return id;
	}

	public String getSub() {
		return sub;
	}

	public String getScore() {
		return score;
	}

	public String getDate() {
		return date;
	}

	public void setSubNo(String subNo) {
		this.subNo = subNo;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

}
