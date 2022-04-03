package structures;

import java.io.File;

import javafx.event.Event;
import javafx.event.EventHandler;
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
//        setOnCloseRequest(ev -> {
//            ev.consume();
//        });
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
        area.setText(FileManager.read(file, FileManager.UTF_8));
    }

    public final void saveFile() {
        FileManager.write(area.getText(), file, FileManager.UTF_8);
    }

    public TextArea getArea() {
        return area;
    }

    public void replace(File file, boolean read){
        this.file = file;
        setText(file.getName());
        if(read)readFile();
    }

}
