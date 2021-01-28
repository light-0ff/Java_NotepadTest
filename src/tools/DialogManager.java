/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public static List<File> openAllFile(Stage stage) {
        List<File> file = fc.showOpenMultipleDialog(stage);
        return file;
    }

    public static File[] openDir(Stage stage) {
        File dir = dc.showDialog(stage);
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        return files;
    }
}
