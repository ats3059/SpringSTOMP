var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket'); // -> 우리가 정해놓은 엔드포인트로 연결을 요청
    stompClient = Stomp.over(socket); // STOMP는 SockJS 위에서 동작한다.
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        // /topic/greetings를 구독하겠다 -> 	@SendTo("/topic/greetings") 요기에 해당하는 메서드들에게 메시지를 받는 것
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    // /app/hello send하면 -> Controller에서 @MessageMapping("/hello") 가 붙은 메서드 실행
    // stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));

    const test = JSON.stringify({'name': $("#name").val() ,'chat' : $("#message").val()});
    stompClient.send("/app/hello", {}, test);
}

function showGreeting(messages) {
    $("#greetings").append("<tr><td>" + `${messages.name} : ${messages.chat}` + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

