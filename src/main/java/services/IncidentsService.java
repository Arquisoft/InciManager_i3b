package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.Message;
import repositories.IncidentsRepository;

@Service
public class IncidentsService {
	
	@Autowired
	private IncidentsRepository incidentsRepository;
	
	public void addIncident(String topic, Message incident) {
		incidentsRepository.save(incident);
		//TODO: Send to kafka
	}
	
	public List<Message> getAgentIncidents(String agentName) {
		return incidentsRepository.getIncidentsByAgentName(agentName);
	}

}
