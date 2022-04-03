package notepad;

import javafx.scene.Node;
import javafx.scene.effect.BoxBlur;

/**
 *
 * @author G
 */
public class Effects {
    private static final BoxBlur boxBlur;

    static {
        boxBlur = new BoxBlur();
        // Min: 0.0 Max: 255.0
        boxBlur.setWidth(10);//10
        // Min: 0.0 Max: 255.0
        boxBlur.setHeight(5);//5
        // Min: 0 Max: 3
        boxBlur.setIterations(3);
    }

    public static void blur(Node s, boolean on) {
        if (on) {
            s.setEffect(boxBlur);
        }
        else {
            s.setEffect(null);
        }
    }
}