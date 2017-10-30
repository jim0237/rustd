// The purpose of this code is to read the telemetry data
// that is sent through the Aruduino serial port to the PC
//package com.fazecast.jSerialComm ;
import com.fazecast.jSerialComm.* ;
boolean startFrame, stopFrame;
public class rustD1 {
	public static void main(String[] args ) {
		startFrame = false;
		stopFrame = false;
		System.out.println( "This is the rustD code version 1" );
		System.out.println( "It gets telemetry data from the Arduino " );
		
	// Open and init the com port associated with the arduino
		SerialPort comPort = SerialPort.getCommPorts()[0];
		comPort.openPort();
		System.out.println("The speed of the com port = " + comPort.getBaudRate());
		comPort.addDataListener(new SerialPortDataListener() {
			@Override
				public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
			@Override
			   public void serialEvent(SerialPortEvent event)
			   {
				  if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
					 return;
				  byte[] newData = new byte[comPort.bytesAvailable()];
				  int numRead = comPort.readBytes(newData, newData.length);
				  System.out.println("Read " + numRead + " bytes.");
				  for (int i = 0; i < newData.length; ++i) {
				  		System.out.println(newData[i]);
						if(newData[i] == 0x7E) {
							if(startFrame){
								System.out.println("Framing error. Second start frame without End Frame");
								startFrame = false;
								stopFrame = false;
							}
							else{
								System.out.println("Found a Start Frame Byte");
								startFrame = true;
							}
						}
						if(newData[i] == 0x7D){
							System.out.println("Found a End Frame Byte")
							if(startFrame){
								stopFrame = true;
							}
							else if(stopFrame){
								System.out.println("Framing error. Second stop without a Start Frame");
								stopFrame = false;
								startFrame = false;
							}
							else {
								System.out.println("Framing error stop frame without a start frame");
								stopFrame = false;
								startFrame = false;
							} 	
						}
						System.out.println((char)newData[i]);
					}	
					System.out.println("\n");
			   }
		});
	}
}

