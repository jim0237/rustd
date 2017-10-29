// class to deal with communication with Arduino
// An Ardunio board will be sending data from varius sensors to the PC 
// This class will allow data framming loosly based on PPP 
//

public class dataProtocol {
	public int startFlag(){
		return (0x7E);
		
	}
	
	public int stopFlag(){
		return(0x7E);
	}
	
	public int ESC(){
		return(0x7D);
	}
	
	
}