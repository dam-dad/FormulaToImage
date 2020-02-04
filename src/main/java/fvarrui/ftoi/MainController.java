package fvarrui.ftoi;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import net.sourceforge.jeuclid.LayoutContext;
import net.sourceforge.jeuclid.context.LayoutContextImpl;
import net.sourceforge.jeuclid.context.StyleAttributeLayoutContext;
import net.sourceforge.jeuclid.converter.Converter;
import uk.ac.ed.ph.snuggletex.SnuggleEngine;
import uk.ac.ed.ph.snuggletex.SnuggleInput;
import uk.ac.ed.ph.snuggletex.SnuggleSession;

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
        	SnuggleEngine engine = new SnuggleEngine();
        	SnuggleSession session = engine.createSession();

        	SnuggleInput input = new SnuggleInput("$$" + formula.get() + "$$");
			session.parseInput(input);
	    	
			LayoutContext layout = new StyleAttributeLayoutContext(LayoutContextImpl.getDefaultLayoutContext(), "30pt", Color.BLACK);
			
	    	BufferedImage renderedImage = Converter.getInstance().render(session.buildDOMSubtree().item(0), layout);
	    	
	    	Image image = SwingFXUtils.toFXImage(renderedImage, null);
	    	
	    	this.image.set(image);
	    	
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    }

}
