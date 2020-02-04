package fvarrui.ftoi;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {
	
	// model
	
	private StringProperty formula = new SimpleStringProperty();
	private ObjectProperty<Image> image = new SimpleObjectProperty<>();
	
	// view

    @FXML
    private VBox view;

    @FXML
    private TextArea formulaText;

    @FXML
    private Button convertButton;

    @FXML
    private ImageView formulaImage;

	
	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		formula.bind(formulaText.textProperty());
		formulaImage.imageProperty().bind(image);
		formulaText.setText("\\vec{F}=\\frac{A}{B}");
	}
	
	public VBox getView() {
		return view;
	}

    @FXML
    void onConvertAction(ActionEvent e) {
    	try {
	    	this.image.set(FormulaUtils.formulaToImage(formula.get(), 30, Color.black));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

}
