/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package address.book;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author rianix
 */
public class MainFXMLDocumentController implements Initializable {
    
    @FXML
    private TextField newNicknameField;
    @FXML
    private TextField newEmailField;
    @FXML
    private TextField newWebsiteField;
    @FXML
    private TextField newCountryField;
    @FXML
    private TextField newCityField;
    @FXML
    private TextField newAddressField;
    @FXML
    private DatePicker newBirthDatepicker;
    @FXML
    private TextArea newNotesArea;
    @FXML
    private TextField newNameField;
    @FXML
    private TextField newMobileNumberField;
    @FXML
    private TextField newFaxNumberField;
    @FXML
    private TextField newPhoneNumberField;
    @FXML
    private TextField newOtherNumberField;
    @FXML
    private ImageView newProfileImageView;
    @FXML
    private TabPane tabPane;

    @FXML
    private Button newUploadProfilePictureOption;
    @FXML
    private Button newSaveOption;
    @FXML
    private Button newResetOption;
    
    @FXML
    private Button contactDeleteButton;
    @FXML
    private TextField oldNicknameField;
    @FXML
    private TextField oldWebsiteField;
    @FXML
    private TextField oldCountryField;
    @FXML
    private TextField oldCityField;
    @FXML
    private TextField oldAddressField;
    @FXML
    private DatePicker oldBirthDatepicker;
    @FXML
    private TextArea oldNotesArea;
    @FXML
    private TextField oldNameField;
    @FXML
    private TextField oldMobileNumberField;
    @FXML
    private TextField oldFaxNumberField;
    @FXML
    private TextField oldPhoneNumberField;
    @FXML
    private TextField oldOtherNumberField;
    @FXML
    private ImageView oldProfileImageView;
    @FXML
    private TextField oldEmailField;
    @FXML
    private Button oldUploadProfilePictureOption;
    

    private int currentIndex;
    @FXML
    private TableView<ContactInfo> contactTable;
    private ListView<ContactInfo> contactListView;
    @FXML
    private TableColumn<ContactInfo, String> nameColumn;
    @FXML
    private TableColumn<ContactInfo, String> nicknameColumn;
    @FXML
    private Tab contactInformationAndInformationEditTab;
    @FXML
    private Tab createNewContactTab;
    @FXML
    private Tab createNewGroupAndEditGroupInformationTab;
    @FXML
    private Button oldSave;
    @FXML
    private Button oldEditInfo;
    @FXML
    private Button oldCancel;
    @FXML
    private TextField searchField;
    
    private void reset(){
        newNameField.setText("");
        newNicknameField.setText("");
        newMobileNumberField.setText("");
        newFaxNumberField.setText("");
        newPhoneNumberField.setText("");
        newOtherNumberField.setText("");
        newEmailField.setText("");
        newWebsiteField.setText("");
        newCountryField.setText("");
        newCityField.setText("");
        newAddressField.setText("");
        newBirthDatepicker.setValue(null);
        newNotesArea.setText("");
        GlobalDatas.imagePath = "AddressBook/Data/Image/profile.png";
        
        
        
        File file = new File(GlobalDatas.imagePath);
        Image profileImage = new Image(file.toURI().toString());
        newProfileImageView.setImage(profileImage);
    }
    
    private void Default(){
        oldNameField.setText("");
        oldNicknameField.setText("");
        oldMobileNumberField.setText("");
        oldFaxNumberField.setText("");
        oldPhoneNumberField.setText("");
        oldOtherNumberField.setText("");
        oldEmailField.setText("");
        oldWebsiteField.setText("");
        oldCountryField.setText("");
        oldCityField.setText("");
        oldAddressField.setText("");
        oldBirthDatepicker.setValue(null);
        oldNotesArea.setText("");
        GlobalDatas.imagePath = "AddressBook/Data/Image/profile.png";
        
        
        
        File file = new File(GlobalDatas.imagePath);
        Image profileImage = new Image(file.toURI().toString());
        oldProfileImageView.setImage(profileImage);
    }
    
