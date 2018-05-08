package uniovi.es.utils;

public class AgentLogin {

	private String login, password, kindCode;
	
	public AgentLogin() {}
	
	public AgentLogin(String login, String password, String kind) {
		this.login = login;
		this.password = password;
		this.kindCode = kind;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKindCode() {
		return kindCode;
	}

	public void setKind(String kind) {
		this.kindCode = kind;
	}

}
