package org.glassfish.jersey.archetypes;

import java.util.ArrayList;
import java.util.List;

public class ClssSecuenciaADN {

	//A C G T	
	private char SccC;
	private char SccG;
	private char SccT;
	private char SccA;	
	private String[] adn;
	
	public char getSccA() {
		return 'A';
	}
	public char getSccC() {
		return 'C';
	}
	public char getSccG() {
		return 'G';
	}
	public char getSccT() {
		return 'T';
	}
	
	public Boolean isMutant(String[] pADN)
	{		 
		boolean isValid=false;
		
		
		for (String item : pADN) {	
		
			int freqA =0, freqC = 0,freqG = 0,freqT=0;
			
			for (char  c : item.toCharArray()) {
			  if(c==getSccA())
			  freqA++;
			  if(c==getSccC())
			  freqC++;
			  if (c==getSccG()) 
			  freqG++;
			  if (c==getSccT()) 
			  freqT++;			
			}
			if(freqA==4||freqC==4||freqG==4||freqT==4)
			{
				isValid=true;			
				break;
			}
		 }
		return isValid;
	}
	public Boolean SecuenciaValida(String[] pADN)
	{		
	 boolean isValid=false;
	 
	 for (String item : pADN) {		 		 
		for (char  c : item.toCharArray()) {
			
			if(!Character.isDigit(c)&&(c==getSccA()||c==getSccC()||c==getSccG()||c==getSccT()))
			{ 
			isValid=true;				
			}
			else
			{
				isValid=false;
				break;
			}			
		}
		if(!isValid)
		{
			break;
		}
	 }
	 if(isValid)
	 {
		adn=pADN; 
	 }
	 
		return isValid;
	}
}
