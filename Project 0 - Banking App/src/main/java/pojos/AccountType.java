package pojos;

public class AccountType {

	private int id;
	private String type ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "AccountInfo [id=" + id + ", type=" + type + "]";
	}	
	
}
