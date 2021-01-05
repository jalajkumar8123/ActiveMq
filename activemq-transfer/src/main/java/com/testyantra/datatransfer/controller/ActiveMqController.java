package com.testyantra.datatransfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.testyantra.datatransfer.dto.ResponceDto;
import com.testyantra.datatransfer.service.ActiveMqService;

@RestController
public class ActiveMqController {

	@Autowired
	private ActiveMqService activeMqService;

	@PostMapping("/post-activemq")
	public ResponceDto postActivemq(@RequestBody String activeMqBody) {
		
		activeMqService.postActiveMq(activeMqBody);

		return null;
	}

}
