package lapr.project.ui;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import lapr.project.utils.Logger;

import java.io.IOException;

/**
 * This class loads a FXML page and injects it inside the main pane of the app.
 * <p>
 * Created by Diogo Capela (1171316@isep.ipp.pt) on 31/05/2018.
 */
public class FXPageChanger {

    private BorderPane mainPane;
    private static FXPageChanger staticInstance;

    FXPageChanger(BorderPane mainPane) {
        this.mainPane = mainPane;
        staticInstance = this;
    }

    public static FXPageChanger getStaticInstance() {
        return staticInstance;
    }

    public void showPage(String pageName) {
        Pane loadedPage = null;
        try {
            // load the page
            loadedPage = FXMLLoader.load(getClass().getResource("/fxml/" + pageName));
            // set padding around the loaded page
            loadedPage.setPadding(new Insets(10, 10, 10, 10));
        } catch (IOException e) {
            Logger.log(e.getLocalizedMessage());
        }
        // EASTER EGG: the biggest scandal in java programming in the history of ISEP
        if("UC05SubmitApplicationToEvent.fxml".equals(pageName)) {
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(loadedPage);
            mainPane.setCenter(scrollPane);
        } else {
            // inject the loaded page into the main pane
            mainPane.setCenter(loadedPage);
        }
    }

}