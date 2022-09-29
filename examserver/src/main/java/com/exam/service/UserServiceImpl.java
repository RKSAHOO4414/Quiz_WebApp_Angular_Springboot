package com.exam.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User local = this.userRepository.findByusername(user.getUsername());
		if(local != null) {
			System.out.println("User is already there !!");
			throw new Exception("User already present !!");
		}else {
			//user create 
			for(UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
			
		}
		
		return local;
	}

	//getting user by username
	@Override
	public User getUser(String username) {
		
		return this.userRepository.findByusername(username);
	}

	@Override
	public void deleteUser(Long userId) {
		
		this.userRepository.deleteById(userId);
		
	}

	@Override
	public User updateUser(User user) {
		return this.userRepository.save(user);
	}

}
