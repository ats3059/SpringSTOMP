package com.example.messagingstompwebsocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
//> **WebSocket이란?**
//>
//
//웹소켓이란 두 프로그램 간의 메시지를 교환하기 위한 통신 방법 중 하나이다.
//
//---
//
//> **웹소켓의 특징**
//>
//
//**양방향 통신**
//
//- 데이터 송수신을 동시에 처리할 수 있는 통신방법.
//- 클라이언트와 서버가 서로에게 원할 때 데이터를 주고 받을 수 있다.
//- 통상적인 Http 통신은 Client가 요청을 보내는 경우에만 Server가 응답하는 단방향 통신
//
//**실시간 네트워킹**
//
//- 웹 환경에서 연속된 데이터를 빠르게 노출
//- 여러 단말기에 빠르게 데이터를 교환해야

//> **비슷한 기술 ( 이전의 기술 )**
//>
//
//**Polling** -> 서버로 일정 주기 요청 송신하고 응답을 받는 것
//real-time 통신에서는 언제 통신이 발생할지 예측이 불가능 -> 불필요한 request와 connection을 생성 ( 리소스가 낭비된다 )
//Real-time 통신이라고 부르기 애매할정도의 통신
//
//**LongPolling** -> 서버에 요청 보내고 이벤트가 생겨 응답 받을 때 까지 연결 종료 X -> 응답 받으면 끊고 다시 재요청
//많은 양의 메세지가 쏟아질 경우 polling과 같다.
//
//**Streaming** -> 서버에 요청 보내고 끊기지 않은 연결상태에서 끊임없이 데이터 수신 클라이언트에서 서버로의 데이터 송신이 어렵다.
//
//결과적으로 이 모든 방법이 HTTP를 통해 통신하기 때문에 Request, Response 둘다 Header가 불필요하게 큼.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		//토픽방식을 사용하겠다.
		config.enableSimpleBroker("/topic");
		// /app/~ 해당하는 path로 webSocket이 send 해야함 그러한 설정임 ㅇㅇ
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/gs-guide-websocket")
				.withSockJS();
	}

}
