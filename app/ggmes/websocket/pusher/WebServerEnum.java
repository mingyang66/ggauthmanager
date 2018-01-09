package ggmes.websocket.pusher;

import java.net.URI;
import java.net.URISyntaxException;

import ggmes.websocket.client.MsgWebSocketClient;
import ggmes.websocket.server.MsgWebSocketServer;
import utils.ObjectUtils;

public enum WebServerEnum {

	SERVER;
	
	private static MsgWebSocketServer socketServer = null;
	
	public static void initServer(MsgWebSocketServer server) {
		socketServer = server;
		if(ObjectUtils.isNotNull(socketServer)) {
			socketServer.start();
		}
		boolean flag = true;
		int i=100;
		while(flag) {
			System.out.println("server..."+(i--));
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i == 0) {
				flag = false;
			}
		}
	}
	
}
