package service;

public class DeliveryVO {
	private String address;
	private String description;
	private String id;
	private String status;
	private String base64ImageStr;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBase64ImageStr() {
		return base64ImageStr;
	}
	public void setBase64ImageStr(String base64ImageStr) {
		this.base64ImageStr = base64ImageStr;
	}
}
