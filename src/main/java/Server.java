import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Server {

    public static Purchase processRequest(String jsonString) throws IOException {
        return Server.parseJson2(jsonString);
    }

    private static Purchase parseJson2(String value) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(value, Purchase.class);
    }

}