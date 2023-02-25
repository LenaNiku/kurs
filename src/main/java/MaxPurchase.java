import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MaxPurchase {
    String category;
    Integer sum;

    public MaxPurchase(String category, Integer sum) {
        this.category = category;
        this.sum = sum;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // wrap with try
        return mapper.writeValueAsString(this);
    }
}
