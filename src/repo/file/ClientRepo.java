package repo.file;

import domain.Client;
import domain.validators.Validator;

import java.util.List;

public class ClientRepo extends AbstractFileRepository<String, Client> {
    public ClientRepo(String fileName, Validator<Client> validator) {
        super(fileName, validator);
        loadData();
    }

    @Override
    public Client extractEntity(List<String> attributes) {
        String username=attributes.get(0);
        Client client=new Client(attributes.get(1));
        client.setId(username);
        return client;
    }

    @Override
    protected String createEntityAsString(Client entity) {
        return entity.getId()+","+entity.getName();
    }
}
