/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.io.File;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import tools.FileManager;

/**
 *
 * @author tortl
 */
public class TextTab extends Tab {

    private final TextArea area;
    private File file;

    {
        area = new TextArea();
        setContent(area);
        setOnCloseRequest(ev -> {
            ev.consume();
            
        });
    }

    public TextTab() {
        setText("New Tab");
    }

    public TextTab(File file) {
        this.file = file;
        setText(file.getName());
        readFile();
    }

    public final void readFile() {
        area.setText(FileManager.read(file, FileManager.CP1251));
    }

    public final void saveFile() {
        FileManager.write(area.getText(), file, FileManager.CP1251);
    }

    public TextArea getArea() {
        return area;
    }
    
}
