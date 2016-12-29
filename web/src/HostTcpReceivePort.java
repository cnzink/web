
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class HostTcpReceivePort implements Runnable {

	private boolean stop = false;
	private boolean hasStopped = false;

	ServerSocket srvr;

	protected static final int READ_BYTE_LEN = 20;
	GwIncomingDataProcessor gwProcessor;

	public HostTcpReceivePort(GwIncomingDataProcessor gwProcessor) {

		try {
			srvr = new ServerSocket(8891);
			System.out.println("server open  success");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.gwProcessor = gwProcessor;
	}

	public void run() {
		try {
			System.out.println("rec start");
			while (!stop) {
				Thread.yield();
				Thread.sleep(5);
				try {
					srvr.setSoTimeout(100);
					Socket skt = srvr.accept();
					System.out.println("accept success");
					InputStream in = skt.getInputStream();

					byte[] npduBytes = new byte[0];
					byte[] incomingBytes = new byte[READ_BYTE_LEN];

					int inputSize = -1;
					while ((inputSize = in.read(incomingBytes)) > -1) {
						byte[] newNpduBytes = new byte[npduBytes.length + inputSize];
						System.arraycopy(npduBytes, 0, newNpduBytes, 0, npduBytes.length);
						System.arraycopy(incomingBytes, 0, newNpduBytes, npduBytes.length, inputSize);
						npduBytes = newNpduBytes;
					}
					gwProcessor.addNpduBytes(npduBytes);
					skt.close();
				} catch (SocketTimeoutException ste) {
					// do nothing
					// want to check stop condition
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				srvr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			hasStopped = true;
		}
	}

	/**
	     *
	     */
	public void stop() {
		this.stop = true;
		System.out.println("rec stop");
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasStopped() {
		return hasStopped;
	}
}
