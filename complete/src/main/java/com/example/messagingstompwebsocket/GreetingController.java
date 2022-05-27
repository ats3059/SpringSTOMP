package com.example.messagingstompwebsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

	// socket의 요청(STOMP)이 /hello로 들어오면 해당 메서드가 처리함
	@MessageMapping("/hello")
	//아래의 /topic/greetings를 구독한 모든 소켓에게 메시지를 전달하겠다.
	@SendTo("/topic/greetings")
	public Greeting greeting(@RequestBody HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}

}
