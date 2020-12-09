/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prng;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 *
 * @author chala sahim
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private JFXTextField RoundTxt;
    
    @FXML JFXPasswordField KeyTxt;
    @FXML 
    private JFXButton Btn;
    @FXML
    private Label txtDon,txtEror,txtEror1;
    @FXML
    private ImageView imgDon,imgEror;
       
  
      
    
    
    
  @FXML
    @SuppressWarnings("empty-statement")
    private void handleButtonAction(ActionEvent event) throws UnsupportedEncodingException, Exception {
         String keytxt= KeyTxt.getText();
         String r= RoundTxt.getText();
         int Round = 0;
         boolean isInteger;
         try{
             Integer.parseInt(r);
             isInteger= true;
             Round = Integer.parseInt(r);
         }catch(NumberFormatException e){
             isInteger =false;
         }
         
        
         byte[] Key = keytxt.getBytes();
        
         Proposal pos=new Proposal(Round);
             
         
              if (keytxt.length() == 8 && Round>0 && isInteger) {
                   pos.writeBytes(pos.GenwithLFSR(Round, Key), "StreamKey.txt");
                   if (Desktop.isDesktopSupported()){
                       Desktop desktop = Desktop.getDesktop();
                       File myFile = new File("StreamKey.txt");
                       desktop.open(myFile);  
                 }
            txtDon.setVisible(true);
            imgDon.setVisible(true); 
            txtEror.setVisible(false);
            txtEror1.setVisible(false);
            imgEror.setVisible(false);
              
              } else{
                 
                  
                  txtEror.setVisible(true);
                  txtEror1.setVisible(true);
                  imgEror.setVisible(true); 
                  txtDon.setVisible(false);
                  imgDon.setVisible(false);
              
              }
    }
    
    
    @FXML
    private void handleButtonClose(ActionEvent e) {
       System.exit(0);
    }
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
