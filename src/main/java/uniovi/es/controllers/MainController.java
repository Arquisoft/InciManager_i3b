package uniovi.es.controllers;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uniovi.es.entities.Coordinates;
import uniovi.es.entities.Message;
import uniovi.es.entities.UserInfo;
import uniovi.es.services.IncidentsService;



@Controller
public class MainController {

	@Autowired
	private IncidentsService incidentsService;

	@RequestMapping("/index")
	public String index(HttpSession session, Model model) {
		if (session.getAttribute("user").equals("")) {
			return "redirect:logIn";
		}
		model.addAttribute("message", new Message());
		session.setAttribute("map", new HashMap<String, String>());
		return "index";
	}

	@RequestMapping("/logIn")
	public String log(HttpSession session) {
		session.setAttribute("user", "");
		session.setAttribute("kind", "");
		session.setAttribute("map", "");
		return "logIn";
	}

	@RequestMapping(value = "/logIn", method = RequestMethod.POST)
	public String log(HttpSession session, @ModelAttribute UserInfo u, RedirectAttributes redirect) {
		if (u.getKind() < 0 || u.getKind() > 3) {
			return "redirect:logIn";
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
	public String send(HttpSession session, @Validated Message message) {
		if (session.getAttribute("user").equals("")) {
			return "redirect:logIn";
		}
		// set user
		message.setName((String) session.getAttribute("user"));
		message.setKind((int) session.getAttribute("kind"));
		message.setCustomFields((Map<String, String>) session.getAttribute("map"));
		// set Coords
		Coordinates coor = new Coordinates();
		message.setLocation("" + coor.getCoordinates());

		incidentsService.addIncident("exampleTopic", message);
		session.setAttribute("map", new HashMap<String, String>());
		return "redirect:list";
	}

	@RequestMapping("/list")
	public String queryInfo(Model model, HttpSession session) {
		if (session.getAttribute("user").equals("")) {
			return "redirect:logIn";
		}
		List<Message> l = incidentsService.getAgentIncidents((String) session.getAttribute("user"));
		model.addAttribute("incidentList", l);
		return "list";
	}

	@RequestMapping(value = "/add-custom-field/{key}/{value}")
	public String addCustomField(HttpSession session, @PathVariable("key") String key,
			@PathVariable("value") String value, Model model) {
		if (session.getAttribute("user").equals("")) {
			return "redirect:logIn";
		}
		Map<String, String> map = (Map<String, String>) session.getAttribute("map");
		map.put(key, value);
		model.addAttribute("fieldsMap", map);
		return "index :: tableFields";
	}

}