
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class dataprocess implements Runnable {
	private int counti = 0;
	private boolean stop = false;
	private final ConcurrentLinkedQueue<byte[]> incomingBytes = new ConcurrentLinkedQueue<>();

	public void addNpduBytes(byte[] data) {
		incomingBytes.add(data);
	}

	public void run() {
		while (!stop) {
			try {
				Thread.yield();
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			byte[] nextMessage = incomingBytes.poll();
			if (nextMessage == null) {
				continue;
			} else {
				try {
					Handler(nextMessage);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public void stop() {
		this.stop = true;
	}

	public void Handler(byte[] msg) throws IOException, Exception {
		System.out.println("receive:");
		for (int i = 0; i < msg.length; i++) {
			System.out.println(msg[i]);
		}
		// HostQueryEntry input = new HostQueryEntry(msg);
		// int APIindex = (int) input.getIndex();
		final byte[] tagList;
		byte[] deviceID;
		final byte[] dataValue;
		byte[] customCmdNum;
		final byte[] payload;
		byte[] timestampBytes;

		// switch (APIindex) {
		// case 5: // TagList(tag list)
		// tagList = input.getTagList();
		// SwingUtilities.invokeLater(new Runnable() {
		//
		// public void run() {
		// host.addDevicesFromTagList(tagList);
		// }
		// });
		// break;
		//
		// case 6: // Data(device tag, data value). For Read or Subscribe
		// deviceID = input.getDeviceId();
		// dataValue = input.getDataValue();
		// timestampBytes = input.getTimestamp();
		//
		// final String deviceIdAndTimestamp = getTimeFromBytes(timestampBytes)
		// + " (" + Converter.ByteArrayToHexString(deviceID ).toUpperCase()+")";
		// byte[] cmdNumBytes = host.getSelectedCmd(); // get the Host's current
		// cmd in 2byte format
		// final int hostCurrentCmd = (cmdNumBytes[0] << 8) + cmdNumBytes[1]; //
		// Convert 2 bytes into int
		//
		//// // Update the Host GUI only if the user has currently selected this
		// specific device.
		// if (Arrays.equals(deviceID, host.getSelectedDev())) {
		// SwingUtilities.invokeLater(new Runnable() {
		//
		// @Override
		// public void run() {
		// // Clear the Host's TextArea if we exceed the cache limit
		// host.clearTextArea(host.getMaxCacheItems());
		//
		// if (dataValue.length == 0) {
		// // If there is no data, display null message
		// host.displayRecvData(deviceIdAndTimestamp + ": " + " data is
		// null\n");
		// }
		// else {
		// // If the command number is 1, and data can be interpreted as a real
		// number,
		// // then update Host by displaying a real number and updating graph
		// if (hostCurrentCmd == 1 && dataValue.length % 4 == 0) {
		// double y = Converter.bytesToRealNumber(dataValue);
		//
		// host.displayRecvData(deviceIdAndTimestamp + ": " + y + "\n");
		// host.getGraph().drawPoint((float) y);
		//
		//
		// }
		// else {
		// // If the command number != 1, then just display the data as hex
		// bytes
		// counti=counti+1;
		// host.displayRecvData(deviceIdAndTimestamp + ": " +
		// Converter.ByteArrayToInt(dataValue,0) + "/"+counti+ "\n");}
		//
		// }
		// }// end run()
		// });
		// } // end of GUI updating logic
		//
		//
		// // If there is no data to save, just return
		// if (dataValue.length == 0)
		// break;
		//
		// // Get the current burst cmd num in string format, add to deviceID
		// and timestamp
		// String cmdNumStr =
		// Converter.ByteArrayToHexString(host.getSelectedCmd());
		// String info = cmdNumStr + " " + deviceIdAndTimestamp;
		//
		// // Save the received data into cache
		// CachedDataPoint dataPoint = new CachedDataPoint(info, dataValue,
		// hostCurrentCmd);
		// host.putDataInCache(deviceID, dataPoint);
		//
		// break;
		//
		// case 9: // Custom Cmd(device tag, cmd number, payload)
		// // The gateway sends back the response to the custom cmd sent by the
		// user, so display the command number + payload on the Host.
		//
		// // Get the command number
		// customCmdNum = input.getCustomCmdNum(); //2 bytes
		// final int cmdNumInteger = (customCmdNum[0] << 8) + customCmdNum[1];
		// // convert 2 byte command number into Integer
		//
		// // Get the payload (response code is the first byte in payload)
		// payload = input.getPayload();
		// final byte[] payload2 = new byte[payload.length - 1]; //holds the
		// payload except for status byte
		// System.arraycopy(payload, 1, payload2, 0, payload2.length); //remove
		// status byte payload and copy the rest into payload2
		//
		// SwingUtilities.invokeLater(new Runnable() {
		// @Override
		// public void run() {
		// if (payload2.length != 0) {
		// host.getCmdDlg().displayResponse("Received Cmd " +
		// Integer.toString(cmdNumInteger) + " Response:\n Status: " + (int)
		// payload[0] + "\n Payload: 0x" +
		// Converter.ByteArrayToHexString(payload2).toUpperCase());
		// }
		// else {
		// host.getCmdDlg().displayResponse("Received Cmd " +
		// Integer.toString(cmdNumInteger) + " Response:\n Status: " + (int)
		// payload[0]);
		// }
		// }
		// });
		// break;
		//
		// default: // Do nothing
		// break;
		// }
		// }

		// Convert timestamp from bytes to string
		// public String getTimeFromBytes(byte[] time) {
		// // Returns a string in this format:
		// // yyyy/MM/dd hh:mm:ss a
		//
		// int year = (time[0] * 100) + time[1];
		// int month = time[2];
		// int day = time[3];
		// int hour = time[4];
		// int minute = time[5];
		// int second = time[6];
		// int am = time[7]; //am = 0, pm = 1
		//
		// String output = "";
		// output += Integer.toString(year) + "/";
		// output += String.format("%02d", month) + "/";
		// output += String.format("%02d", day) + " ";
		// output += String.format("%02d", hour) + ":";
		// output += String.format("%02d", minute) + ":";
		// output += String.format("%02d", second) + " ";
		//
		// if(am == 0)
		// output += "AM";
		// else
		// output += "PM";
		//
		// return output;
		// }
	}
}