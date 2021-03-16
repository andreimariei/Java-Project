package service;

import domain.Client;
import domain.Flight;
import domain.Ticket;
import repo.Repository;

public class Service  {
    Repository<String, Client> repoClient;
    Repository<Long, Flight> repoFlight;
    Repository<String,Ticket> repoTicket;

    public Service(Repository<String, Client> repoClient, Repository<Long, Flight> repoFlight, Repository<String, Ticket> repoTicket) {
        this.repoClient = repoClient;
        this.repoFlight = repoFlight;
        this.repoTicket=repoTicket;
    }

    public Iterable<Client> getAllClienti(){
        return repoClient.findAll();
    }

    public Iterable<Flight> getAllFlights()
    {
        return repoFlight.findAll();
    }

    public Iterable<Ticket> getAllTickets(){
        return repoTicket.findAll();
    }

    public Ticket addTicket(Ticket ticket)
    {
        Ticket rez=repoTicket.save(ticket);
        return rez;
    }


}
