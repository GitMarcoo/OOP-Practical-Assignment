package week3;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TellerView {

    private Parent root;

    public TellerView(){
        initializeView();
    }


    private Label getalLabel;
    private Button optellen;

    private Button veeloptellen;
    private void initializeView(){
        VBox vBox = new VBox();
        getalLabel = new Label("getal is 0");
        veeloptellen = new Button("+10");
        optellen = new Button("+1");


        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(getalLabel,optellen,veeloptellen);

        root = vBox;

    }

    public Button getOptellen(){
        return optellen;
    }

    public Label getLabel(){
        return getalLabel;
    }

    public void setLabel(String s){
        getalLabel.setText(s);
    }

    public Button getVeeloptellen(){
        return veeloptellen;
    }

    public Parent getRoot(){
        return root;
    }
}
