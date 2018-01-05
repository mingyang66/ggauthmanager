package ggmes.websocket.server;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class MessageSocketServer extends WebSocketServer{

	public MessageSocketServer(int port) {
		// TODO Auto-generated constructor stub
		super(new InetSocketAddress(port));
	}
	/**
	 * 在WebSocket连接已关闭称为。
	 */
	@Override
	public void onClose(WebSocket socket, int arg1, String arg2, boolean arg3) {
		// TODO Auto-generated method stub
		System.out.println("------------------onClose-------------------");
	}

	/**
	 * 错误发生时调用。
	 */
	@Override
	public void onError(WebSocket socket, Exception arg1) {
		// TODO Auto-generated method stub
		System.out.println("------------------onError-------------------");
		
	}

	/**
	 * 对从远程主机接收的字符串消息进行回调
	 */
	@Override
	public void onMessage(WebSocket socket, String arg1) {
		// TODO Auto-generated method stub
		System.out.println("------------------onMessage-------------------");
		System.out.println(new String(socket.getResourceDescriptor().getBytes()));
		
	}

	/**
	 * 在websocket进行握手之后调用，并且给WebSocket写做准备
	 */
	@Override
	public void onOpen(WebSocket socket, ClientHandshake shake) {
		// TODO Auto-generated method stub
		System.out.println("-----------------onOpen--------------------");
		
	}
	/**
	 * 当服务器成功启动时调用。
	 */
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		System.out.println("------------------onStart-------------------");
	}

}
