/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement;

import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.stage.Stage;

import java.sql.PreparedStatement;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author hussein
 */
public class FXMLVIEWPAGEController implements Initializable {

    @FXML private Button add,btnUndo;
    @FXML public TableView Table;
    @FXML public TableColumn<ModelTable,String> col_id ;
    @FXML private TableColumn<ModelTable,String> col_name ;
    @FXML private TableColumn<ModelTable,String> col_fn ;
    @FXML private TableColumn<ModelTable,String> col_gn ;
    @FXML private TableColumn<ModelTable,String> col_mn ;
    @FXML private TableColumn<ModelTable,String> col_birth ;
    @FXML private TableColumn<ModelTable,String> col_ad ;
    @FXML private TableColumn<ModelTable,String> col_not ;
    @FXML private TableColumn<ModelTable,String> col_pn ;
    @FXML private TableColumn<ModelTable,String> col_price ;
    @FXML private JFXTextField searchText;
    private double width , height;

    StudentManagement sm = new StudentManagement();
    ModelTable mt = new ModelTable();









    public ObservableList<ModelTable> data =  FXCollections.observableArrayList();
    public FilteredList<ModelTable> filteredData = new FilteredList<>(data, p -> true);




     private Statement state = null;
     private Connection conn = null;
     private Connection con = null;
     private ResultSet rs = null;






    @FXML
    private void back(ActionEvent event){

        try {

            ((Node)(event.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            sm.start(stage);
        } catch (IOException ex) {
            Logger.getLogger(FXMLVIEWPAGEController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FXMLVIEWPAGEController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle rb) {



        Table.setStyle("-fx-background-color: TRANSPARENT;");
        col_id.setStyle("-fx-background-color: TRANSPARENT;");

        try {
            String SQL = "SELECT * FROM `register`";
            conn = DBConnection.condb();

              state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            state = conn.createStatement();

            rs = conn.createStatement().executeQuery(SQL);


            while (rs.next()) {

                data.add(new ModelTable(rs.getString("id"),rs.getString("name"),rs.getString("father name"),
                        rs.getString("grand name"),rs.getString("mother name"),rs.getString("birthday"),rs.getString("address"),rs.getString("notes"),
                        rs.getString("phone number"),rs.getString("price")));
            }


            Table.setItems(data);

            } catch (SQLException ex) {
            Logger.getLogger(FXMLVIEWPAGEController.class.getName()).log(Level.SEVERE, null, ex);
        }

            // TODO

            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_fn.setCellValueFactory(new PropertyValueFactory<>("fn"));
            col_gn.setCellValueFactory(new PropertyValueFactory<>("gn"));
            col_mn.setCellValueFactory(new PropertyValueFactory<>("mn"));
            col_birth.setCellValueFactory(new PropertyValueFactory<>("birth"));
            col_ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
            col_not.setCellValueFactory(new PropertyValueFactory<>("not"));
            col_pn.setCellValueFactory(new PropertyValueFactory<>("pn"));
            col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

           edit();





        searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(ModelTable -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(ModelTable.getId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;

                } else if (String.valueOf(ModelTable.getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                 else if (String.valueOf(ModelTable.getFn()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                 else if (String.valueOf(ModelTable.getGn()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                 else if (String.valueOf(ModelTable.getMn()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                 else if (String.valueOf(ModelTable.getBirth()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                 else if (String.valueOf(ModelTable.getAd()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                 else if (String.valueOf(ModelTable.getNot()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                 else if (String.valueOf(ModelTable.getPn()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                 else if (String.valueOf(ModelTable.getPrice()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });


        SortedList<ModelTable> sortedData = new SortedList<>(filteredData);


        sortedData.comparatorProperty().bind(Table.comparatorProperty());

        Table.setItems(sortedData);


    }


    public void edit(){




                  col_id.setCellFactory(TextFieldTableCell.forTableColumn());
            col_id.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
                 ModelTable user = e.getRowValue();
                 user.setId(e.getNewValue());
                 updateData("id", e.getNewValue(), user.getId());


            });



            col_name.setCellFactory(TextFieldTableCell.forTableColumn());
            col_name.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());

                ModelTable user = e.getRowValue();
                 user.setName(e.getNewValue());
                 updateData("name", e.getNewValue(), user.getId());

            });



            col_fn.setCellFactory(TextFieldTableCell.forTableColumn());
            col_fn.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setFn(e.getNewValue());

                ModelTable user = e.getRowValue();
                 user.setFn(e.getNewValue());
                 updateData("father name", e.getNewValue(), user.getId());
            });

            col_gn.setCellFactory(TextFieldTableCell.forTableColumn());
            col_gn.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setGn(e.getNewValue());

                 ModelTable user = e.getRowValue();
                 user.setGn(e.getNewValue());
                 updateData("grand name", e.getNewValue(), user.getId());


            });

            col_mn.setCellFactory(TextFieldTableCell.forTableColumn());
            col_mn.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setMn(e.getNewValue());

                 ModelTable user = e.getRowValue();
                 user.setMn(e.getNewValue());
                 updateData("mother name", e.getNewValue(), user.getId());
            });

            col_birth.setCellFactory(TextFieldTableCell.forTableColumn());
            col_birth.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setBirth(e.getNewValue());

                 ModelTable user = e.getRowValue();
                 user.setBirth(e.getNewValue());
                 updateData("birthday", e.getNewValue(), user.getId());
            });

            col_ad.setCellFactory(TextFieldTableCell.forTableColumn());
            col_ad.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setAd(e.getNewValue());

                 ModelTable user = e.getRowValue();
                 user.setAd(e.getNewValue());
                 updateData("address", e.getNewValue(), user.getId());
            });

            col_not.setCellFactory(TextFieldTableCell.forTableColumn());
            col_not.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setNot(e.getNewValue());

                 ModelTable user = e.getRowValue();
                 user.setNot(e.getNewValue());
                 updateData("notes", e.getNewValue(), user.getId());
            });

            col_pn.setCellFactory(TextFieldTableCell.forTableColumn());
            col_pn.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setPn(e.getNewValue());

                 ModelTable user = e.getRowValue();
                 user.setPn(e.getNewValue());
                 updateData("phone number", e.getNewValue(), user.getId());
            });

            col_price.setCellFactory(TextFieldTableCell.forTableColumn());
            col_price.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrice(e.getNewValue());

                 ModelTable user = e.getRowValue();
                 user.setPrice(e.getNewValue());
                 updateData("price", e.getNewValue(), user.getId());

            });


            Table.setEditable(true);


    }

    private void updateData(String column, String newValue, String id) {


    try {
        con = DBConnection.condb();
        PreparedStatement stmt = con.prepareStatement("UPDATE register SET `"+column+"` = ? WHERE id = ? ");

        stmt.setString(1, newValue);
        stmt.setString(2, id);
        stmt.execute();
    } catch (SQLException ex) {
        System.err.println("Error");
        // if anything goes wrong, you will need the stack trace:
        ex.printStackTrace(System.err);
    }
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

