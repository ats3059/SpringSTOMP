package com.example.messagingstompwebsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {
	private final SimpMessagingTemplate template;

	public GreetingController(SimpMessagingTemplate template) {
		this.template = template;
	}

	// socket의 요청(STOMP)이 /hello로 들어오면 해당 메서드가 처리함
	@MessageMapping("/hello")
	public void greeting(@RequestBody HelloMessage message) throws Exception {
		template.convertAndSend("/topic/greetings" , message);
	}

}
