package hello;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.entities.Message;
import hello.services.IncidentsService;

@Controller
public class MainController {

    @Autowired
    private IncidentsService incidentsService;

    @RequestMapping("/")
    public String landing(Model model) {
        return "redirect:/logIn";
    }
    
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("message", new Message());
        return "index";
    }
    
    @RequestMapping("/logIn")
    public String log(Model model) 
    {
        return "logIn";
    }

    @RequestMapping(value = "/logIn", method = RequestMethod.POST)
    public String log(HttpSession session ,Model model, @ModelAttribute UserInfo u, RedirectAttributes redirect) 
    {
    	if(u.getKind()<0 || u.getKind()>3)
    	{
    		return "logIn";	
    	}else {
    		session.setAttribute("user", u.getName());
    		session.setAttribute("kind", u.getKind());
    		redirect.addFlashAttribute("user",u);
    		redirect.addFlashAttribute("name", u.getName());
    		redirect.addFlashAttribute("kind", u.getKind());
        return "redirect:index";}
    }
    
    @PostMapping("/send")
    public String send(HttpSession session, Model model, @Validated Message message) {
    	message.setName((String) session.getAttribute("user"));
        message.setKind((int) session.getAttribute("kind"));
        String[] t = message.getTagsString().split(" ");
        message.setTags(t);
        incidentsService.addIncident("exampleTopic", message);
        return "redirect:/";
    }
    
    @RequestMapping("/list")
    public String queryInfo(Model model, HttpSession s) {
    	List<Message> l = incidentsService.getAgentIncidents((String)s.getAttribute("user"));
    	/*Message m = new Message();
    	m.setName("esteban");
    	m.setKind(2);
    	m.setAditionalInfo("aditional info");
    	m.setLocation("canada");
    	m.setMessage("mesage");
    	m.setTitle("tutulo");
    	m.setState(1);
    	String [] s1 = {"tag1", "tag2", "tag3", "tag4"};
     	m.setTags(s1);
    	l.add(m);*/
    	model.addAttribute("incidentList", l);
        return "list";
    }

}