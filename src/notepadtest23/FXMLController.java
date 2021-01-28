/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepadtest23;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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
//        if (event.isMetaDown()) {
//            System.out.println("meta is down");
//            if (event.getCode() == KeyCode.LEFT) {
//                System.out.println("meta + left");
//            }
//        }
//        if (event.getCode() == KeyCode.LEFT && event.isMetaDown()) {
//            System.out.println("meta + left");
//        }
//        ////////////////////////////////////////////////
//        if (event.isControlDown()) {
//            System.out.println("meta is down");
//            if (event.getCode() == KeyCode.LEFT) {
//                System.out.println("meta + left");
//            }
//        }
//        if (event.getCode() == KeyCode.LEFT && event.isControlDown()) {
//            System.out.println("control + left");
//        }

        if (event.getCode() == KeyCode.LEFT && event.isControlDown()) {
            System.out.println("left side");
            mn.putToLeft();
        } else if (event.getCode() == KeyCode.RIGHT && event.isControlDown()) {
            System.out.println("right side");
            mn.putToRight();

        }
        /*       else if (event.getCode() == KeyCode.UP && event.isControlDown()) {
            System.out.println("up side");
            maximized();
        }   */

    }

    @FXML
    private void fileMenuHnd(Event ev
    ) {
        switch (((MenuItem) ev.getSource()).getId()) {
            case "mCloseAll":
                break;
            case "mClose":
                break;
            case "mOpenDir":
                File[] files1 = DialogManager.openDir(stage);
                if (files1 != null) {
                    for (File file : files1) {
                        TextTab tab = new TextTab(file);
                        tabpane.getTabs().add(tab);
                        tabpane.getSelectionModel().select(tab);
                    }
                }
                break;

            case "mOpenAll":
                List<File> files = DialogManager.openAllFile(stage);
                if (files != null) {
                    files.forEach(file -> {
                        TextTab tab = new TextTab(file);
                        tabpane.getTabs().add(tab);
                        tabpane.getSelectionModel().select(tab);

                    });
                }
                break;
            case "mOpen":
                File file = DialogManager.openFile(stage);
                if (file != null) {
                    TextTab tab = new TextTab(file);

                    TextTab buffer = (TextTab) tabpane.getSelectionModel().getSelectedItem();
                    if (buffer.getText().equals("New Tab")) {
                        if (buffer.getArea().getText().equals("")) {
                            // если сейчас активна пустая новая вкладка, то заменить ее открытым файлом
//                            tabpane.getSelectionModel().getSelectedItem() = buffer;
//                            tabpane.getChildrenUnmodifiable().get(buffer.hashCode()).setUserData(new TextTab(file));
                        }
                    }
                    tabpane.getTabs().add(tab);
                }
                break;
            case "mNew":
                break;
            case "mSaveAs":
                break;
            case "mSave":
                break;
        }
    }
}
