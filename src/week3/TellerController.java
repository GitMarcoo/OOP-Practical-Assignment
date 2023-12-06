package week3;

import javafx.scene.control.Label;

public class TellerController {

    private TellerView view;
    private Teller teller;


    public TellerController(){
        view = new TellerView();
        teller = new Teller();

        view.getOptellen().setOnAction(actionEvent -> telgetalop());
        view.getVeeloptellen().setOnAction(actionEvent -> veeltellen());
    }

    private void veeltellen(){
        teller.setTelGetal(teller.getTelGetal() + 10);

        view.setLabel(Integer.toString(teller.getTelGetal()));
    }

    private void telgetalop(){
        teller.setTelGetal(teller.getTelGetal() + 1);
        if (teller.getTelGetal() == 69){
            view.setLabel("NICE");
        }else{
        view.setLabel(Integer.toString(teller.getTelGetal()));
    }}

    public  TellerView getView(){
        return  view;
    }
}
