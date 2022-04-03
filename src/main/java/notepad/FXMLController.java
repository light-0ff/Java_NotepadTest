package notepad;

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

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextTab tab = new TextTab();
        tabpane.getSelectionModel().selectedItemProperty().addListener((observableValue, tabOld, tabNew) -> {
            if (tabOld != null) tabOld.getStyleClass().remove("selected");
            if (tabNew != null) tabNew.getStyleClass().add("selected");
        });
        tabpane.getTabs().add(tab);
        tabpane.getTabs().forEach(t -> tabpane.getSelectionModel().select(tab));

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
    }

    @FXML
    private void dragged(MouseEvent ev) {
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
        }
        /////////////////////////////////////////////////////////////////////////////
        if (event.isControlDown()) {
            switch (event.getCode()) {
                case N -> newTab();
                case S -> save();
                case W -> tabpane.getTabs().remove(tabpane.getSelectionModel().getSelectedItem());
                case O -> openFile();
            }

        }

    }

    @FXML
    private void fileMenuHnd(Event ev
    ) {
        File file;
        switch (((MenuItem) ev.getSource()).getId()) {
            case "mCloseAll":
                break;
            case "mClose":
                tabpane.getTabs().remove(tabpane.getSelectionModel().getSelectedItem());
                break;
            case "mOpen":
                openFile();
                break;
            case "mNew":
                newTab();
                break;
            case "mSaveAs":
                file = DialogManager.createFile(stage);
                ((TextTab) tabpane.getSelectionModel().getSelectedItem()).replace(file, false);
                //break;
                //don't stop. just save
            case "mSave":
                save();
                break;
        }
    }

    public void newTab() {
        TextTab tab = new TextTab();
        tabpane.getTabs().add(tab);
        tabpane.getSelectionModel().select(tabpane.getTabs().toArray().length - 1);
    }

    public void openFile() {
        File file = DialogManager.openFile(stage);
        if (file != null) {
            // check if current tab is empty
            //if not, create new tab
            TextTab buffer = (TextTab) tabpane.getSelectionModel().getSelectedItem();
            if (buffer.getText().equals("New Tab")) {
                if (buffer.getArea().getText().equals("")) {
                    ((TextTab) tabpane.getSelectionModel().getSelectedItem()).replace(file, true);
                    return;
                }
            }
            TextTab tab = new TextTab(file);
            tabpane.getTabs().add(tab);
            tabpane.getSelectionModel().select(tabpane.getTabs().toArray().length - 1);
        }
    }

    public void save() {
        if (tabpane.getSelectionModel().getSelectedItem().getText().equals("New Tab")) {
            File file = DialogManager.createFile(stage);
            ((TextTab) tabpane.getSelectionModel().getSelectedItem()).replace(file, false);
        }
        ((TextTab) tabpane.getSelectionModel().getSelectedItem()).saveFile();
    }

}