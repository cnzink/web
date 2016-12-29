/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import mesh.cmd.Cmd64046;
import mesh.cmd.CmdManipulation;
import mesh.cmd.GuiCmdInfo;

/**
 *
 * @author zxk
 */
public class Test {
	private static String ip = "192.168.1.101";
	private static int port = 8900;
	private Socket sk;
	private PrintStream out;
	private GUITcpRecvPort guiListener;
	private GUICmdProcessor cmdProcessor;
	private boolean isConnected;
	private GUITcpRecvPort rec;
	private GUICmdProcessor pro;
	private GUICmdSender cmdSender;

	/**
	 * @param args
	 *            the command line arguments
	 */
	// public static void main(String[] args) {
	//
	// // TODO code application logic here
	// }

	public void connect(String IPAddress, int port) {
		System.out.println("try to connect");
		// Send a "connect" message to ManagerCore
		Cmd64046 cmd64046 = new Cmd64046(true); 
		cmd64046.setProgressFlag((byte) 1); 

		ArrayList<GuiCmdInfo> cmdInfo = new ArrayList<GuiCmdInfo>();
		cmdInfo.add(cmd64046);
		byte[] connectInfo = CmdManipulation.GuiCmdListToByteArray(cmdInfo);

		// Connect to NM
		try {
			sk = new Socket();
			sk.connect(new InetSocketAddress(IPAddress, port), 5000); // attempt
																		// to
																		// connect
																		// to
																		// NM,
																		// with
																		// a
																		// specific
																		// timeout
			System.out.println("connect");
			out = new PrintStream(sk.getOutputStream(), true);
			sendToManagerCore(connectInfo);
			this.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendToManagerCore(byte[] npduBytes) {
		try {
			if (npduBytes.length > 32767)
				throw new Exception("Trying to send " + npduBytes.length + ", cannot send more than 32,767");
			byte[] lengthBytes = new byte[2];
			lengthBytes[0] = (byte) npduBytes.length; // lower byte
			lengthBytes[1] = (byte) (npduBytes.length >> 8); // upper byte
			out.write(lengthBytes);
			out.write(npduBytes);
			out.flush();
			System.out.println("send cmd");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void start() {

		// Setup and start TCP recv port
		cmdProcessor = new GUICmdProcessor();
		try {
			this.guiListener = new GUITcpRecvPort(sk, this.cmdProcessor);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Thread guiListenerThread = new Thread(guiListener);
		guiListenerThread.start();

		Thread cmdProcessorThread = new Thread(cmdProcessor);
		cmdProcessorThread.start();

		// cmdSender = new GUICmdSender();
		// Thread cmdSenderThread = new Thread(cmdSender);
		// cmdSenderThread.start();
		// cmdProcessor.setCmdSender(cmdSender);
		System.out.println("listen");
		isConnected = true;
		// TODO Auto-generated method stub

	}

	public void disconnect() {
		if (isConnected == false) {
			return; // GUI is not connected, no need to disconnect
		}

		// Send a "disconnect" message to ManagerCore
		Cmd64046 cmd64046 = new Cmd64046(true);
		cmd64046.setProgressFlag((byte) 4); // Indicates GUI wants to disconnect
											// from ManagerCore
		ArrayList<GuiCmdInfo> cmdInfo = new ArrayList<GuiCmdInfo>();
		cmdInfo.add(cmd64046);
		byte[] disconnectMsg = CmdManipulation.GuiCmdListToByteArray(cmdInfo);
		sendToManagerCore(disconnectMsg);
		isConnected = false;

		// Close the TCP socket
		try {
			out.close();
			sk.close(); // close the socket
			System.out.println("disconnect");
			guiListener.stop(); // stop Tcp recv port thread
			cmdProcessor.stop();
			// cmdSender.stop();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void run() {
		this.connect(ip, port);
		// TODO Auto-generated method stub

	}
}
