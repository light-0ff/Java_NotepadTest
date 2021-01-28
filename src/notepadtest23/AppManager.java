/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepadtest23;

import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author tortl
 */
public class AppManager {

    //Stage
    private final Stage stage;
    private double sx;
    private double sy;
    private double sw;
    private double sh;
    private boolean max;
    //Mous    
    private double mx;
    private double my;
    //Screan
    private double scW;
    private double scH;
    //Screan Insets
    private Insets i;
    //

    {
        dimentions();
    }

    public AppManager(Stage stage) {
        this.stage = stage;
    }

    public Insets getIns() {
        return i;
    }

    public Pane root() {
        return (Pane) stage.getScene().getRoot();
    }

    public void pressed(double x, double y) {
        mx = x;
        my = y;
    }

    public void move(double x, double y) {
        if (y >= i.top) {
            stage.setX(stage.getX() + (x - mx));
            stage.setY(stage.getY() + (y - my));
            mx = x;
            my = y;
            if (x == 0) {
                putToLeft();
            } else if (x == (scW - 1)) {
                putToRight();
            }

            if (max) {
                maximized();
                stage.setY(i.top);
                stage.setX(x - 200);
            }
        } else if (!max) {
//        if (y == i.top) {
            maximized();
        }
    }

    public void dimentions() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rc = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        i = Toolkit.getDefaultToolkit().getScreenInsets(
                ge.getDefaultScreenDevice().getDefaultConfiguration());
        //
        scW = rc.width;
        scH = rc.height;
    }

    public void setOldVals() {
        stage.setX(sx);
        stage.setY(sy);
        stage.setWidth(sw);
        stage.setHeight(sh);
    }

    public void getCurrentVals() {
        sx = stage.getX();
        sy = stage.getY();
        sw = stage.getWidth();
        sh = stage.getHeight();
    }

    public void maximized() {
        if (!max) {
            getCurrentVals();
            stage.setX(i.left);
            stage.setY(i.top);
            stage.setWidth(scW - i.left - i.right);
            stage.setHeight(scH - i.top - i.bottom);
        } else {
            setOldVals();
        }
        max = !max;
    }

    public void putToLeft() {
//        getCurrentVals(); //нужно ли
        stage.setX(i.left);
        stage.setY(i.top);
        stage.setWidth((scW - i.left - i.right) / 2);
        stage.setHeight(scH - i.top - i.bottom);

    }

    public void putToRight() {
//        getCurrentVals(); //нужно ли
        stage.setX((scW - i.left - i.right) / 2);
        stage.setY(i.top);
        stage.setWidth((scW - i.left - i.right) / 2);
        stage.setHeight(scH - i.top - i.bottom);

    }
}
