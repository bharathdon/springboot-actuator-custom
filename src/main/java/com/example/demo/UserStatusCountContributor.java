package com.example.demo;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
@Component
public class UserStatusCountContributor implements InfoContributor {

	@Autowired
	private UserDatabase database;
	
	@Override
	public void contribute(Builder builder) {

		Map<String, Long> user = new HashMap<String, Long>();
		user.put("acive", database.getStatusCount("active"));
		user.put("inactive", database.getStatusCount("inactive"));
		
		builder.withDetail("userStatus", user);
	}
}
