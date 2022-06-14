package com.example.messagingstompwebsocket;

public class HelloMessage {

    private String name;
    private String chat;

    public HelloMessage() {
    }

    public HelloMessage(String name, String chat) {
        this.name = name;
        this.chat = chat;
    }

    public String getName() {
        return name;
    }

    public String getChat() {
        return chat;
    }

}
