package hello.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.entities.Coordinates;
import hello.entities.Message;
import hello.entities.UserInfo;
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
	public String index(HttpSession session, Model model) {
		model.addAttribute("message", new Message());
		return "index";
	}

	@RequestMapping("/logIn")
	public String log(Model model) {
		return "logIn";
	}

	@RequestMapping(value = "/logIn", method = RequestMethod.POST)
	public String log(HttpSession session, Model model, @ModelAttribute UserInfo u, RedirectAttributes redirect) {
		if (u.getKind() < 0 || u.getKind() > 3) {
			return "logIn";
		} else {
			session.setAttribute("user", u.getName());
			session.setAttribute("kind", u.getKind());
			session.setAttribute("map", new HashMap<String, String>());
			redirect.addFlashAttribute("user", u);
			redirect.addFlashAttribute("name", u.getName());
			redirect.addFlashAttribute("kind", u.getKind());
			return "redirect:index";
		}
	}

	@PostMapping("/send")
	public String send(HttpSession session, Model model, @Validated Message message) {
		// set user
		message.setName((String) session.getAttribute("user"));
		message.setKind((int) session.getAttribute("kind"));
		message.setCustomFields((Map<String, String>) session.getAttribute("map"));
		// set Coords
		Coordinates coor = new Coordinates();
		message.setLocation("" + coor.getCoordinates());

		incidentsService.addIncident("exampleTopic", message);
		session.setAttribute("map", new HashMap<String, String>());
		return "redirect:index";
	}

	@RequestMapping("/list")
	public String queryInfo(Model model, HttpSession s) {
		List<Message> l = incidentsService.getAgentIncidents((String) s.getAttribute("user"));
		/*
		 * Message m = new Message(); m.setName("esteban"); m.setKind(2);
		 * m.setAditionalInfo("aditional info"); m.setLocation("canada");
		 * m.setMessage("mesage"); m.setTitle("tutulo"); m.setState(1); String [] s1 =
		 * {"tag1", "tag2", "tag3", "tag4"}; m.setTags(s1); l.add(m);
		 */
		model.addAttribute("incidentList", l);
		return "list";
	}

	@RequestMapping(value = "/add-custom-field/{key}/{value}")
	public String addCustomField(HttpSession session, @PathVariable("key") String key,
			@PathVariable("value") String value, RedirectAttributes redirect, Model model) {
		Map<String, String> map = (Map<String, String>) session.getAttribute("map");
		map.put(key, value);
//		redirect.addFlashAttribute("name", session.getAttribute("user"));
//		redirect.addFlashAttribute("kind", session.getAttribute("kind"));
		model.addAttribute("fieldsMap", map);
		return "index :: tableFields";
	}

}