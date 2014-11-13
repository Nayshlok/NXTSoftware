package blueTooth;

import javax.bluetooth.RemoteDevice;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class RemoteConsoleTest {

	public static void main(String[] args){
		NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		NXTInfo info = new NXTInfo(NXTCommFactory.BLUETOOTH, "NXT", "00:16:53:0D:DD:A7");
	
		nxtComm.open(info);
		RemoteDevice.getRemoteDevice();
		
	}
}
