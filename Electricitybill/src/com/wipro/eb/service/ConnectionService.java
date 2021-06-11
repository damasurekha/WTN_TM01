package com.wipro.eb.service;

/*package com.wipro.eb.service;
import com.wipro.eb.exception.*;
import com.wipro.eb.entity.*;


public class ConnectionService {

public boolean validate(int currentReading, int previousReading, String type) throws InvalidReadingException, InvalidConnectionException
{
	//• If the currentReading is less than previousReading or if any of the readings 
	//are negative then the function should throw InvalidReadingException	
	
		if(currentReading<previousReading 
				|| currentReading<0 
				|| previousReading<0) {
			
			throw new InvalidReadingException();
			
		}
		
		if(!type.equals("Commercial")
				&& !type.equals("Domestic")) {
			
			throw new InvalidConnectionException();
		
		}
		
	return true;
	
}
public float calculateBillAmt(int currentReading, int previousReading, String type) 
{
	float tb;
	float units = currentReading - previousReading;
	if(type.equals(""))
	if(units<50)
		tb=(float) (units*2.3);
		if(units<=100)
		tb = (50*2.3)+(units - 50)*4.2;
		else
		tb = (50*2.3)+(50*4.2)+(units-100)*5.5;
	
	return tb;
}

public String generateBill(int currentReading, int previousReading, String type)
{
	
}

}*/





 


import com.wipro.eb.exception.InvalidConnectionException;
import com.wipro.eb.exception.InvalidReadingException;
import com.wirpo.eb.entity.Commercial;
import com.wirpo.eb.entity.Connection;
import com.wirpo.eb.entity.Domestic;

 

public class ConnectionService {
    public boolean validate(int currentReading,int previousReading,String type)throws InvalidReadingException,InvalidConnectionException {
        
        if((currentReading<previousReading)
                ||(currentReading<0)
                ||(previousReading<0)) {
            throw new InvalidReadingException();
        }
        
        //else if(!type.equals("Domestic")&&!type.equals("Commercial"))
        else if( !(type.equals("Domestic")
                ||type.equals("Commercial") )){
            throw new InvalidConnectionException();
            }
        else {
            return true;
        }
    }
    
    /*public float calculateBillAmt(int currentReading,int previousReading,String type) {
        try {
            validate(currentReading,previousReading,type);
            if(type.equals("Domestic")) {
                float[] slabs= {2.3f,4.8f,5.5f};
                Domestic d=new Domestic(currentReading,previousReading,slabs);
                float bill=d.computeBill();
                return bill;
            }
            else {
                float[] slabs= {5.2f,6.8f,8.3f};
                Commercial c=new Commercial(previousReading, previousReading, slabs);
                float bill=c.computeBill();
                return bill;
            }    
        }
        catch (InvalidReadingException e) {
            return -1;
        }
        catch (InvalidConnectionException e) {
            return -2;
        }
        }
        */    
    public float calculateBillAmt(int currentReading,int previousReading,String type) {
        try {
            //Call the validate method to check inputs
            validate(currentReading,previousReading,type);
            float[] slabs;
            Connection conn;
            //Invoke appropriate Connection type object
            if(type.equals("Domestic")) {
                slabs= new float[]{2.3f,4.2f,5.5f};
                conn=new Domestic(currentReading,previousReading,slabs);
            }
            else {
                slabs= new float[]{5.2f,6.8f,8.3f};
                conn=new Commercial(currentReading, previousReading, slabs);
            }
            //Calculate the bill and return the value
            float bill=conn.computeBill();
            return bill;
        }
        catch (InvalidReadingException e) {
            return -1;
        }
        catch (InvalidConnectionException e) {
            return -2;
        }
        }
    public String generateBill(int currentReading,int previousReading,String type) {
        float amount=calculateBillAmt(currentReading,previousReading,type);
        if(amount==-1) {
            return "Incorrect Reading";
        }
        else if(amount==-2) {
            return "Invalid ConnectionType";
        }
        else {
            return "Amount to be paid:"+amount;
        }
    }
}