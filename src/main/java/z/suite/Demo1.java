package z.suite;

import java.io.IOException;

import cloud.cases.EditGoodsCase;
import saas.cases.AddGoodCase;
import wms.cases.WmsEditGoodsCase;

public class Demo1 {

	public static void main(String[] args) throws IOException {
		
		AddGoodCase.addgoods("/src/main/java/saas/cases/saaslogin.json","/src/main/java/saas/cases/addgoodcase01.json");
		EditGoodsCase.editgoods("/src/main/java/cloud/cases/cloudlogin.json", "/src/main/java/cloud/cases/cloudgoodscase01.json");
		WmsEditGoodsCase.wmsEditgoods("/src/main/java/wms/cases/wmslogin.json", "/src/main/java/wms/cases/wmsgoods.json");


	}

}
