package vo;

public class MemberKEY_NHIS {
	
	private String person_id;
	private String nhis_id;
	
	public MemberKEY_NHIS(String person_id, String nhis_id){
		this.person_id = person_id;
		this.nhis_id = nhis_id;
	}
	
	
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	public String getNhis_id() {
		return nhis_id;
	}
	public void setNhis_id(String nhis_id) {
		this.nhis_id = nhis_id;
	}

	
	
}
