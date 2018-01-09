package ggmes.websocket.bootstrap;


import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import ggmes.websocket.client.MsgWebSocketClient;
import ggmes.websocket.pusher.WebServerEnum;
import ggmes.websocket.server.MsgWebSocketServer;
import play.mvc.Controller;

public class SocketServerEngine{


	public static void main(String[] args) {
		WebServerEnum.SERVER.initServer(new MsgWebSocketServer(8099));
	}
}
