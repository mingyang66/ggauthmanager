package ggmes.websocket.bootstrap;


import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import ggmes.websocket.client.MsgWebSocketClient;
import ggmes.websocket.pusher.WebClientEnum;
import ggmes.websocket.pusher.WebServerEnum;
import ggmes.websocket.server.MsgWebSocketServer;
import play.mvc.Controller;

public class SocketClientEngine{


	public static void main(String[] args) {
		try {
			WebClientEnum.CLIENT.initClient(new MsgWebSocketClient("ws://192.168.152.73:8099"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
