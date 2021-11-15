package bsu.comp152.webdataonwindowmw;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class UniversityController implements Initializable {
    @FXML
    private TextField NameField;
    @FXML
    private TextField UniversityCountryField;
    @FXML
    private TextField websiteDisplayField;
    @FXML
    private ListView<DataHandler.UniversityDataType> ListControl;
    private DataHandler Model;

    public void loadData(){
        var site = "http://universities.hipolabs.com/search?name=";
        var params = getQueryParams();
        var query = site+params;
        Model = new DataHandler(query);
        var univList = Model.getData();
        ObservableList<DataHandler.UniversityDataType> dataToShow =
                FXCollections.observableArrayList(univList);
        ListControl.setItems(dataToShow);
    }

    private String getQueryParams(){
        TextInputDialog inputGrabber = new TextInputDialog("Young");
        inputGrabber.setHeaderText("Gathering Information for query");
        inputGrabber.setContentText("What University Name shall we search for:");
        var name = inputGrabber.showAndWait();
        if (name.isPresent()){
            return name.get();
        }
        else {
            return "";
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        ListControl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DataHandler.UniversityDataType>() {
            @Override
            public void changed(ObservableValue<? extends DataHandler.UniversityDataType> observable, DataHandler.UniversityDataType oldValue, DataHandler.UniversityDataType newValue) {

            }
        });
    }
}