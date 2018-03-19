package hello;

/**
 * Created by herminio on 27/2/17.
 */
public class Message {

    private String message;
    private String title;
    private String location;

    public String getTitle() {
		return title;
	}

	public String getLocation() {
		return location;
	}

	public Message() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
