package ggmes.websocket.pusher;

import ggmes.websocket.server.MsgWebSocketServer;

public enum WebServerEnum {

	server;
	
	private static MsgWebSocketServer socketServer = null;
	
	public static void init(MsgWebSocketServer server) {
		socketServer = server;
		if(socketServer != null) {
			socketServer.start();
		}
	}
}
