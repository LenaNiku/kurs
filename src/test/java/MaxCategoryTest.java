import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.io.File;
import java.io.IOException;

public class MaxCategoryTest {

    Server server = Mockito.mock(Server.class);

    final String JSON_STRING_PURCHASE_1 = "{\"title\":\"хлеб\",\"date\":\"6666\",\"sum\":700}";
    final String JSON_STRING_PURCHASE_2 = "{\"title\":\"хлеб\",\"date\":\"6666\",\"sum\":800}";
    final String JSON_STRING_PURCHASE_3 = "{\"title\":\"хлеб\",\"date\":\"6666\",\"sum\":900}";

    final String MAX_PURCHASE_CATEGORY_TITLE = "еда";

    final String PURCHASE_TITLE_1 = "булка";
    final Integer MAX_PURCHASE_SUM_280 = 280;
    final String PURCHASE_TITLE_2 = "мыло";
    final Integer PURCHASE_SUM_180 = 180;
    final Integer PURCHASE_SUM_100 = 100;
    final Integer PURCHASE_SUM_190 = 190;

    @Test
    public void maxCategory_test() throws IOException {

        File categoriesFile = new File("categories.tsv");
        MaxCategory maxCategory = new MaxCategory(categoriesFile);

        Purchases purchases = new Purchases();

        given(server.processRequest(JSON_STRING_PURCHASE_1)).willReturn(
                new Purchase(PURCHASE_TITLE_1, "2022.02.15", PURCHASE_SUM_180));
        given(server.processRequest(JSON_STRING_PURCHASE_2)).willReturn(
                new Purchase(PURCHASE_TITLE_1, "2022.02.15", PURCHASE_SUM_100));
        given(server.processRequest(JSON_STRING_PURCHASE_3)).willReturn(
                new Purchase(PURCHASE_TITLE_2, "2022.02.15", PURCHASE_SUM_190));

        Purchase purchase1 = server.processRequest(JSON_STRING_PURCHASE_1);
        purchases.addPurchase(purchase1);
        Purchase purchase2 = server.processRequest(JSON_STRING_PURCHASE_2);
        purchases.addPurchase(purchase2);
        Purchase purchase3 = server.processRequest(JSON_STRING_PURCHASE_3);
        purchases.addPurchase(purchase3);

        MaxPurchase maxPurchase = maxCategory.getMaxPurchase(purchases);

        assertEquals(MAX_PURCHASE_CATEGORY_TITLE, maxPurchase.getCategory());
        assertEquals(MAX_PURCHASE_SUM_280, maxPurchase.getSum());

    }

}
