package ggmes.websocket.server;

import java.net.InetSocketAddress;
import java.util.Iterator;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import ggframework.bottom.log.GGLogger;
import ggmes.websocket.builder.WebSocketBuilder;

/**
 * 
 * websocket服务器
 * 
 * @version 1.0
 * @since JDK1.7
 * @author yaomy
 * @date 2018年1月8日 下午4:17:18
 */
public class MsgWebSocketServer extends WebSocketServer{

	public MsgWebSocketServer(int port) {
		// TODO Auto-generated constructor stub
		super(new InetSocketAddress(port));
	}
	/**
	 * 在WebSocket连接已关闭时调用。
	 */
	@Override
	public void onClose(WebSocket ws, int arg1, String arg2, boolean arg3) {
		// TODO Auto-generated method stub
		System.out.println("------------------onClose-------------------"+ws.isClosed()+"---\n"+
				arg1+"---"+arg2+"---"+arg3);
		WebSocketBuilder.removeWs(ws);
	}

	/**
	 * 错误发生时调用。
	 */
	@Override
	public void onError(WebSocket ws, Exception e) {
		// TODO Auto-generated method stub
		System.out.println("------------------onError-------------------");
		if(ws != null) {
			WebSocketBuilder.removeWs(ws);
		}
		e.getStackTrace();
		
	}

	/**
	 * 对从远程主机接收的字符串消息进行回调
	 */
	@Override
	public void onMessage(WebSocket ws, String msg) {
		// TODO Auto-generated method stub
		System.out.println("------------------onMessage-------------------连接状态："+ws.getReadyState()+
				"---本地SOCKET地址："+ws.getLocalSocketAddress()+"--客户端SOCKET地址："+ws.getRemoteSocketAddress()+
				"---"+ws.getAttachment()+"\n--"+ws.getDraft().getRole()+"--"+ws.getDraft().INITIAL_FAMESIZE+"--"+ws.getDraft().MAX_FAME_SIZE+
				"---"+ws.getResourceDescriptor()+"---"+ws.DEFAULT_PORT+"---"+ws.DEFAULT_WSS_PORT+"---"+ws.getRemoteSocketAddress().getAddress().getCanonicalHostName()+
				"---"+ws.getRemoteSocketAddress().getPort()+"---"+ws.getRemoteSocketAddress().getHostName()+"---"+ws.getRemoteSocketAddress().getHostString()+"---"+"\n"+
				"---"+ws.getLocalSocketAddress().getPort()+"---"+ws.getLocalSocketAddress().getHostName()+"---"+ws.getLocalSocketAddress().getHostString()+"---"+ws.getLocalSocketAddress().getAddress()+"---"+
				"---"+ws.getLocalSocketAddress().getAddress());
		if(ws.isClosed()) {
			GGLogger.info("ws连接已关闭...");
		} else if (ws.isClosing()) {
			GGLogger.info("ws连接正在关闭...");
		} else if (ws.isConnecting()) {
			GGLogger.info("ws正在连接中...");
		} else if(ws.isOpen()) {
			GGLogger.info("ws连接已打开...");
			System.out.println(msg);
		}
	}

	/**
	 * 在websocket进行握手之后调用，并且给WebSocket写做准备
	 * 通过握手可以获取请求头信息
	 */
	@Override
	public void onOpen(WebSocket ws, ClientHandshake shake) {
		// TODO Auto-generated method stub
		System.out.println("-----------------onOpen--------------------"+ws.isOpen()+"--"+ws.getReadyState()+"--"+ws.getAttachment());
		for(Iterator<String> it=shake.iterateHttpFields();it.hasNext();) {
			String key = it.next();
			System.out.println(key+":"+shake.getFieldValue(key));
		}
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
