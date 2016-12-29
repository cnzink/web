
import java.io.IOException;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class web {
	private static Session s;
	Test a = new Test();

	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {
	}

	@OnOpen
	public void onOpen(Session s) throws Exception {
		this.s = s;
		this.send("hello");
		a.run();
	}

	public static void send(String mes) throws Exception {
		s.getBasicRemote().sendText(mes);
	}

	@OnClose
	public void onClose() {
		a.disconnect();
	}
}