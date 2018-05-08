package uniovi.es.utils;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;

public class LogInMock {
	private static AgentLogin[] validUsers = {new AgentLogin("11111111A", "123456", "1"), new AgentLogin("test2", "passwd2", "1"), new AgentLogin("test3", "passwd3", "2")};
	
	public static HttpResponse logIn(String data) {
		Gson gson = new Gson();
		
		AgentLogin agentLogin = gson.fromJson(data, AgentLogin.class);
		
		
		
		for (AgentLogin login : validUsers)
			if(login.getLogin().equals(agentLogin.getLogin()) && login.getKindCode().equals(agentLogin.getKindCode()) && login.getPassword().equals(agentLogin.getPassword()))
				return new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 0), HttpStatus.OK.value(), ""));
		
		return new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 0), HttpStatus.NOT_FOUND.value(), ""));
	}

}
