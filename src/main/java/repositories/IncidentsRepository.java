package repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import entities.Message;

public interface IncidentsRepository extends MongoRepository<Message, String> {
	public List<Message> getIncidentsByAgentName(String name);
}
