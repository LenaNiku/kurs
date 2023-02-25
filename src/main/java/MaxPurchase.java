import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MaxPurchase {
    private String category;
    private Integer sum;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

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
