

package fahrradausleihe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.util.ArrayList;



public class Fahrradausleihe
{
  
  int               fahrradausleiheID;
  int				fahrradnummer;
  String            kundenname;
  LocalDate			ausleihDatum;
  

 /*
 * diverse Konstruktoren
 */

   public Fahrradausleihe( int	pFahrradnummer, String pName, LocalDate pDatumBegin)
   {
	  super();
	  this.fahrradnummer  	= pFahrradnummer;
	  this.kundenname     	= pName;
	  this.ausleihDatum		= pDatumBegin;
    
   }

   public Fahrradausleihe( int pFahrradausleiheIDWert, int	pFahrradnummer, String pName, LocalDate pDatumBegin)
   {
	  super();
	  this.fahrradausleiheID  = pFahrradausleiheIDWert;
	  this.fahrradnummer  	= pFahrradnummer;
	  this.kundenname         = pName;
	  this.ausleihDatum		= pDatumBegin;

   }
   public Fahrradausleihe( int pFahrradausleiheIDWert)
   {
	  super();
	  this.fahrradausleiheID  = pFahrradausleiheIDWert;

   }
   /*
    * Methoden
    */
   /**
    * Methode ermittelt die größte in der Datenbank verwendete AusleihID
    * wird zum Anlegen neuer Datens�tze ben�tigt
    * @return fahrradausleiheID
    */
    public int holengroessteAusleihID(Connection pConnection)
    {
	     int lHöchsterWert=1;
	 
         Statement lBefehl;
	     ResultSet lErgebnis;


	     try
	     {
		    lBefehl = pConnection.createStatement();
		    lErgebnis=lBefehl.executeQuery("select max(f.id) FROM test.fahrradausleihe f;");
		    lErgebnis.first();
		    lHöchsterWert=lErgebnis.getInt(1);
		
	      }

	     catch (SQLException e)
	     {
		   
		     e.printStackTrace();
	    
	     }

	     return lHöchsterWert;
    }
    /**
     * Prüft ob es einen Ausleihvorgang gibt. Falls es einen Ausleihvorgang gibt wird er ausgelesen
     * @return true / false
     */
    public boolean gibtEsFahrradausleiheID(Connection pConnection)
    {
	    int lAusgeleseneID=-1;
	    Statement lBefehl;
	    ResultSet lErgebnis;
	    try 
	    {
	        lBefehl=pConnection.createStatement();
	        lErgebnis = lBefehl.executeQuery("SELECT * FROM fahrradausleihe where ID="+this.getFahrradausleiheID());
	        lErgebnis.first();
	  
	        while(! lErgebnis.isAfterLast())
	        {
   	             lAusgeleseneID=lErgebnis.getInt(1);
		         this.setFahrradnummer(lErgebnis.getInt(2));
   	             this.setKundenname(lErgebnis.getString(3));
   	             this.setAusleihDatum(lErgebnis.getDate(4).toLocalDate());
		         lErgebnis.next();
	        }
	   
	        if (this.getFahrradausleiheID()==lAusgeleseneID)
		    {	
		        return true;
		    }
	        else
	        {
		        return false;
	        }
         }
	     catch (Exception ex)
		 {
           return false;
		 }
     }

 
/*
 * Getter/Setter
 */

public LocalDate getAusleihDatum() {
	return ausleihDatum;
}

public void setAusleihDatum(LocalDate ausleihDatum) {
	this.ausleihDatum = ausleihDatum;
}

public int getFahrradausleiheID() {
	return fahrradausleiheID;
}

public void setFahrradausleiheID(int fahrradausleiheID) {
	this.fahrradausleiheID = fahrradausleiheID;
}

public int getFahrradnummer()
{
	return fahrradnummer;
}

public void setFahrradnummer(int pFahrradnummer)
{
	this.fahrradnummer = pFahrradnummer;
}

public String getKundenname()
{
	return kundenname;
}

public void setKundenname(String pName)
{
	this.kundenname = pName;
}

}