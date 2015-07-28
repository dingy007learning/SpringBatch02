package main.java.com.SpringBatch02.trial01;

public class ParsedData {
	private String id;
	private String description;
	public ParsedData(String id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	public ParsedData() {
		super();
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ParsedData [id=");
		builder.append(id);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
