package ba.unsa.etf.rpr.tutorijal8;

import javafx.application.Platform;
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

    public Button prekiniDugme;
    public TextField poljeTeksta;
    public ListView lista=new ListView();
    public Button traziDugme;
    public boolean dugmeKliknuto = false;
    public boolean isPrekini = false;
    private Set<String> stringSet = new HashSet<String>();
    private ObservableList<String> itemList = FXCollections.observableArrayList();

    private void pretrazi(File file){
        if(file.getName().contains(poljeTeksta.getText())) {
            String absolutePath = file.getAbsolutePath();
            //stringSet.add(absolutePath);
            Platform.runLater(() -> lista.getItems().add(absolutePath));
            //itemList.add(absolutePath);
        }
        if (!file.exists())
            System.out.println("");
        else if (file.isDirectory())
            for(File ime : file.listFiles())
                pretrazi(ime);
    }
    public void traziButton(ActionEvent actionEvent) {
        dugmeKliknuto = true;
        File file = new File(System.getProperty("user.home"));
        itemList.clear();
        Controller daljinski = new Controller();
        daljinski.poljeTeksta = poljeTeksta;
        daljinski.pretrazi(file);
        Thread nit = new Thread(new Runnable() {
            @Override
            public void run() {
                prekiniDugme.setDisable(false);
                traziDugme.setDisable(true);
                pretrazi(file);
                traziDugme.setDisable(false);
                if(isPrekini){
                    prekiniDugme.setDisable(true);
                    return;
                }
                prekiniDugme.setDisable(true);
            }
        });
        nit.start();
        //itemList.addAll(daljinski.stringSet);
        //lista.setItems(itemList);
    }


    public void run() {
        if(dugmeKliknuto)
            traziDugme.setDisable(true);
    }

    public void prekiniDugme(ActionEvent actionEvent) {
        isPrekini = true;
    }
}
