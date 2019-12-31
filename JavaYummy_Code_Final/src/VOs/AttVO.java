package VOs;

public class AttVO {
	private String id;
	private String att;
	private String late;
	private String leave;
	private String out;
	private String abs;

	public AttVO(String id, String att, String late, String leave, String out, String abs) {
		this.id = id;
		this.att = att;
		this.late = late;
		this.leave = leave;
		this.out = out;
		this.abs = abs;
	}

	public String getId() {
		return id;
	}

	public String getAtt() {
		return att;
	}

	public String getLate() {
		return late;
	}

	public String getLeave() {
		return leave;
	}

	public String getOut() {
		return out;
	}

	public String getAbs() {
		return abs;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAtt(String att) {
		this.att = att;
	}

	public void setLate(String late) {
		this.late = late;
	}

	public void setLeave(String leave) {
		this.leave = leave;
	}

	public void setOut(String out) {
		this.out = out;
	}

	public void setAbs(String abs) {
		this.abs = abs;
	}

}
