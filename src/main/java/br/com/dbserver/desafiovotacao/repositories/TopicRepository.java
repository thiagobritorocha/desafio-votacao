package br.com.dbserver.desafiovotacao.repositories;

import br.com.dbserver.desafiovotacao.models.Topic;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, UUID> {}
