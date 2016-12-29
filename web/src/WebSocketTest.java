import java.io.IOException;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

//@ServerEndpoint("/websocket1")
public class WebSocketTest {

	GwIncomingDataProcessor gwDataProcessor = new GwIncomingDataProcessor();
	HostTcpReceivePort hostReceiver = new HostTcpReceivePort(gwDataProcessor);
	String num;

	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {
		// receive
		System.out.println("receive:" + message);
		num = gwDataProcessor.num();

		// send
		if (num != null) {
			session.getBasicRemote().sendText(num);
		} else {
			session.getBasicRemote().sendText("0");
		}
	}

	@OnOpen
	public void onOpen() {
		System.out.println("Client connect");
		// open
		mysocket sk = new mysocket();
		try {
			sk.connect();
			System.out.println("connect success");

		} catch (Exception e) {
			System.out.println(e);
		}

		// send
		sk.setlist();

		Thread receiverThread = new Thread(hostReceiver);
		receiverThread.start();

		Thread gwDataProcessorThread = new Thread(gwDataProcessor);
		gwDataProcessorThread.start();

		// close
		try {
			sk.close();
			System.out.println("close success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose() {
		System.out.println("Client disconnect");
		hostReceiver.stop();
		gwDataProcessor.stop();
	}
}