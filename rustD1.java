// The purpose of this code is to read the telemetry data
// that is sent through the Aruduino serial port to the PC
//package com.fazecast.jSerialComm ;
import com.fazecast.jSerialComm.* ;

public class rustD1 {
	public static void main(String[] args ) {
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
						if(newData[i] == 13) {
							System.out.println("Found a carriage return");
						}
						if(newData[i] == 10) {
							System.out.println("Found a line feed");
						}
						System.out.println((char)newData[i]);
					}	
					System.out.println("\n");
			   }
		});
	}
}

