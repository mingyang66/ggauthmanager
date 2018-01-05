package ggmes.websocket.pusher;

import ggmes.websocket.server.MessageSocketServer;

public enum WebServerEnum {

	server;
	
	private static MessageSocketServer socketServer = null;
	
	public static void init(MessageSocketServer server) {
		socketServer = server;
		if(socketServer != null) {
			socketServer.start();
		}
	}
}
