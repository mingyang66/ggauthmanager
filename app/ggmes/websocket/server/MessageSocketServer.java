package ggmes.websocket.server;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import ggframework.bottom.log.GGLogger;

public class MessageSocketServer extends WebSocketServer{

	public MessageSocketServer(int port) {
		// TODO Auto-generated constructor stub
		super(new InetSocketAddress(port));
	}
	/**
	 * 在WebSocket连接已关闭时调用。
	 */
	@Override
	public void onClose(WebSocket socket, int arg1, String arg2, boolean arg3) {
		// TODO Auto-generated method stub
		System.out.println("------------------onClose-------------------"+socket.isClosed());
	}

	/**
	 * 错误发生时调用。
	 */
	@Override
	public void onError(WebSocket socket, Exception e) {
		// TODO Auto-generated method stub
		System.out.println("------------------onError-------------------");
		
	}

	/**
	 * 对从远程主机接收的字符串消息进行回调
	 */
	@Override
	public void onMessage(WebSocket socket, String msg) {
		// TODO Auto-generated method stub
		System.out.println("------------------onMessage-------------------连接状态："+socket.getReadyState()+
				"---本地SOCKET地址："+socket.getLocalSocketAddress()+"--客户端SOCKET地址："+socket.getRemoteSocketAddress()+
				"--"+socket.getAttachment());
		if(socket.isClosed()) {
			GGLogger.info("socket连接已关闭...");
		} else if (socket.isClosing()) {
			GGLogger.info("socket连接正在关闭...");
		} else if (socket.isConnecting()) {
			GGLogger.info("socket正在连接中...");
		} else if(socket.isOpen()) {
			GGLogger.info("socket连接已打开...");
			System.out.println(msg);
		}
		
	}

	/**
	 * 在websocket进行握手之后调用，并且给WebSocket写做准备
	 */
	@Override
	public void onOpen(WebSocket socket, ClientHandshake shake) {
		// TODO Auto-generated method stub
		System.out.println("-----------------onOpen--------------------"+socket.isOpen()+"--"+socket.getReadyState()+"--"+socket.getAttachment());
		
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
