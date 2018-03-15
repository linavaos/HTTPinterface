package wms.bean;

public class Accepstep{

	private String taskid;
	private String billType;
	private String goodsId;
	private String goodsname;
	private String goodsCode;
	private double srcstorageNumber;
	private double storageNumber;
	private String expireDate;
	private String productionDate;
	private String goodsBatch;
	private String goodsQuality;
	private String csrf;
	private String perStackNumber;
	private String taskCode;
	private String[] acceDetailId;
	private String acceDetailIdString;
	private String unitName;




	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public double getSrcstorageNumber() {
		return srcstorageNumber;
	}
	public void setSrcstorageNumber(double srcstorageNumber) {
		this.srcstorageNumber = srcstorageNumber;
	}
	public double getStorageNumber() {
		return storageNumber;
	}
	public void setStorageNumber(double storageNumber) {
		this.storageNumber = storageNumber;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}
	public String getGoodsBatch() {
		return goodsBatch;
	}
	public void setGoodsBatch(String goodsBatch) {
		this.goodsBatch = goodsBatch;
	}
	public String getGoodsQuality() {
		return goodsQuality;
	}
	public void setGoodsQuality(String goodsQuality) {
		this.goodsQuality = goodsQuality;
	}
	public String getCsrf() {
		return csrf;
	}
	public void setCsrf(String csrf) {
		this.csrf = csrf;
	}
	public String getPerStackNumber() {
		return perStackNumber;
	}
	public void setPerStackNumber(String perStackNumber) {
		this.perStackNumber = perStackNumber;
	}
	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}		
	public String getAcceDetailIdString() {
		return acceDetailIdString;
	}
	public void setAcceDetailIdString(String acceDetailIdString) {
		this.acceDetailIdString = acceDetailIdString;
	}

	public String[] getAcceDetailId() {
		return acceDetailId;
	}
	public void setAcceDetailId() {
		this.acceDetailId = acceDetailIdString.split(",");
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


}
