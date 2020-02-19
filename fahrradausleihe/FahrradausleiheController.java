

package fahrradausleihe;

import java.sql.Connection;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class FahrradausleiheController
{
	private static final Datenbankverbindung dbVerbindung = new Datenbankverbindung();
	
	@FXML
    private TextField tfsuchenIDWert;
	
	@FXML
    private TextField tfFahrradausleiheIDWert;

    @FXML
    private TextField tfFahrradnummer;

	@FXML
    private TextField tfName;

	@FXML
	private DatePicker dpAusleihdatum;

    @FXML
	private TextField tfHinweise;

    @FXML
    private Button    btAnzeigenAusleihe;
/**
 * zeigt einen Datensatz zur Eingegebenen Ausleihnummer an.
 * @param event
 */
    @FXML
    void anzeigenAusleihe(ActionEvent event)
    {
    	Connection lConnection = dbVerbindung.getConnection();    	
    	Fahrradausleihe lFahrradausleihe = new Fahrradausleihe( Integer.parseInt(tfsuchenIDWert.getText()) );
        if (lFahrradausleihe.gibtEsFahrradausleiheID(lConnection))
        {
           // Ausgabe im Formular
    	   tfFahrradausleiheIDWert.setText(String.valueOf(lFahrradausleihe.getFahrradausleiheID()));
    	   tfName.setText(lFahrradausleihe.getKundenname());
           tfFahrradnummer.setText(String.valueOf(lFahrradausleihe.getFahrradnummer()));
           dpAusleihdatum.setValue(lFahrradausleihe.getAusleihDatum());  
           tfHinweise.setText("");
        }
        else
        {
    	   tfHinweise.setText("Diesen Ausleihvorgang gibt es nicht");
    	   tfFahrradausleiheIDWert.setText("");
    	   tfFahrradnummer.setText("");
    	   tfName.setText("");   	 
    	   dpAusleihdatum.setValue(null);
        }
       
    }

    @FXML
   public void initialize()
   {
    	dbVerbindung.verbinden();
        
   }
  /**
   *
   */
 
}

