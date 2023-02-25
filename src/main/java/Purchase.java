import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Purchase {
    private String title;
    private String date;
    private Integer sum;

    public Purchase(@JsonProperty("title") String title, @JsonProperty("date") String date, @JsonProperty("sum") Integer sum) {
        this.title = title;
        this.date = date;
        this.sum = sum;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // wrap with try
        return mapper.writeValueAsString(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
