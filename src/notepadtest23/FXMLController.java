package notepadtest23;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import structures.TextTab;
import tools.DialogManager;
import static tools.DialogManager.fc;

/**
 * FXML Controller class
 *
 * @author tortl
 */
public class FXMLController implements Initializable {

    private Stage stage;
    private AppManager mn;

    public void setStage(Stage stage) {
        this.stage = stage;
        this.mn = new AppManager(stage);

    }

    public AppManager mn() {
        return mn;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextTab tab = new TextTab();
        tabpane.getTabs().add(tab);
        tabpane.getTabs().forEach(t -> {
            tabpane.getSelectionModel().select(tab);
        });
    }

    @FXML
    private TabPane tabpane;

    @FXML
    private void close() {
        stage.close();
    }

    @FXML
    private void maximized() {
        //stage.setMaximized(!stage.isMaximized());
        mn.maximized();
    }

    @FXML
    private void minimized() {
        stage.setIconified(true);
    }

    @FXML
    private void pressed(MouseEvent ev) {
        mn.pressed(ev.getScreenX(), ev.getScreenY());
//        if (ev.getClickCount()==2) {
//            System.out.println("");
//        }
    }

    @FXML
    private void draged(MouseEvent ev) {
        mn.move(ev.getScreenX(), ev.getScreenY());

    }

    @FXML
    public void doubleClick(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                maximized();
            }
        }
    }

    @FXML
    public void btnHandler(KeyEvent event) {

        if (event.getCode() == KeyCode.LEFT && event.isControlDown()) {
            System.out.println("left side");
            mn.putToLeft();
        } else if (event.getCode() == KeyCode.RIGHT && event.isControlDown()) {
            System.out.println("right side");
            mn.putToRight();
//            mn.setOldVals(); // вернуть прошлий размер
        } else if (event.getCode() == KeyCode.S && event.isControlDown()) {
            ((TextTab) tabpane.getSelectionModel().getSelectedItem()).saveFile();
        } else if (event.getCode() == KeyCode.N && event.isControlDown()) {
            tabpane.getTabs().add(new TextTab());
            tabpane.getSelectionModel().select(tabpane.getTabs().toArray().length-1);
        }

}

    @FXML
    private void fileMenuHnd(Event ev
    ) {
        switch (((MenuItem) ev.getSource()).getId()) {
            case "mCloseAll":
                break;
            case "mClose":
                break;
            case "mOpen":
                File file = DialogManager.openFile(stage);
                if (file != null) {
                    // check if current tab is empty
                    //if not, create new tab

                    TextTab buffer = (TextTab) tabpane.getSelectionModel().getSelectedItem();
                    if (buffer.getText().equals("New Tab")) {
                        // если сейчас активна пустая новая вкладка, то заменить ее открытым файлом
                        if (buffer.getArea().getText().equals("")) {
                            ((TextTab) tabpane.getSelectionModel().getSelectedItem()).replace(file);
                            break;
                        }
                    }
                    TextTab tab = new TextTab(file);
                    tabpane.getTabs().add(tab);
                    tabpane.getSelectionModel().select(tabpane.getTabs().toArray().length-1);
                }
                break;
            case "mNew":
                 tabpane.getTabs().add(new TextTab());
                break;
            case "mSaveAs":
                break;
            case "mSave":
                ((TextTab) tabpane.getSelectionModel().getSelectedItem()).saveFile();
                break;
        }
    }

}