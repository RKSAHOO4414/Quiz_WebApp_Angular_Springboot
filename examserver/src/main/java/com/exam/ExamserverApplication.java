package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.service.UserService;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("starting code");
		
		User user =new User();
		/*
		user.setFirstName("Raja");
		user.setLastName("Kumar");
		user.setUsername("Raja123");
		user.setPassword(this.bCryptPasswordEncoder.encode("raja"));
		user.setEmail("raja@gmail.com");
		user.setProfile("default.png");
		
		Role role1 = new Role();
		
		role1.setRoleId(44L);
		role1.setRoleName("ADMIN");
		
		Set<UserRole> userRoleSet = new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setRole(role1);
		userRole.setUser(user);
		
		userRoleSet.add(userRole);
		
		User userData = this.userService.createUser(user, userRoleSet);
		System.out.println(userData.getUsername());	
*/		
	}

}
