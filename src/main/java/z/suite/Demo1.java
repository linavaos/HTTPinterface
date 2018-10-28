package z.suite;

import java.io.IOException;

import org.apache.http.client.HttpClient;
import saas.bean.PurchaseBill;
import saas.cases.ActionGoodCaseDoc;
import saas.cases.ActionPurchaseBill;
import saas.cases.LoginCase;
import saas.cases.NoSurchGoodsException;


public class Demo1 {

	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException, NoSurchGoodsException {
		
		HttpClient httpClient=LoginCase.loginCase("/src/main/java/saas/cases/saaslogin.json");
		//ActionGoodCaseDoc.addgoods(httpClient, "/src/main/java/saas/cases/addgoodcase01.json");
	    PurchaseBill purchaseBill2=ActionPurchaseBill.addpurchaseBill("/src/main/java/saas/cases/addpurchasebillcase01.json", httpClient);
	    ActionPurchaseBill.aprovepurchaseBill(httpClient, purchaseBill2);
		System.out.println(purchaseBill2);
	

	}

}
