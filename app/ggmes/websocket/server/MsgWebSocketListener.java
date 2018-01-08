package ggmes.websocket.server;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketListener;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;

public class MsgWebSocketListener implements WebSocketListener{

	@Override
	public InetSocketAddress getLocalSocketAddress(WebSocket arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InetSocketAddress getRemoteSocketAddress(WebSocket arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onWebsocketClose(WebSocket arg0, int arg1, String arg2, boolean arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebsocketCloseInitiated(WebSocket arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebsocketClosing(WebSocket arg0, int arg1, String arg2, boolean arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebsocketError(WebSocket arg0, Exception arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebsocketHandshakeReceivedAsClient(WebSocket arg0, ClientHandshake arg1, ServerHandshake arg2)
			throws InvalidDataException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket arg0, Draft arg1, ClientHandshake arg2)
			throws InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onWebsocketHandshakeSentAsClient(WebSocket arg0, ClientHandshake arg1) throws InvalidDataException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebsocketMessage(WebSocket arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebsocketMessage(WebSocket arg0, ByteBuffer arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebsocketMessageFragment(WebSocket arg0, Framedata arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebsocketOpen(WebSocket arg0, Handshakedata arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebsocketPing(WebSocket arg0, Framedata arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebsocketPong(WebSocket arg0, Framedata arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWriteDemand(WebSocket arg0) {
		// TODO Auto-generated method stub
		
	}

}
