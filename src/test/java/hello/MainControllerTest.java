package hello;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class MainControllerTest {
	
	private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
	
	@Test
    public void testGetLoginPage() throws Exception {
		mockMvc.perform(get("/logIn"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Username:")))
        .andExpect(content().string(containsString("Password:")))
        .andExpect(content().string(containsString("Kind:")));		
	}
	
	@Test
    public void testPostLoginPageCorrectKind() throws Exception {
		mockMvc.perform(post("/logIn")
	              .param("loging", "Pepe")
	              .param("password", "123456")
	    		  .param("kind","1"))
	              .andExpect(status().is3xxRedirection())
	              .andExpect(redirectedUrl("index"));	
	}
	
	@Test
    public void testPostLoginPageWrongKind() throws Exception {
		mockMvc.perform(post("/logIn")
	              .param("loging", "Pepe")
	              .param("password", "123456")
	    		  .param("kind","54654"))
	              .andExpect(status().isOk())
	              .andExpect(content().string(containsString("LOG IN")));	
	}
}