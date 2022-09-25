package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

@Repository
public class UserDatabase {

	public List<User> getAllUser() {
		return Stream.of(new User(1, "abc","active"), new User(2, "Def","inactive")

		).collect(Collectors.toList());
	}
	
	public Long getStatusCount(String status) {
		return getAllUser().stream().filter(a->a.getStatus().equalsIgnoreCase(status)).count();
	}
}
