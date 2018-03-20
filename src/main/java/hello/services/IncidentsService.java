package hello.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import hello.entities.Message;
import hello.producers.KafkaProducer;
import hello.repositories.IncidentsRepository;

@Service
public class IncidentsService {
	
	@Autowired
	private IncidentsRepository incidentsRepository;
	@Autowired
	private KafkaProducer kafkaProducer;
	
	public void addIncident(String topic, Message incident) {
		//incidentsRepository.save(incident);
		Gson gson = new Gson();
		String kafkaMessage = gson.toJson(incident);
		//kafkaProducer.send(topic, kafkaMessage);
	}
	
	public List<Message> getAgentIncidents(String agentName) {
		//return incidentsRepository.getIncidentsByAgentName(agentName);
		return null;
	}

}
