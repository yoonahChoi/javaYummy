package VOs;

public class ScoreGetQuizVO {

	private String memberId;
	private String subjectNo;
	private String quizScore;
	private String quizRank;

	public ScoreGetQuizVO(String memberId, String subjectNo, String quizScore, String quizRank) {
		super();
		this.memberId = memberId;
		this.subjectNo = subjectNo;
		this.quizScore = quizScore;
		this.quizRank = quizRank;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	public String getQuizScore() {
		return quizScore;
	}

	public void setQuizScore(String quizScore) {
		this.quizScore = quizScore;
	}

	public String getQuizRank() {
		return quizRank;
	}

	public void setQuizRank(String quizRank) {
		this.quizRank = quizRank;
	}

}
