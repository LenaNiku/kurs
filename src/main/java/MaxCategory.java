import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MaxCategory {

    private final Map<String, String> categories = new HashMap<>();

    public MaxCategory(File categoriesFile) {
        loadCategoriesFromFile(categoriesFile);
    }

    public MaxCategory() {
    }

    private void loadCategoriesFromFile(File textFile) {

        try (BufferedReader reader = new BufferedReader(new FileReader(textFile)))  {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    break;
                }
                String[] item_category = line.split("\t");
                categories.put(item_category[0], item_category[1]);
            }
        }

        catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public MaxPurchase getMaxPurchase(Purchases purchases) {

        List<Purchase> allPurchases = purchases.getPurchases();
        for (Purchase purchase: allPurchases) {
            String otherCategoryKey = "другое";
            purchase.setCategory(categories.getOrDefault(purchase.getTitle(), otherCategoryKey));
        }

        Map.Entry<String, Integer> maxEntry = allPurchases
                .stream()
                .collect(Collectors.groupingBy(
                        Purchase::getCategory, Collectors.summingInt(Purchase::getSum)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        assert maxEntry != null;
        return new MaxPurchase(maxEntry.getKey(), maxEntry.getValue());

    }

}
