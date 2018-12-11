package ba.unsa.etf.rpr.tutorijal8;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Controller implements Runnable{
    public Controller(){}
    public TextField poljeTeksta;
    public ListView lista;
    public Button traziDugme;
    private Set<String> stringSet = new HashSet<String>();
    private ObservableList<String> itemList = FXCollections.observableArrayList();
    private void pretrazi(File file){
        if(file.getName().contains(poljeTeksta.getText()))
            stringSet.add(file.getAbsolutePath());
        if (!file.exists())
            System.out.println("Datoteka ne postoji.");
        else if (file.isDirectory())
            for(File ime : file.listFiles())
                pretrazi(ime);
    }
    public void trazi(ActionEvent actionEvent) {
        itemList.clear();
        File file = new File("C:\\Users\\Goran\\IdeaProjects");
        Controller daljinski = new Controller();
        Thread nit = new Thread(daljinski);
        daljinski.poljeTeksta = poljeTeksta;
        daljinski.pretrazi(file);
        nit.start();
        itemList.addAll(daljinski.stringSet);
        lista.setItems(itemList);
    }

    @Override
    public void run() {

    }
}
