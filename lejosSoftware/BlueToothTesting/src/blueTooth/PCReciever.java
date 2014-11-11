package blueTooth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class PCReciever {

	public static void main(String[] args) {
		
		try {
			NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
			NXTInfo info = new NXTInfo(NXTCommFactory.BLUETOOTH, "NXT", "00:16:53:0D:DD:A7");
			//NXTInfo[] info = nxtComm.search("NXT");
			
//			for(NXTInfo i : info){
//				System.out.println(i.deviceAddress + ", " + i.name);
//			}
			
			nxtComm.open(info);
			
			try(InputStream is = nxtComm.getInputStream()){
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int i = -1;
				while((i = is.read(b)) != -1){
					byteStream.write(b);
					if(!byteStream.toString().isEmpty()){
						System.out.println(byteStream.toString());
						byteStream.reset();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (NXTCommException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        System.out.println("Hello World!");
//        Motor.A.forward();
	}

}
