package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.config.JwtProvider;
import com.example.models.User;
import com.example.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user) {
		
		User newUser = new User();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());
		newUser.setProfilePic(user.getProfilePic());
		
		User savedUser = userRepository.save(newUser);
		
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws Exception{
		Optional<User> user = userRepository.findById(userId);
		
		if (user.isPresent()) {
			return user.get();
		}
		throw new Exception("User not found with id "+userId);

	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer reqUserId1, Integer userId2) throws Exception {
	    
	    User reqUser = findUserById(reqUserId1);
	    User user2 = findUserById(userId2);
	    
	    
	    if (reqUser.getFollowing().contains(user2.getId())) {
	        
	        reqUser.getFollowing().remove(user2.getId());
	        user2.getFollowers().remove(reqUser.getId());
	    } else {
	        
	        reqUser.getFollowing().add(user2.getId());
	        user2.getFollowers().add(reqUser.getId());
	    }
	    
	    userRepository.save(reqUser);
	    userRepository.save(user2);
	    
	    return reqUser;
	}


	@Override
	public User updateUser(User user, Integer userId) throws Exception {
		Optional<User> user1 = userRepository.findById(userId);
		
		if(user1.isEmpty()) {
			throw new Exception("user not present with id " + userId);
		}
		
		User oldUser = user1.get();
		
		
		if(user.getFirstName()!=null) {
			oldUser.setFirstName(user.getFirstName());
		}
		
		if(user.getLastName()!=null) {
			oldUser.setLastName(user.getLastName());
		}
		
		if(user.getEmail()!=null) {
			oldUser.setEmail(user.getEmail());
		}
		
		if(user.getPassword()!=null) {
			oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		
		if(user.getProfilePic()!=null) {
			oldUser.setProfilePic(user.getProfilePic());
		}
		
		if(user.getGender()!=null) {
			oldUser.setGender(user.getGender());
		}
		
		User updatedUser = userRepository.save(oldUser);
		
		return updatedUser;
	}

	@Override
	public List<User> searchUser(String Query) {
		
		return userRepository.searchUser(Query);
	}

	@Override
	public User findUserByJwt(String jwt) {
		
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		
		User user = userRepository.findByEmail(email);
		
		return user;
	}

	@Override
	public String deleteUser(Integer userId) throws Exception {
		User reqUser = findUserById(userId);
		userRepository.delete(reqUser);
		return "deleted successfully";
	}
}
