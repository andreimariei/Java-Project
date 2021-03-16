package repo.file;

import domain.Flight;
import domain.validators.Validator;

import java.time.LocalDateTime;
import java.util.List;

public class FlightRepo extends AbstractFileRepository<Long, Flight> {
    public FlightRepo(String fileName, Validator<Flight> validator) {
        super(fileName, validator);
        loadData();
    }

    @Override
    public Flight extractEntity(List<String> attributes) {
        Long id1=Long.parseLong(attributes.get(0));
        Flight zbor=new Flight(attributes.get(1),attributes.get(2), LocalDateTime.parse(attributes.get(3)),
                LocalDateTime.parse(attributes.get(4)),Integer.parseInt(attributes.get(5)));
        zbor.setId(id1);
        return zbor;
    }

    @Override
    protected String createEntityAsString(Flight entity) {
        return entity.getId()+","+entity.getFrom()+","+entity.getTo()+","+entity.getDepartureTime()+","+entity.getLandingTime()+","+entity.getSeats();
    }
}
