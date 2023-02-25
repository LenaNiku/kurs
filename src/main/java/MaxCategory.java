import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MaxCategory {

    private Map<String, String> categories = new HashMap<String, String>();

    //List<Purchase> purchases = new ArrayList<>();

    public MaxCategory(File categoriesFile) {
        MaxCategory maxCategory = new MaxCategory();
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
                String value = reader.readLine();
                String[] item_category = value.split("\t");
                categories.put(item_category[0], item_category[1]);
            }
        }

        catch (IOException e) {
            System.out.println(e);
        }
    }

    public MaxPurchase getMaxPurchase(Purchases purchases) {

        List<Purchase> allPurchases = purchases.getPurchases();

        allPurchases.sort(
                Comparator.comparingInt(Purchase::getSum)
        );

        Purchase maxPurchase = allPurchases.get(allPurchases.size() - 1);

        return new MaxPurchase(maxPurchase.getTitle(), maxPurchase.getSum());

    }

}
