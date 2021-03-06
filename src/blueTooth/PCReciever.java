package blueTooth;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class PCReciever {

	public static void main(String[] args) {
		
		try {
			NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
			NXTInfo[] info = nxtComm.search("NXT");
			
			for(NXTInfo i : info){
				System.out.println(i.name + " at " + i.deviceAddress);
			}
			
			nxtComm.open(info[0]);
			
			try(InputStream is = nxtComm.getInputStream()){
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int i = -1;
				while((i = is.read(b)) != -1){
					byteStream.write(b);
					if(byteStream.toString().contains("\r\n\r\n")){
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
		
	}

}
