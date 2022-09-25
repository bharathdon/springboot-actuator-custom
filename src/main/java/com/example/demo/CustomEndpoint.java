package com.example.demo;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;
@Component
@Endpoint(id = "release-notes")
public class CustomEndpoint {

	Map<String, List<String>> releaseMapNotes = new LinkedHashMap<String, List<String>>();
	
	@PostConstruct
	public void init() {
		releaseMapNotes.put("v1", Arrays.asList("a","b"));
		releaseMapNotes.put("v2", Arrays.asList("c","d"));
	}
	
	@ReadOperation
	public Map<String, List<String>> getReleaseNotes(){
		return releaseMapNotes;
	}
	
	@ReadOperation
	public List<String> getNotesByVersion(@Selector String version){
		return releaseMapNotes.get(version);
	}
	
	@WriteOperation
	public void addNotes(@Selector String version,String releaseNotes) {
		
		releaseMapNotes.put(version, Arrays.stream(releaseNotes.split(",")).collect(Collectors.toList()));
	}
	
	@DeleteOperation
	public void deleteNotes(String version) {
		releaseMapNotes.remove(version);
	}
	
}
