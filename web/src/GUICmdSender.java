/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
import java.util.Vector;
import java.util.logging.Level;

/**
 * This class defines command from GUI to network manager
 * 
 * @author Hang Yu
 */
public class GUICmdSender implements Runnable {

	private final static Logger LOGGER = Logger.getLogger(GUICmdSender.class.getName());

	// private MeshSimulator localMS; //reference to MS, so we can call
	// getTcpManager()

	// the outgoing message queue to the Gateway. The message is in the byte
	// array format
	final private ConcurrentLinkedQueue<byte[]> outgoingBytes = new ConcurrentLinkedQueue<byte[]>();

	boolean isActive = true;

	// data structure for holding the sequence number and received and sent
	// messages
	public char sequenceNumber = 0x0000;

	Vector<byte[]> sentMsgVector;
	Vector<byte[]> recvMsgVector;
	Vector<byte[]> allMsgVector;

	// GUITopology topology;

	public void run() {
		while (isActive) {
			System.out.println("run sender");
			try {
				Thread.yield();
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			while (outgoingBytes.size() > 0) {
				byte[] nextOutgoingMessage = outgoingBytes.poll();
				if (nextOutgoingMessage == null) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException ex) {
						Logger.getLogger(GUICmdSender.class.getName()).log(Level.SEVERE, null, ex);
					}
					continue;
				} else {
					// localMS.getTcpManager().sendToManagerCore(nextOutgoingMessage);
				}
			}

		}
	}

	// public GUICmdSender(MeshSimulator ms, GUITopology topo)
	// {
	// this.localMS = ms;
	// isActive = true;
	// topology = topo;
	// }

	public void stop() {
		this.isActive = false;
		System.out.println("sender stop");
	}

	public void addMsg(byte[] msg) {
		outgoingBytes.add(msg);
	}

}
