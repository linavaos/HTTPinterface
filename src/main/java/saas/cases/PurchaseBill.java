package saas.cases;

import java.util.List;

public class PurchaseBill {

	private String	id;
	private String	billNo;
	private String	type;
	private String	redFlag;
	private String	approveFlag;
	private String	orderBillId;
	private String	orderBillNo;
	private String	deliveryType;
	private String	workTimeBackup;
	private String	mpState;
	private String	supplierId;
	private String	supplierName;
	private String	workOperId;
	private String	warehouseId;
	private String	workTime;
	private String	remark;
	private String	totalAmount;
	private String	nowPaidAmount;
	private String	totalSaleamount;
	private String	totalRateamount;
	private String	nowDiscountAmount;
	private String	afterDiscountAmount;
	private List<Account>	accountlist;
	private String	nowLeftAmount;
	private List<Detail> detaillist;
	private String	selectBaseUnit;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRedFlag() {
		return redFlag;
	}
	public void setRedFlag(String redFlag) {
		this.redFlag = redFlag;
	}
	public String getApproveFlag() {
		return approveFlag;
	}
	public void setApproveFlag(String approveFlag) {
		this.approveFlag = approveFlag;
	}
	public String getOrderBillId() {
		return orderBillId;
	}
	public void setOrderBillId(String orderBillId) {
		this.orderBillId = orderBillId;
	}
	public String getOrderBillNo() {
		return orderBillNo;
	}
	public void setOrderBillNo(String orderBillNo) {
		this.orderBillNo = orderBillNo;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public String getWorkTimeBackup() {
		return workTimeBackup;
	}
	public void setWorkTimeBackup(String workTimeBackup) {
		this.workTimeBackup = workTimeBackup;
	}
	public String getMpState() {
		return mpState;
	}
	public void setMpState(String mpState) {
		this.mpState = mpState;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getWorkOperId() {
		return workOperId;
	}
	public void setWorkOperId(String workOperId) {
		this.workOperId = workOperId;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getNowPaidAmount() {
		return nowPaidAmount;
	}
	public void setNowPaidAmount(String nowPaidAmount) {
		this.nowPaidAmount = nowPaidAmount;
	}
	public String getTotalSaleamount() {
		return totalSaleamount;
	}
	public void setTotalSaleamount(String totalSaleamount) {
		this.totalSaleamount = totalSaleamount;
	}
	public String getTotalRateamount() {
		return totalRateamount;
	}
	public void setTotalRateamount(String totalRateamount) {
		this.totalRateamount = totalRateamount;
	}
	public String getNowDiscountAmount() {
		return nowDiscountAmount;
	}
	public void setNowDiscountAmount(String nowDiscountAmount) {
		this.nowDiscountAmount = nowDiscountAmount;
	}
	public String getAfterDiscountAmount() {
		return afterDiscountAmount;
	}
	public void setAfterDiscountAmount(String afterDiscountAmount) {
		this.afterDiscountAmount = afterDiscountAmount;
	}
	public List<Account> getAccountlist() {
		return accountlist;
	}
	public void setAccountlist(List<Account> accountlist) {
		this.accountlist = accountlist;
	}
	public String getNowLeftAmount() {
		return nowLeftAmount;
	}
	public void setNowLeftAmount(String nowLeftAmount) {
		this.nowLeftAmount = nowLeftAmount;
	}
	public List<Detail> getDetaillist() {
		return detaillist;
	}
	public void setDetaillist(List<Detail> detaillist) {
		this.detaillist = detaillist;
	}
	public String getSelectBaseUnit() {
		return selectBaseUnit;
	}
	public void setSelectBaseUnit(String selectBaseUnit) {
		this.selectBaseUnit = selectBaseUnit;
	}
	
}
