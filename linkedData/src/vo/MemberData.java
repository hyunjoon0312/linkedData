package vo;

public class MemberData {

	private int linkID;
	private String personID;
	
	public MemberData(int linkID, String personID){
		this.linkID = linkID;
		this.personID = personID;
	}

	public int getLinkID() {
		return linkID;
	}

	public void setLinkID(int linkID) {
		this.linkID = linkID;
	}

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}
	
	
	
	
}


