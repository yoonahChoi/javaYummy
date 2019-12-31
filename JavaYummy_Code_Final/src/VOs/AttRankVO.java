package VOs;

public class AttRankVO {
	private String rowNum;
	private String member_Id;
	private String atts;

	public AttRankVO(String rowNum, String member_Id, String atts) {
		super();
		this.rowNum = rowNum;
		this.member_Id = member_Id;
		this.atts = atts;
	}

	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}

	public String getMember_Id() {
		return member_Id;
	}

	public void setMember_Id(String member_Id) {
		this.member_Id = member_Id;
	}

	public String getAtts() {
		return atts;
	}

	public void setAtts(String atts) {
		this.atts = atts;
	}

}
