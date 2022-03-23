package it.polito.tdp.librettovoti;

import it.polito.tdp.model.Libretto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent root = loader.load();
        //Non chiamo un metodo statico ma chiamo un 
        //nuovo oggetto su cui chiamo il metodo load
        //tutto questo per non chiamare un metodo statico
        //e soprattutto avere un oggetto loader che ha
        //un metodo getController che ha a disposizione
        //un metodo setModel
        
        FXMLController controller = loader.getController();
        
        
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        Libretto model = new Libretto();
        controller.setModel(model);
        
        stage.setTitle("Applicazione Libretto Voti");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
