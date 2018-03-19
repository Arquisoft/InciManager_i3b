package hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.producers.KafkaProducer;

@Controller
public class MainController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping("/")
    public String landing(Model model) {
        return "redirect:/logIn";
    }
    
    /*@RequestMapping("/")
    public String landing(Model model) {
        model.addAttribute("message", new Message());
        return "index";
    }*/
    
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
    public String log(Model model, @ModelAttribute UserInfo u) 
    {
    	if(u.getKind()<0 || u.getKind()>3)
    	{
    		return "logIn";	
    	}else {
    	model.addAttribute("info", u);
        return "redirect:/index";}
    }
    
    @RequestMapping("/send")
    public String send(Model model, @ModelAttribute Message message) {
        kafkaProducer.send("exampleTopic", message.getMessage());
        return "redirect:/";
    }

}