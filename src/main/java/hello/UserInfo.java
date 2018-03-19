package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserInfo {
	
	// Log
	private static final Logger LOG = LoggerFactory.getLogger(UserInfo.class);

    private final String name;
    private final int kind;

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