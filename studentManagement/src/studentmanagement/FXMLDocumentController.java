/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDialogEvent;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;




public class FXMLDocumentController implements Initializable {

   
    
    @FXML private JFXTextField firstName,secondName,thirdName,fourthName,phoneNumber,address;
    @FXML private JFXCheckBox qc,hc,qhc,fc;
    @FXML private JFXDatePicker dataPicker ;
    @FXML private JFXTextArea notes;
    @FXML private Button add,move;
    @FXML private Label lbl;
    @FXML private FontAwesomeIconView minimze;
    @FXML private StackPane sp;
     private Connection conn = null;
     private Statement state = null;
     private ResultSet rs = null;
     private String price ;
     private double width , height;
     private String color = "-fx-background-color: red;";
     private AnchorPane ap;
     
     Stage stage = new Stage();
    
    
 
    StudentManagement sm = new StudentManagement();
    @FXML
    private void cost(){
        if (fc.isSelected()){
            qhc.setSelected(true);
            hc.setSelected(true);
            qc.setSelected(true);
            price = "full price";
        }
        else if (qhc.isSelected()){
            hc.setSelected(true);
            qc.setSelected(true);
            price = "quarter half price";
            
        } else if (hc.isSelected()){
            qc.setSelected(true);
            price = "half price";
        }
        else if (qc.isSelected()) {
                     price = "quarter price";
                    }
        else {
            price = "";
        }
            
            
        
        
    }
    
  
    @FXML 
            private void sceneTwo(ActionEvent event) throws SQLException{
                
        try {
             
            Parent   root = FXMLLoader.load(getClass().getResource("FXMLVIEWPAGE.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            root.requestFocus();
          ((Node)(event.getSource())).getScene().getWindow().hide();
          
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            stage.setResizable(false);
            
            
            
            conn.close();
            state.close();
            rs.close();
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
    
    @FXML
    void addtional () throws SQLException{
        
       if(firstName.getText().isEmpty()||secondName.getText().isEmpty()||thirdName.getText().isEmpty()||
                 fourthName.getText().isEmpty()||phoneNumber.getText().isEmpty()||address.getText().isEmpty()||
                 (qc.isSelected()==false&&hc.isSelected()==false&&qhc.isSelected()==false&&fc.isSelected()==false) || dataPicker.getValue()==null){
             
            

                  firstName.setStyle(null);
                  secondName.setStyle(null);
                  thirdName.setStyle(null);
                  fourthName.setStyle(null);
                  phoneNumber.setStyle(null);
                  address.setStyle(null);
                  qc.setStyle(null);
                  hc.setStyle(null);
                  qhc.setStyle(null);
                  fc.setStyle(null);
                  dataPicker.setStyle(null);
                  
                  if(firstName.getText().isEmpty()){
             
             firstName.setStyle(color);
         }
             
             
             if(secondName.getText().isEmpty()){
                 
                 secondName.setStyle(color);
                 
             }
                 if(thirdName.getText().isEmpty()){
                     
                     thirdName.setStyle(color);
                     
                 }
                 if(fourthName.getText().isEmpty()){
                     fourthName.setStyle(color);
                     
                 }if(phoneNumber.getText().isEmpty()){
                     phoneNumber.setStyle(color);
                 }
                  if(address.getText().isEmpty()){
                      address.setStyle(color);
                      
                  }
                  if(qc.isSelected()==false&&hc.isSelected()==false&&qhc.isSelected()==false&&fc.isSelected()==false) {
                      
                      qc.setStyle(color);
                      hc.setStyle(color);
                      qhc.setStyle(color);
                      fc.setStyle(color);
                  }
                  if(dataPicker.getValue()==null){
                      
                      dataPicker.setStyle(color);
                          }
                
System.out.println("work");

         }else{
           conn = DBConnection.condb();
        System.out.println(firstName.getText());
             LocalDate ld = dataPicker.getValue();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO `register`"
                     + "(`name`, `father name`, `grand name`, `price`, `birthday`, `address`, `notes`, `phone Number`, `mother Name`)"
                     + "VALUES (?,?,?,?,?,?,?,?,?)");
                         
                         ps.setString(1, firstName.getText());
                         ps.setString(2, secondName.getText());
                         ps.setString(3, thirdName.getText());
                         ps.setString(4, price);
                         ps.setString(5, ld.toString());
                         ps.setString(6, address.getText());
                         ps.setString(7, notes.getText());
                         ps.setString(8, phoneNumber.getText());
                         ps.setString(9, fourthName.getText());
                         ps.execute();
 
                
//                String sql = "INSERT INTO `register`"
//                        + "(`name`, `father name`, `grand name`, `price`, `birthday`, `address`, `notes`, `phone Number`, `mother Name`)"
//                        + " VALUES (\""+firstName.getText()+"\",\""+secondName.getText()+"\",\""+thirdName.getText()+"\",\""+price+"\",'"+ld+
//                         "',\""+address.getText()+"\",\""+notes.getText()+"\","+phoneNumber.getText()+",\""+fourthName.getText()+"\")"  ;
//                  
//                 state.executeUpdate(sql);
                 firstName.setText("");
                 secondName.setText("");
                 thirdName.setText("");
                 qc.setSelected(false);
                 hc.setSelected(false);
                 qhc.setSelected(false);
                 fc.setSelected(false);
                 LocalDate ll = LocalDate.of(1111, Month.JANUARY, 1);
                 dataPicker.setValue(ll); 
                 address.setText("");
                 notes.setText("");
                 phoneNumber.setText("");
                 fourthName.setText("");
                 
                 JFXDialogLayout jfxdl = new JFXDialogLayout();
                JFXDialog dialog = new JFXDialog(sp, jfxdl, JFXDialog.DialogTransition.CENTER,true);
                jfxdl.getStyleClass().add("dialoglayout");
                jfxdl.setBody(new Label("تم اضافة الطالب"));
                dialog.show();
                
             
                 
         }
                 
               
              
                
                
                
        
    }
              
     
    
 
      
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            // TODO
            
            
             try {
            
            conn = DBConnection.condb();
           
            
//            System.out.println("connected !");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            phoneNumber.setPrefColumnCount(11);
            
            notes.setOnKeyPressed((event) -> {
                notes.setOpacity(1.0);
            });
            
          
        
           
    }    
    
     @FXML
    void dragged(MouseEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - width);
        stage.setY(event.getScreenY() - height);

    }
    
    
    @FXML
    void pressed(MouseEvent event) {
        width = event.getSceneX();
        height = event.getSceneY();

    }
    @FXML
    void pres(MouseEvent event){
    System.exit(0);
}
    
     @FXML
    void down(MouseEvent event) {
       // ((Stage)((FontAwesomeIconView)e.getSource()).getScene().getWindow()).setIconified(true);
      //   ((Stage)((Node)event.getSource()).getScene().getWindow()).setIconified(true);
         Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
         s.setIconified(true);
      
    }
    
}
