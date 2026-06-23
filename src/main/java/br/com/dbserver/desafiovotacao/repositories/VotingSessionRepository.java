package br.com.dbserver.desafiovotacao.repositories;

import br.com.dbserver.desafiovotacao.models.VotingSession;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingSessionRepository extends JpaRepository<VotingSession, UUID> {}
