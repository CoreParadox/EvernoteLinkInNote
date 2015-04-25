package evernotelinkinnote;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author CoreParadox
 * @version 1.1
 */
public class EvernoteLinkInNote extends Application implements Initializable{
    @FXML
    TextField txtJump;
    @FXML
    TextField txtLink;
    @FXML
    TextField txtDestination;
    @FXML
    TitledPane paneLink;
    @FXML
    TitledPane paneDestination;
    @FXML
    Button btnGenerate;
    @FXML
    WebView webLink;
    @FXML
    Label lblVersion;
    @FXML
    Label lblError;
    
    public static final String VERSION = "Version 1.1";
    private final String firstHalf = "<!doctype html><html><head></head><body>";
    private final String secondHalf = "</body></html>";
    private final String ERROR_TEXT = "All fields must be completed.";
     String jump = "";
    String linkText = "";
    String destination = "";
    public static void main(String[] args) {
        Application.launch(EvernoteLinkInNote.class, (java.lang.String[])null); 
    }
    
    public void generateLinks(ActionEvent e) {
        lblError.setText("");
        
        if(txtJump.getText() != null && !txtJump.getText().isEmpty() 
                && txtLink.getText() != null && !txtLink.getText().isEmpty()
                && txtDestination.getText() != null && !txtDestination.getText().isEmpty()){
            jump = txtJump.getText();
            linkText = txtLink.getText();
            destination = txtDestination.getText();
            String anchor = "<a name=\""+jump+"\">"+destination+"</a>";
            String link = "<a href=\"file:///#"+jump+"\">"+linkText+"</a><br><br>";

            webLink.setFontSmoothingType(FontSmoothingType.LCD);
            webLink.getEngine().loadContent(firstHalf+link+anchor+secondHalf);
        }else{
            lblError.setText(ERROR_TEXT);
        }
        
        
        

    }
    
    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Evernote Link in Note Generator");
        stage.setScene(scene);
        stage.show();
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblVersion.setText(VERSION);
    }
}
