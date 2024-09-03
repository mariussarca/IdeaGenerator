package com.nexusll.ideasgenerator.repository;

import com.nexusll.ideasgenerator.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

}
