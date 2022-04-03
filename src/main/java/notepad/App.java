package notepad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author tortl
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {
            //loader
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXML.fxml"));
            //root
            Pane root = loader.load();
            //controller
            FXMLController controller = loader.getController();
            controller.setStage(stage);

            //scene
            Scene scene = new Scene(root);
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
            scene.setFill(Color.TRANSPARENT);
            //
            scene.setOnKeyPressed(ev -> {
                ev.consume();
                if (ev.isControlDown()) {
                    switch (ev.getCode()) {
                        case M:
                            controller.mn().maximized();
                            break;
                    }
                }
            });
            //stage
            stage.setScene(scene);
            stage.setTitle("Title");
            stage.initStyle(StageStyle.TRANSPARENT);
            //
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            System.exit(1);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
