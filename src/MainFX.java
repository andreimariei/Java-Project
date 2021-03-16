import domain.Client;
import domain.Flight;
import domain.Ticket;
import domain.validators.ClientValidator;
import domain.validators.FlightValidator;
import domain.validators.TicketValidator;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import repo.Repository;
import repo.file.ClientRepo;
import repo.file.FlightRepo;
import repo.file.TicketRepo;
import service.Service;

public class MainFX extends Application {

    Service serv;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Repository<String, Client> repoClient=new ClientRepo("src/clienti.txt",new ClientValidator());
        Repository<Long, Flight> repoFlight=new FlightRepo("src/zboruri.txt",new FlightValidator());
        Repository<String, Ticket> repoTicket=new TicketRepo("src/tickets.txt",new TicketValidator());
        serv=new Service(repoClient,repoFlight,repoTicket);

        initView(stage);
        stage.show();
    }

    private void initView(Stage primaryStage)throws Exception{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("Prima.fxml"));
        AnchorPane layout=loader.load();
        primaryStage.setScene(new Scene(layout));

        ClientController clientController=loader.getController();
        clientController.setService(serv);
    }
}
