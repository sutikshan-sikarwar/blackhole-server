package com.example.services;

import java.util.List;

import com.example.models.Chat;
import com.example.models.Message;
import com.example.models.User;

public interface MessageService {
	
	public Message createMessage(User user, Integer chatId, Message req) throws Exception;
	
	public List<Message> findChatMessages(Integer chatId) throws Exception;

}
