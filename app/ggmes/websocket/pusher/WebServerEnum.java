package ggmes.websocket.pusher;

import ggmes.websocket.server.MessageSocketServer;

public enum WebServerEnum {

	server;
	
	private static MessageSocketServer pusher = null;
	
	public static void init(MessageSocketServer socketServer) {
		pusher = socketServer;
		if(pusher != null) {
			pusher.start();
		}
	}
}
