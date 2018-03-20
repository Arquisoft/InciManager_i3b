package hello;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.producers.KafkaProducer;

@Controller
public class MainController {

    @Autowired
    private KafkaProducer kafkaProducer;

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
    public String log(Model model, @ModelAttribute UserInfo u, RedirectAttributes redirect) 
    {
    	if(u.getKind()<0 || u.getKind()>3)
    	{
    		return "logIn";	
    	}else {
    		redirect.addFlashAttribute("user",u);
    		redirect.addFlashAttribute("name", u.getName());
    		redirect.addFlashAttribute("kind", u.getKind());
        return "redirect:index";}
    }
    
    @RequestMapping("/send")
    public String send(Model model, @ModelAttribute Message message) {
        //incidentService.add("exampleTopic", message);
        return "redirect:/";
    }
    
    @RequestMapping("/list")
    public String queryInfo(Model model, @ModelAttribute UserInfo u) {
        //list = lista(u.getName(), u.kind());
    	//model.addAttribute("incidentsList", list);
    	List<Message> l = new ArrayList<Message>();
    	Message m = new Message();
    	m.setAditionalInfo("aditional info");
    	m.setLocation("canada");
    	m.setMessage("mesage");
    	m.setTitle("tutulo");
    	m.setState(1);
    	String [] s = {"tag1", "tag2", "tag3", "tag4"};
     	m.setTags(s);
    	l.add(m);
    	model.addAttribute("incidentList", l);
        return "list";
    }

}