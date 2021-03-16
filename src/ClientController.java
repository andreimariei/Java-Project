import domain.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class ClientController {

    Service serv;

    @FXML
    TextField usernameUser;

    public void loginUser(ActionEvent actionEvent) throws IOException {
        String username=usernameUser.getText();
        Client client1=null;
        List<Client> lista1= StreamSupport.stream(serv.getAllClienti().spliterator(),false).collect(Collectors.toList());
        for(Client client : lista1)
        {
            if(client.getId().equals(username))
                client1=client;
        }
        if(client1!=null)
        {
            FXMLLoader load=new FXMLLoader();
            load.setLocation(getClass().getResource("Zboruri.fxml"));
            AnchorPane layout= load.load();

            Stage primaryStage=new Stage();
            primaryStage.setScene(new Scene(layout));

            FlightController flightController=load.getController();
            flightController.setService(serv,client1);
            primaryStage.show();

        }

    }


    public void setService(Service serv) {
        this.serv=serv;
    }
}
