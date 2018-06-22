package lapr.project.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lapr.project.model.Expo;
import lapr.project.utils.Logger;
import lapr.project.utils.XMLSerializer;

public class Main extends Application {

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

    @Override
    public void start(Stage stage) throws Exception {

        // Load application state data
        loadState();

        // Main pane for the JavaFX pages
        BorderPane mainPane = new BorderPane();

        // Pass the main pane down to the PageChanger, so it can change pages when requested
        new FXPageChanger(mainPane);

        // Add menu to the top of the main pane
        Parent menu = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        mainPane.setTop(menu);

        // Create the scene with our main pane and set it's width and height
        Scene scene = new Scene(mainPane, 1000, 800);

        // Set the CSS stylesheet to our scene
        scene.getStylesheets().add("/styles/Styles.css");

        // Configure the stage and show it
        stage.setTitle("LAPR2 Project");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();

        // Set the home page of the app
        FXPageChanger.getStaticInstance().showPage("Dashboard.fxml");

    }

    public static void exit() {
        Platform.exit();
        System.exit(0);
    }

    public static void saveState() {
        try {
            XMLSerializer.serializeToXML(Expo.getInstance(), Expo.class, "./state.xml");
        } catch (Exception e) {
            Logger.log(e.getMessage());
        }
    }

    public static void loadState() {
        Expo expo = Expo.getInstance();
        try {
            Expo importedExpo = (Expo) XMLSerializer.deserializeFromXML(Expo.class, "./state.xml");
            expo.setUserRegistry(importedExpo.getUserRegistry());
            expo.setEventRegistry(importedExpo.getEventRegistry());
        } catch (Exception e) {
            Logger.log(e.getMessage());
        }

    }

}