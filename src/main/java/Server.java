import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Server {

    public Purchase processRequest(String jsonString) throws IOException {
        return parseJson2(jsonString);
    }

    private Purchase parseJson2(String value) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(value, Purchase.class);
    }

}