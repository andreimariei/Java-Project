import domain.Client;
import domain.Flight;
import domain.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FlightController {

    Service serv;
    Client client;


    @FXML
    TextField userLogged;
    @FXML
    DatePicker dataPlecare;

    @FXML
    ComboBox<String> Plecare;

    @FXML
    ComboBox<String> Sosire;

    @FXML
    TableView<Flight> tabelZboruri;

    @FXML
    TableColumn<String,Flight> tabelPlecare;

    @FXML
    TableColumn<String,Flight> tabelSosire;

    @FXML
    TableColumn<String,Flight> tabelDPlecare;

    @FXML
    TableColumn<String,Flight> tabelDSosire;

    @FXML
    TableColumn<String,Flight> tabelNrLoc;





    public void setService(Service serv, Client client1) {
        this.serv=serv;
        this.client=client1;

        initTable();
        initCombo();
    }

    private void initTable() {
        ObservableList<Flight> lista= FXCollections.observableArrayList();
        List<Flight> lista1= StreamSupport.stream(serv.getAllFlights().spliterator(),false).collect(Collectors.toList());
        LocalDate data1=dataPlecare.getValue();
        if(data1!=null)
        {List<Flight> lista2 = new ArrayList<>();
        for (Flight x : lista1) {
            if (x.getDepartureTime().getDayOfYear() == data1.getDayOfYear()) {
                lista2.add(x);
            }
        }

        lista.setAll(lista2);
        }
        else {
            lista.setAll(lista1);
        }
        tabelPlecare.setCellValueFactory(new PropertyValueFactory<>("From"));
        tabelSosire.setCellValueFactory(new PropertyValueFactory<>("To"));
        tabelDPlecare.setCellValueFactory(new PropertyValueFactory<>("DepartureTime"));
        tabelDSosire.setCellValueFactory(new PropertyValueFactory<>("LandingTime"));
        tabelNrLoc.setCellValueFactory(new PropertyValueFactory<>("Seats"));
        tabelZboruri.setItems(lista);

        userLogged.setText(client.getName());
        userLogged.setEditable(false);
    }


    private void initCombo() {
        ObservableList<String> lista= FXCollections.observableArrayList();
        List<Flight> lista1= StreamSupport.stream(serv.getAllFlights().spliterator(),false).collect(Collectors.toList());
        List<String> lista2 = new ArrayList<>();
        for(Flight flight:lista1)
        {
            lista2.add(flight.getFrom());
        }
        lista.setAll(lista2);
        Plecare.setItems(lista);

        ObservableList<String> lista3= FXCollections.observableArrayList();
        List<Flight> lista4= StreamSupport.stream(serv.getAllFlights().spliterator(),false).collect(Collectors.toList());
        List<String> lista5 = new ArrayList<>();
        for(Flight flight:lista4)
        {
            lista5.add(flight.getTo());
        }
        lista3.setAll(lista5);
        Sosire.setItems(lista3);
    }



    public void Refresh(ActionEvent actionEvent) {
        String plecare=Plecare.getValue();
        String sosire=Sosire.getValue();
        LocalDate data=dataPlecare.getValue();
        List<Flight> lista1=StreamSupport.stream(serv.getAllFlights().spliterator(),false).collect(Collectors.toList());
        List<Flight> lista2=lista1.stream().filter(x->x.getFrom().equals(plecare)&&x.getTo().equals(sosire)&&x.getDepartureTime().getDayOfYear()==data.getDayOfYear()).collect(Collectors.toList());
        ObservableList<Flight> lista=FXCollections.observableArrayList();
        lista.setAll(lista2);
        tabelPlecare.setCellValueFactory(new PropertyValueFactory<>("From"));
        tabelSosire.setCellValueFactory(new PropertyValueFactory<>("To"));
        tabelDPlecare.setCellValueFactory(new PropertyValueFactory<>("DepartureTime"));
        tabelDSosire.setCellValueFactory(new PropertyValueFactory<>("LandingTime"));
        tabelNrLoc.setCellValueFactory(new PropertyValueFactory<>("Seats"));
        tabelZboruri.setItems(lista);
    }

    public void selectSosire(ActionEvent actionEvent) {
    }

    public void selectPlecare(ActionEvent actionEvent) {
    }

    public void buyTicket(ActionEvent actionEvent) {
        Flight flight=tabelZboruri.getSelectionModel().getSelectedItem();
        Ticket ticket=new Ticket(flight.getId(),LocalDateTime.now());
        ticket.setId(userLogged.getId());
        serv.addTicket(ticket);
    }
}
