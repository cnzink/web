
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class mysocket {
	private String addr = "192.168.1.100";
	private int port = 8890;
	private static Socket sk;
	private byte[] byt;

	public void connect() throws IOException {
		sk = new Socket(addr, port);
	}

	public void close() throws Exception {
		sk.close();
	}

	public void setlist() {
		byte[] pkt = new byte[2];
		pkt[0] = 10;
		pkt[1] = (byte) 1;
		byt = pkt;
		PrintStream out;
		try {
			out = new PrintStream(sk.getOutputStream(), true);
			out.write(byt);
			out.flush();
			out.close();
			// System.out.println("setlist success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public static void main(String args[]){
	// mysocket skt=new mysocket();
	// try {
	// skt.connect();
	// System.out.println("success");
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
}
