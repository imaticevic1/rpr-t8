package ba.unsa.etf.rpr.tutorijal8;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;

public class Controller {

    public TextField poljeTeksta;
    public ListView lista;
    public Button traziDugme;

    private static void pretrazi(File file){
        if (!file.exists())
            System.out.println("Datoteka ne postoji.");
        else if (file.isFile()) {
            System.out.println(file.getAbsolutePath());
        }
        else if (file.isDirectory())
            for(File ime : file.listFiles())
                pretrazi(ime);
    }
    public void trazi(ActionEvent actionEvent) {
        File file = new File(poljeTeksta.getText());
    }
}
