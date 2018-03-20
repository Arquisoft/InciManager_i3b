package hello.entities;

import java.util.Map;

import org.springframework.data.annotation.Id;

/**
 * Created by herminio on 27/2/17.
 */
public class Message {
	
	@Id
	private String incidentID;
	private String message;
	private String title;
	private String location;
	private int state;
	private String[] tags;
	private String agentName;
	private String aditionalInfo;
	private int kind;
	private Map<String, String> customFields;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getName() {
		return agentName;
	}

	public void setName(String name) {
		this.agentName = name;
	}

	public String getAditionalInfo() {
		return aditionalInfo;
	}

	public void setAditionalInfo(String aditionalInfo) {
		this.aditionalInfo = aditionalInfo;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public Map<String, String> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(Map<String, String> customFields) {
		this.customFields = customFields;
	}

}
