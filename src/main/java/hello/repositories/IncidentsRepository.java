package hello.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import hello.entities.Message;

public interface IncidentsRepository extends MongoRepository<Message, String> {
	public List<Message> getIncidentsByAgentName(String name);
}
