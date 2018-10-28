package saas.bean;

public class Account {


	private String id;
	private String type;
	private String name;
	private double amount;
	private int seq;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", type=" + type + ", name=" + name + ", amount=" + amount + "]";
	}


	public String tourlString(){

		String urlparam = "&accounts["+seq+"].amount="+amount+"&accounts["+seq+"].id="+id+"&accounts["+seq+"].type="+type;
		return urlparam;

	}


}
