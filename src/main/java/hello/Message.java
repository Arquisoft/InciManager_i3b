package hello;

/**
 * Created by herminio on 27/2/17.
 */
public class Message {

    public String message;
    public String title;
    public String location;
    public int state;
    public String[] tags;
    public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAditionalInfo() {
		return aditionalInfo;
	}

	public void setAditionalInfo(String aditionalInfo) {
		this.aditionalInfo = aditionalInfo;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String aditionalInfo;

    public String getTitle() {
		return title;
	}

	public String getLocation() {
		return location;
	}

	public Message() {}
	

    public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
