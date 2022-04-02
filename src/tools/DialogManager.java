package tools;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.List;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author tortl
 */
public class DialogManager {

    public static final FileChooser fc;
    public static final DirectoryChooser dc;

    static {
        fc = new FileChooser();
        fc.setTitle("Open File");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text", "*.txt", "*.json", "*.html"),
                new FileChooser.ExtensionFilter("All", "*.*")
        );
        dc = new DirectoryChooser();
        dc.setInitialDirectory(new File("C:/"));
    }

    public static File openFile(Stage stage) {
        File file = fc.showOpenDialog(stage);
        return file;
    }

    public static File createFile(Stage stage){
        File file = fc.showSaveDialog(stage);
        return file;
    }

}
