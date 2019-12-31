package VOs;

public class MemberVO {
	private String id;
	private String pw;
	private String name;
	private String teamno;
	private String level;
	
	public MemberVO(String id, String pw, String name, String teamno, String level) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.teamno = teamno;
		this.level = level;
	}
	
	public String getId() {
		return id;
	}
	public String getPw() {
		return pw;
	}
	public String getName() {
		return name;
	}
	public String getTeamno() {
		return teamno;
	}
	public String getLevel() {
		return level;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTeamno(String teamno) {
		this.teamno = teamno;
	}
	public void setLevel(String level) {
		this.level = level;
	}

}