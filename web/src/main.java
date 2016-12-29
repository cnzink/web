
public class main {

	public static void main(String arg[]) {
		// open
		mysocket sk = new mysocket();
		try {
			sk.connect();
			// System.out.println("connect success");

		} catch (Exception e) {
			System.out.println(e);
		}

		// send
		sk.setlist();

		GwIncomingDataProcessor gwDataProcessor = new GwIncomingDataProcessor();
		HostTcpReceivePort hostReceiver = new HostTcpReceivePort(gwDataProcessor);
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

		// hostReceiver.stop();
		// gwDataProcessor.stop();
	}
}