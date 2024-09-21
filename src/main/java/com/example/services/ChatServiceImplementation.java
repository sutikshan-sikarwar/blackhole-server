package com.example.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Chat;
import com.example.models.User;
import com.example.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService {

	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public Chat createChat(User reqUser, User user2) {
		Chat isExists = chatRepository.findChatByUsersId(user2, reqUser);
		if(isExists!=null) {
			return isExists;
		}
		Chat chat = new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(reqUser);
		chat.setTimestamp(LocalDateTime.now());
		return chatRepository.save(chat);
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {
	Optional<Chat> opt = chatRepository.findById(chatId);
	
	if(opt.isEmpty()) {
		throw new Exception("Chat not found");
	}
	
		return opt.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) {
		
		return chatRepository.findByUsersId(userId);
	}

}
