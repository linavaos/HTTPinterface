package saas.bean;

import java.lang.reflect.Field;

public class Detail {

	private	String	articleNumber;
	private	String	goodsName;
	private	String	goodsId;
	private	String	barcode	;
	//生产日期是否开启0没开启,1开启
	private	String	productionDateState;
	private	String	currUnitId;
	private	String	currUnitName;
	private	String	currUnitFactor;
	private	Double	origPrice;
	private	Double	realPrice;
	private	Double	costPrice;
	private	String	currUnitFactorName;
	private	String	productionDate;
	private	String	baseUnitId;
	private	String	baseUnitName;
	private	Double	baseUnitFactor;
	private	String	baseBarcode;
	private	Double	basePurchase;
	private	String	pkgUnitId;
	private	String	pkgUnitName;
	private	Double	pkgUnitFactor;
	private	String	pkgBarcode;
	private	Double	pkgPurchase;
	private	String	unitFactorName;
	private	Double	quantity;
	private	Double  subAmount;
	private	int	seq;


	public String getArticleNumber() {
		return articleNumber;
	}
	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getProductionDateState() {
		return productionDateState;
	}
	public void setProductionDateState(String productionDateState) {
		this.productionDateState = productionDateState;
	}
	public String getCurrUnitId() {
		return currUnitId;
	}
	public void setCurrUnitId(String currUnitId) {
		this.currUnitId = currUnitId;
	}
	public String getCurrUnitName() {
		return currUnitName;
	}
	public void setCurrUnitName(String currUnitName) {
		this.currUnitName = currUnitName;
	}
	public String getCurrUnitFactor() {
		return currUnitFactor;
	}
	public void setCurrUnitFactor(String currUnitFactor) {
		this.currUnitFactor = currUnitFactor;
	}
	public Double getOrigPrice() {
		return origPrice;
	}
	public void setOrigPrice(Double origPrice) {
		this.origPrice = origPrice;
	}
	public Double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public String getCurrUnitFactorName() {
		return currUnitFactorName;
	}
	public void setCurrUnitFactorName(String currUnitFactorName) {
		this.currUnitFactorName = currUnitFactorName;
	}
	public String getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}
	public String getBaseUnitId() {
		return baseUnitId;
	}
	public void setBaseUnitId(String baseUnitId) {
		this.baseUnitId = baseUnitId;
	}
	public String getBaseUnitName() {
		return baseUnitName;
	}
	public void setBaseUnitName(String baseUnitName) {
		this.baseUnitName = baseUnitName;
	}
	public Double getBaseUnitFactor() {
		return baseUnitFactor;
	}
	public void setBaseUnitFactor(Double baseUnitFactor) {
		this.baseUnitFactor = baseUnitFactor;
	}
	public String getBaseBarcode() {
		return baseBarcode;
	}
	public void setBaseBarcode(String baseBarcode) {
		this.baseBarcode = baseBarcode;
	}
	public Double getBasePurchase() {
		return basePurchase;
	}
	public void setBasePurchase(Double basePurchase) {
		this.basePurchase = basePurchase;
	}
	public String getPkgUnitId() {
		return pkgUnitId;
	}
	public void setPkgUnitId(String pkgUnitId) {
		this.pkgUnitId = pkgUnitId;
	}
	public String getPkgUnitName() {
		return pkgUnitName;
	}
	public void setPkgUnitName(String pkgUnitName) {
		this.pkgUnitName = pkgUnitName;
	}
	public Double getPkgUnitFactor() {
		return pkgUnitFactor;
	}
	public void setPkgUnitFactor(Double pkgUnitFactor) {
		this.pkgUnitFactor = pkgUnitFactor;
	}
	public String getPkgBarcode() {
		return pkgBarcode;
	}
	public void setPkgBarcode(String pkgBarcode) {
		this.pkgBarcode = pkgBarcode;
	}
	public Double getPkgPurchase() {
		return pkgPurchase;
	}
	public void setPkgPurchase(Double pkgPurchase) {
		this.pkgPurchase = pkgPurchase;
	}
	public String getUnitFactorName() {
		return unitFactorName;
	}
	public void setUnitFactorName(String unitFactorName) {
		this.unitFactorName = unitFactorName;
	}

	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getSubAmount() {
		return subAmount;
	}
	public void setSubAmount(Double subAmount) {
		this.subAmount = subAmount;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String tourlString() throws IllegalArgumentException, IllegalAccessException{

		String urlparam = null;
		//获得当前对象的Class
		Class c = this.getClass();
		//获得所有Filed
		Field fileds[] = c.getDeclaredFields();

		for(Field filed:fileds){
			filed.setAccessible(true);
			urlparam+="&details["+seq+"]."+filed.getName()+"="+filed.get(this);
		}
		
		return urlparam.replace("null", "");

	}


}
