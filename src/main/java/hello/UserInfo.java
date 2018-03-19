package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserInfo {
	
	// Log
	private static final Logger LOG = LoggerFactory.getLogger(UserInfo.class);

    public String name;
    private int kind;

    
    
    public void setName(String name) {
		this.name = name;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public UserInfo() {}
    
    public UserInfo(String name, Integer kind) {
    	LOG.info("Creating user " + name + ". kind: " + kind);
        this.name = name;
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public Integer getKind() {
        return kind;
    }
}