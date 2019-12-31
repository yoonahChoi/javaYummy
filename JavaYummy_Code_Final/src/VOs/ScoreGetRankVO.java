package VOs;

public class ScoreGetRankVO {
	
	private String rowNum;
	private String memberId;
	private String sumScore;
	
	public ScoreGetRankVO(String rowNum, String memberId, String sumScore) {
		super();
		this.rowNum = rowNum;
		this.memberId = memberId;
		this.sumScore = sumScore;
	}

	public String getRowNum() {
		return rowNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getSumScore() {
		return sumScore;
	}
	
	
	
	
	
	
	}