    private void display(){
        oldNameField.setText(GlobalDatas.contactInfo.get(currentIndex).getName());
        oldNicknameField.setText(GlobalDatas.contactInfo.get(currentIndex).getNickname());
        oldMobileNumberField.setText(GlobalDatas.contactInfo.get(currentIndex).getMobileNumber());
        oldFaxNumberField.setText(GlobalDatas.contactInfo.get(currentIndex).getFaxNumber());
        oldPhoneNumberField.setText(GlobalDatas.contactInfo.get(currentIndex).getPhoneNumber());
        oldOtherNumberField.setText(GlobalDatas.contactInfo.get(currentIndex).getOtherNumber());
        oldEmailField.setText(GlobalDatas.contactInfo.get(currentIndex).getEmail());
        oldWebsiteField.setText(GlobalDatas.contactInfo.get(currentIndex).getWebsite());
        oldCountryField.setText(GlobalDatas.contactInfo.get(currentIndex).getCountry());
        oldCityField.setText(GlobalDatas.contactInfo.get(currentIndex).getCity());
        oldAddressField.setText(GlobalDatas.contactInfo.get(currentIndex).getAddress());
        String line = GlobalDatas.contactInfo.get(currentIndex).getBirthDate();
        if(line.equals("null")){
        oldBirthDatepicker.setValue(null);
        }else{
            oldBirthDatepicker.setValue(LocalDate.parse(line, DateTimeFormatter.ISO_DATE));
        }
        oldNotesArea.setText(GlobalDatas.contactInfo.get(currentIndex).getNotes());

        
        File file = new File(GlobalDatas.contactInfo.get(currentIndex).getProfilePicture());
        Image profileImage = new Image(file.toURI().toString());
        oldProfileImageView.setImage(profileImage);
    }
    
    private void disable(){
        oldNameField.setEditable(false);
        oldNicknameField.setEditable(false);
        oldMobileNumberField.setEditable(false);
        oldFaxNumberField.setEditable(false);
        oldPhoneNumberField.setEditable(false);
        oldOtherNumberField.setEditable(false);
        oldEmailField.setEditable(false);
        oldWebsiteField.setEditable(false);
        oldCountryField.setEditable(false);
        oldCityField.setEditable(false);
        oldAddressField.setEditable(false);
        oldBirthDatepicker.setEditable(false);
        oldNotesArea.setEditable(false);
        oldUploadProfilePictureOption.setDisable(true);
        oldSave.setDisable(true);
        contactDeleteButton.setDisable(true);
        oldEditInfo.setDisable(false);
        oldCancel.setDisable(true);
    }
    
