package repo.file;

import domain.Entity;
import domain.Ticket;
import domain.validators.Validator;

import java.time.LocalDateTime;
import java.util.List;

public class TicketRepo extends AbstractFileRepository<String,Ticket> {

    /**
     * constructor
     *
     * @param fileName  the file name
     * @param validator the validator
     */
    public TicketRepo(String fileName, Validator<Ticket> validator) {
        super(fileName, validator);
        loadData();
    }

    @Override
    public Ticket extractEntity(List<String> attributes) {
        String id=attributes.get(0);
        Ticket ticket=new Ticket(Long.parseLong(attributes.get(1)), LocalDateTime.parse(attributes.get(2)));
        ticket.setId(id);

        return ticket;
    }

    @Override
    protected String createEntityAsString(Ticket entity) {
        return entity.getId()+","+entity.getFlightId()+","+LocalDateTime.now();
    }
}