    private void enable(){
        oldNameField.setEditable(true);
        oldNicknameField.setEditable(true);
        oldMobileNumberField.setEditable(true);
        oldFaxNumberField.setEditable(true);
        oldPhoneNumberField.setEditable(true);
        oldOtherNumberField.setEditable(true);
        oldEmailField.setEditable(true);
        oldWebsiteField.setEditable(true);
        oldCountryField.setEditable(true);
        oldCityField.setEditable(true);
        oldAddressField.setEditable(true);
        oldBirthDatepicker.setEditable(true);
        oldNotesArea.setEditable(true);
        oldUploadProfilePictureOption.setDisable(false);
        oldSave.setDisable(false);
        contactDeleteButton.setDisable(false);
        oldEditInfo.setDisable(true);
        oldCancel.setDisable(false);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Path currentRelativePath =  Paths.get("");
        
        File theDir = new File("AddressBook/Data/Image");
        if (!theDir.exists()) {
            System.out.println("creating directory: ");
            boolean result = false;
            try{
            theDir.mkdirs();
                result = true;
           } 
        catch(SecurityException se){

          }        
            if(result) {    
                System.out.println("DIR created");  
        }   
        }
        
        String s = Paths.get("profile.png").toAbsolutePath().normalize().toString();
        System.out.println("Current relative path is: " + s);
        File source = new File(s);


        currentIndex = 0;
        disable();

        oldEditInfo.setDisable(true);
        tabPane.getSelectionModel().select(createNewContactTab);
//        contactInformationAndInformationEditTab.setDisable(false);
//        createNewContactTab.setDisable(true);
        createNewGroupAndEditGroupInformationTab.setDisable(true);
        
        
        GlobalDatas.contactInfo = FXCollections.observableArrayList();
        contactTable.setItems(GlobalDatas.contactInfo);
        nameColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getName()));
        nicknameColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getNickname()));

        Tooltip.install(newProfileImageView, new Tooltip("Profile Picture"));
        Tooltip.install(oldProfileImageView, new Tooltip("Profile Picture"));
        Tooltip.install(newUploadProfilePictureOption, new Tooltip("Click for Upload Profile Picture"));
        Tooltip.install(oldUploadProfilePictureOption, new Tooltip("Click for Upload Profile Picture"));
        Tooltip.install(newSaveOption, new Tooltip("Click for Save Data"));
        Tooltip.install(oldSave, new Tooltip("Click for Save Edited Data"));
        Tooltip.install(oldCancel, new Tooltip("Click for Cancel without Save Edited Data"));
        Tooltip.install(oldEditInfo, new Tooltip("Click for Active Edit Data"));
        Tooltip.install(newResetOption, new Tooltip("Click for Reset Data"));
        Tooltip.install(contactDeleteButton, new Tooltip("Click for Detele contact"));
        GlobalDatas.imagePath = "AddressBook/Data/Image/profile.png";
        
        
        
        RandomAccessFile input;
        String line;
        try{
        String name = "";
        String nickname = "";
        String mobileNumber = "";
        String faxNumber = "";
        String phoneNumber = "";
        String otherNumber = "";
        String email = "";
        String website = "";
        String country = "";
        String city = "";
        String address = "";
        String birthDate = "";
        String notes = "";
        String profilePicture = "";
        
            input = new RandomAccessFile("AddressBook/Data/Data.txt","r");
            while(true){
                line = input.readLine();
                if(line==null)break;
                
        ArrayList<String> temp = new ArrayList<>();
                if(line.equals("<start>")){
                    while(true){
                        line = input.readLine();
                        if(line.equals("</start>"))break;
                        
                        if(line.equals("<name>")){
                            line = input.readLine();
                            name = line;
                            line = input.readLine();
                        }
                        
                        if(line.equals("<nickname>")){
                            line = input.readLine();
                            nickname = line;
                            line = input.readLine();
                        }

                        if(line.equals("<mobileNumber>")){
                            line = input.readLine();
                            mobileNumber = line;
                            line = input.readLine();
                        }
                        
                        if(line.equals("<faxNumber>")){
                            line = input.readLine();
                            faxNumber = line;
                            line = input.readLine();
                        }

                        if(line.equals("<phoneNumber>")){
                            line = input.readLine();
                            phoneNumber = line;
                            line = input.readLine();
                        }

                        if(line.equals("<otherNumber>")){
                            line = input.readLine();
                            otherNumber = line;
                            line = input.readLine();
                        }
                        
                        if(line.equals("<email>")){
                            line = input.readLine();
                            email = line;
                            line = input.readLine();
                        }
                        
                        if(line.equals("<website>")){
                            line = input.readLine();
                            website = line;
                            line = input.readLine();
                        }
                        
                        if(line.equals("<country>")){
                            line = input.readLine();
                            country = line;
                            line = input.readLine();
                        }
                        
                        if(line.equals("<city>")){
                            line = input.readLine();
                            city = line;
                            line = input.readLine();
                        }
                        
                        if(line.equals("<address>")){
                            line = input.readLine();
                            address = line;
                            line = input.readLine();
                        }
                        
                        
                        if(line.equals("<birthDate>")){
                            line = input.readLine();
                            birthDate = line;
                            line = input.readLine();
                        }
                        
                        if(line.equals("<profilePicture>")){
                            line = input.readLine();
                            profilePicture = line;
                            line = input.readLine();
                        }
                        
                        if(line.equals("<notes>")){
                            while(true){
                            line = input.readLine();
                            if(line.equals("</notes>"))break;
                            notes += (line+"\n");
                            }
                        }
                        
                        if(line.equals("<group>")){
                            while(true){
                            line = input.readLine();
                            if(line.equals("</group>"))break;
                            temp.add(line);
                            }
                        }
                        
                        
                    }
                    String note;
                    long  len = notes.length();
                    if(len > 0){
                         note = notes.substring(0, (int) (len-1));
                    }else{
                        note = notes;
                    }

                    ContactInfo contactInfo = new ContactInfo(name, nickname, mobileNumber,
                    faxNumber, phoneNumber, otherNumber, email,
                    website, country, city, address,
                    birthDate, note, profilePicture , temp);
                    GlobalDatas.contactInfo.add(contactInfo);
                    notes = "";
                }
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        


       
    }
    
    
    @FXML
    private void handleSearchOnKeyAction(KeyEvent event) {

        nameColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getName()));
        nicknameColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getNickname()));

        
        FilteredList<ContactInfo> filteredData = new FilteredList<>(GlobalDatas.contactInfo, p -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if(person.getNickname().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                return false; 
            });
        });
 
        SortedList<ContactInfo> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(contactTable.comparatorProperty());
        contactTable.setItems(sortedData);
        
        
                
    }
    
    
    
    
    
    

    @FXML
    private void handleNewUploadProfilePictureAction(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose a picture for set profile picture");
            File source = fileChooser.showOpenDialog(null);
            if(source!=null){
        try{
            
            GlobalDatas.imagePath = source.getName();
            File target = new File("AddressBook/Data/Image/"+GlobalDatas.imagePath);
            GlobalDatas.imagePath = "AddressBook/Data/Image/"+GlobalDatas.imagePath;
            System.out.println(GlobalDatas.imagePath);
            Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            
            Image profileImage = new Image(target.toURI().toString());
            newProfileImageView.setImage(profileImage);
        }catch(FileNotFoundException ex){
            System.out.println("canceled fileChooser");
        }catch(IOException e){
            System.out.println("No :(");
        }
            }
    }

    @FXML
    private void handleNewSaveAction(ActionEvent event) {
        String name = newNameField.getText();
        String nickname = newNicknameField.getText();
        String mobileNumber = newMobileNumberField.getText();
        String faxNumber = newFaxNumberField.getText();
        String phoneNumber = newPhoneNumberField.getText();
        String otherNumber = newOtherNumberField.getText();
        String email = newEmailField.getText();
        String website = newWebsiteField.getText();
        String country = newCountryField.getText();
        String city = newCityField.getText();
        String address = newAddressField.getText();
        LocalDate local = newBirthDatepicker.getValue();
        String notes = newNotesArea.getText();
        
        if(name.equals("")){
             
            Alert alert = new Alert(AlertType.ERROR, "You have need to give Contact Name(must)\nto continue.", ButtonType.OK);
            alert.showAndWait();
            
        }else{
            ArrayList<String> groups= new ArrayList<>();
            groups.add("All");
            
            ContactInfo contactInfo = new ContactInfo(name, nickname, mobileNumber,
            faxNumber, phoneNumber, otherNumber, email,
            website, country, city, address,
            local+"", notes, GlobalDatas.imagePath, groups);
            GlobalDatas.contactInfo.add(contactInfo);
            Collections.sort(GlobalDatas.contactInfo);
            RandomAccessFile output;
            try{
                output = new RandomAccessFile("AddressBook/Data/Data.txt","rw");

                output.setLength(0);
               for(ContactInfo a: GlobalDatas.contactInfo){
                output.writeBytes("<start>\n");
                output.writeBytes("<name>\n");
                output.writeBytes(a.getName()+"\n");
                output.writeBytes("</name>\n");
                output.writeBytes("<nickname>\n");
                output.writeBytes(a.getNickname()+"\n");
                output.writeBytes("</nickname>\n");
                output.writeBytes("<mobileNumber>\n");
                output.writeBytes(a.getMobileNumber()+"\n");
                output.writeBytes("</mobileNumber>\n");
                output.writeBytes("<faxNumber>\n");
                output.writeBytes(a.getFaxNumber()+"\n");
                output.writeBytes("</faxNumber>\n");
                output.writeBytes("<phoneNumber>\n");
                output.writeBytes(a.getPhoneNumber()+"\n");
                output.writeBytes("</phoneNumber>\n");
                output.writeBytes("<otherNumber>\n");
                output.writeBytes(a.getOtherNumber()+"\n");
                output.writeBytes("</otherNumber>\n");
                output.writeBytes("<email>\n");
                output.writeBytes(a.getEmail()+"\n");
                output.writeBytes("</email>\n");
                output.writeBytes("<website>\n");
                output.writeBytes(a.getWebsite()+"\n");
                output.writeBytes("</website>\n");
                output.writeBytes("<country>\n");
                output.writeBytes(a.getCountry()+"\n");
                output.writeBytes("</country>\n");
                output.writeBytes("<city>\n");
                output.writeBytes(a.getCity()+"\n");
                output.writeBytes("</city>\n");
                output.writeBytes("<address>\n");
                output.writeBytes(a.getAddress()+"\n");
                output.writeBytes("</address>\n");
                output.writeBytes("<birthDate>\n");
                output.writeBytes(a.getBirthDate()+"\n");
                output.writeBytes("</birthDate>\n");
                output.writeBytes("<notes>\n");
                output.writeBytes(a.getNotes()+"\n");
                output.writeBytes("</notes>\n");
                output.writeBytes("<profilePicture>\n");
                output.writeBytes(a.getProfilePicture()+"\n");
                output.writeBytes("</profilePicture>\n");
                output.writeBytes("<group>\n");
                for(String b: a.getGroup())
                output.writeBytes(b+"\n");
                output.writeBytes("</group>\n");
                output.writeBytes("</start>\n");
               }
                Alert alert = new Alert(AlertType.CONFIRMATION, "Successfully saved data.", ButtonType.OK);
                alert.showAndWait();
                
                reset();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
    }

    @FXML
    private void handleNewResetAction(ActionEvent event) {
        reset();
    }

    @FXML
    private void handleOldUploadProfilePictureAction(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a picture for set profile picture");
            File source = fileChooser.showOpenDialog(null);
            if(source!=null){
        try{
            
            GlobalDatas.oldimagePath = source.getName();
            File target = new File("AddressBook/Data/Image/"+GlobalDatas.oldimagePath);
            GlobalDatas.oldimagePath = "AddressBook/Data/Image/"+GlobalDatas.oldimagePath;

            
            Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            
            Image profileImage = new Image(target.toURI().toString());
            oldProfileImageView.setImage(profileImage);
        }catch(FileNotFoundException ex){
            System.out.println("canceled fileChooser");
        }catch(IOException e){
            System.out.println("No :(");
        }
            }
        
        

    }
    
    

    @FXML
    private void handleContactListClickedAction(MouseEvent event) {

        GlobalDatas.c = contactTable.getSelectionModel().getSelectedItem();
        
        oldNameField.setText(GlobalDatas.c.getName());
        oldNicknameField.setText(GlobalDatas.c.getNickname());
        oldMobileNumberField.setText(GlobalDatas.c.getMobileNumber());
        oldFaxNumberField.setText(GlobalDatas.c.getFaxNumber());
        oldPhoneNumberField.setText(GlobalDatas.c.getPhoneNumber());
        oldOtherNumberField.setText(GlobalDatas.c.getOtherNumber());
        oldEmailField.setText(GlobalDatas.c.getEmail());
        oldWebsiteField.setText(GlobalDatas.c.getWebsite());
        oldCountryField.setText(GlobalDatas.c.getCountry());
        oldCityField.setText(GlobalDatas.c.getCity());
        oldAddressField.setText(GlobalDatas.c.getAddress());
        String line = GlobalDatas.c.getBirthDate();
        if(line.equals("null")){
        oldBirthDatepicker.setValue(null);
        }else{
            oldBirthDatepicker.setValue(LocalDate.parse(line, DateTimeFormatter.ISO_DATE));
        }
        oldNotesArea.setText(GlobalDatas.c.getNotes());

        GlobalDatas.oldimagePath = GlobalDatas.c.getProfilePicture();
        File file = new File(GlobalDatas.c.getProfilePicture());
        Image profileImage = new Image(file.toURI().toString());
        oldProfileImageView.setImage(profileImage);
        
        
        disable();

        oldEditInfo.setDisable(false);
        tabPane.getSelectionModel().select(contactInformationAndInformationEditTab);
//        contactInformationAndInformationEditTab.setDisable(false);
//        createNewContactTab.setDisable(true);
//        createNewGroupAndEditGroupInformationTab.setDisable(true);
    }

    private void handleCreateNewContactAction(ActionEvent event) {
        tabPane.getSelectionModel().select(createNewContactTab);
        contactInformationAndInformationEditTab.setDisable(true);
        createNewContactTab.setDisable(false);
        createNewGroupAndEditGroupInformationTab.setDisable(true);
    }

    private void handleCreateNewGroupOrEditGroupInfoAction(ActionEvent event) {
        tabPane.getSelectionModel().select(createNewGroupAndEditGroupInformationTab);
        contactInformationAndInformationEditTab.setDisable(true);
        createNewContactTab.setDisable(true);
        createNewGroupAndEditGroupInformationTab.setDisable(false);
    
    
    }

    @FXML
    private void handleOldSaveAction(ActionEvent event) {
        String name = oldNameField.getText();
        String nickname = oldNicknameField.getText();
        String mobileNumber = oldMobileNumberField.getText();
        String faxNumber = oldFaxNumberField.getText();
        String phoneNumber = oldPhoneNumberField.getText();
        String otherNumber = oldOtherNumberField.getText();
        String email = oldEmailField.getText();
        String website = oldWebsiteField.getText();
        String country = oldCountryField.getText();
        String city = oldCityField.getText();
        String address = oldAddressField.getText();
        LocalDate local = oldBirthDatepicker.getValue();
        String notes = oldNotesArea.getText();
        if(name.equals("")){
                
            Alert alert = new Alert(AlertType.ERROR, "You have need to give Contact Name(must)\nto continue.", ButtonType.OK);
            alert.showAndWait();
            
        }else{
            ArrayList<String> groups= new ArrayList<>();
            for(String s : GlobalDatas.c.getGroup()){
                groups.add(s);
            }
     
            
            GlobalDatas.contactInfo.remove(GlobalDatas.c);
            disable();

            
            ContactInfo contactInfo = new ContactInfo(name, nickname, mobileNumber,
            faxNumber, phoneNumber, otherNumber, email,
            website, country, city, address,
            local+"", notes, GlobalDatas.oldimagePath, groups);
            GlobalDatas.contactInfo.add(contactInfo);
            Collections.sort(GlobalDatas.contactInfo);

            RandomAccessFile output;
            try{
                output = new RandomAccessFile("AddressBook/Data/Data.txt","rw");

                output.setLength(0);
               for(ContactInfo a: GlobalDatas.contactInfo){
                output.writeBytes("<start>\n");
                output.writeBytes("<name>\n");
                output.writeBytes(a.getName()+"\n");
                output.writeBytes("</name>\n");
                output.writeBytes("<nickname>\n");
                output.writeBytes(a.getNickname()+"\n");
                output.writeBytes("</nickname>\n");
                output.writeBytes("<mobileNumber>\n");
                output.writeBytes(a.getMobileNumber()+"\n");
                output.writeBytes("</mobileNumber>\n");
                output.writeBytes("<faxNumber>\n");
                output.writeBytes(a.getFaxNumber()+"\n");
                output.writeBytes("</faxNumber>\n");
                output.writeBytes("<phoneNumber>\n");
                output.writeBytes(a.getPhoneNumber()+"\n");
                output.writeBytes("</phoneNumber>\n");
                output.writeBytes("<otherNumber>\n");
                output.writeBytes(a.getOtherNumber()+"\n");
                output.writeBytes("</otherNumber>\n");
                output.writeBytes("<email>\n");
                output.writeBytes(a.getEmail()+"\n");
                output.writeBytes("</email>\n");
                output.writeBytes("<website>\n");
                output.writeBytes(a.getWebsite()+"\n");
                output.writeBytes("</website>\n");
                output.writeBytes("<country>\n");
                output.writeBytes(a.getCountry()+"\n");
                output.writeBytes("</country>\n");
                output.writeBytes("<city>\n");
                output.writeBytes(a.getCity()+"\n");
                output.writeBytes("</city>\n");
                output.writeBytes("<address>\n");
                output.writeBytes(a.getAddress()+"\n");
                output.writeBytes("</address>\n");
                output.writeBytes("<birthDate>\n");
                output.writeBytes(a.getBirthDate()+"\n");
                output.writeBytes("</birthDate>\n");
                output.writeBytes("<notes>\n");
                output.writeBytes(a.getNotes()+"\n");
                output.writeBytes("</notes>\n");
                output.writeBytes("<profilePicture>\n");
                output.writeBytes(a.getProfilePicture()+"\n");
                output.writeBytes("</profilePicture>\n");
                output.writeBytes("<group>\n");
                for(String b: a.getGroup())
                output.writeBytes(b+"\n");
                output.writeBytes("</group>\n");
                output.writeBytes("</start>\n");
               }
                Alert alert = new Alert(AlertType.CONFIRMATION, "Successfully saved data.", ButtonType.OK);
                alert.showAndWait();
                
                reset();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
        
    }

    @FXML
    private void handleOldDeleteAction(ActionEvent event) {
        GlobalDatas.contactInfo.remove(GlobalDatas.c);
        disable();
        oldEditInfo.setDisable(true);
        Default();
        
        RandomAccessFile output;
            try{
                output = new RandomAccessFile("AddressBook/Data/Data.txt","rw");

                output.setLength(0);
               for(ContactInfo a: GlobalDatas.contactInfo){
                output.writeBytes("<start>\n");
                output.writeBytes("<name>\n");
                output.writeBytes(a.getName()+"\n");
                output.writeBytes("</name>\n");
                output.writeBytes("<nickname>\n");
                output.writeBytes(a.getNickname()+"\n");
                output.writeBytes("</nickname>\n");
                output.writeBytes("<mobileNumber>\n");
                output.writeBytes(a.getMobileNumber()+"\n");
                output.writeBytes("</mobileNumber>\n");
                output.writeBytes("<faxNumber>\n");
                output.writeBytes(a.getFaxNumber()+"\n");
                output.writeBytes("</faxNumber>\n");
                output.writeBytes("<phoneNumber>\n");
                output.writeBytes(a.getPhoneNumber()+"\n");
                output.writeBytes("</phoneNumber>\n");
                output.writeBytes("<otherNumber>\n");
                output.writeBytes(a.getOtherNumber()+"\n");
                output.writeBytes("</otherNumber>\n");
                output.writeBytes("<email>\n");
                output.writeBytes(a.getEmail()+"\n");
                output.writeBytes("</email>\n");
                output.writeBytes("<website>\n");
                output.writeBytes(a.getWebsite()+"\n");
                output.writeBytes("</website>\n");
                output.writeBytes("<country>\n");
                output.writeBytes(a.getCountry()+"\n");
                output.writeBytes("</country>\n");
                output.writeBytes("<city>\n");
                output.writeBytes(a.getCity()+"\n");
                output.writeBytes("</city>\n");
                output.writeBytes("<address>\n");
                output.writeBytes(a.getAddress()+"\n");
                output.writeBytes("</address>\n");
                output.writeBytes("<birthDate>\n");
                output.writeBytes(a.getBirthDate()+"\n");
                output.writeBytes("</birthDate>\n");
                output.writeBytes("<notes>\n");
                output.writeBytes(a.getNotes()+"\n");
                output.writeBytes("</notes>\n");
                output.writeBytes("<profilePicture>\n");
                output.writeBytes(a.getProfilePicture()+"\n");
                output.writeBytes("</profilePicture>\n");
                output.writeBytes("<group>\n");
                for(String b: a.getGroup()){
                output.writeBytes(b+"\n");
                }
                output.writeBytes("</group>\n");
                output.writeBytes("</start>\n");
               }
                Alert alert = new Alert(AlertType.CONFIRMATION, "Successfully Deleted Contact.", ButtonType.OK);
                alert.showAndWait();
                
                reset();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void handleOldEditInfoAction(ActionEvent event) {
        enable();
    }

    @FXML
    private void handleContactInfoCancelAction(ActionEvent event) {
        oldNameField.setText(GlobalDatas.c.getName());
        oldNicknameField.setText(GlobalDatas.c.getNickname());
        oldMobileNumberField.setText(GlobalDatas.c.getMobileNumber());
        oldFaxNumberField.setText(GlobalDatas.c.getFaxNumber());
        oldPhoneNumberField.setText(GlobalDatas.c.getPhoneNumber());
        oldOtherNumberField.setText(GlobalDatas.c.getOtherNumber());
        oldEmailField.setText(GlobalDatas.c.getEmail());
        oldWebsiteField.setText(GlobalDatas.c.getWebsite());
        oldCountryField.setText(GlobalDatas.c.getCountry());
        oldCityField.setText(GlobalDatas.c.getCity());
        oldAddressField.setText(GlobalDatas.c.getAddress());
        String line = GlobalDatas.c.getBirthDate();
        if(line.equals("null")){
        oldBirthDatepicker.setValue(null);
        }else{
            oldBirthDatepicker.setValue(LocalDate.parse(line, DateTimeFormatter.ISO_DATE));
        }
        oldNotesArea.setText(GlobalDatas.c.getNotes());
//        GlobalDatas.imagePath = "src/image/profile.png";
        
        File file = new File(GlobalDatas.c.getProfilePicture());
        Image profileImage = new Image(file.toURI().toString());
        oldProfileImageView.setImage(profileImage);
        
        
        

        oldEditInfo.setDisable(false);
        disable();
        tabPane.getSelectionModel().select(contactInformationAndInformationEditTab);
//        contactInformationAndInformationEditTab.setDisable(false);
//        createNewContactTab.setDisable(true);
//        createNewGroupAndEditGroupInformationTab.setDisable(true);
    }

    @FXML
    private void handleGooglePlusOverViewAction(MouseEvent event) {
        try {
            new ProcessBuilder("x-www-browser", "https://plus.google.com/u/0/109281343153051788231").start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleFacebookOverViewAction(MouseEvent event) {
        try {
            new ProcessBuilder("x-www-browser", "https://www.facebook.com/arifin.rian.7/").start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAboutUpdateAction(MouseEvent event) {
    }




    
}
