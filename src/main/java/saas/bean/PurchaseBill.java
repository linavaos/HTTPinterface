package saas.bean;

import java.util.List;

public class PurchaseBill {

	private String	id;
	private String	billNo;
	private String	type;
	private String	redFlag;
	private String	approveFlag;
	private String	orderBillId;
	private String	orderBillNo;
	//不知道干啥的，传2即可
	private String	deliveryType;
	//不知道干啥的，传空即可
	private String	workTimeBackup;
	private String	mpState;
	private String	supplierId;
	private String	supplierName;
	private String	workOperId;
	private String	warehouseId;
	private String	workTime;
	private String	remark;
	private double	totalAmount;
	//本次收款金额
	private double	nowPaidAmount;
	//合计销售单价总额
	private double	totalSaleamount;
	//合计税额
	private double	totalRateamount;
	private double	nowDiscountAmount;
	private double	afterDiscountAmount;
	private List<Account>	accountlist;
	private double	nowLeftAmount;
	private List<Detail> detaillist;
	private String	selectBaseUnit;
	private String deptId;



	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

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
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getNowPaidAmount() {
		return nowPaidAmount;
	}
	public void setNowPaidAmount(double nowPaidAmount) {
		this.nowPaidAmount = nowPaidAmount;
	}
	public double getTotalSaleamount() {
		return totalSaleamount;
	}
	public void setTotalSaleamount(double totalSaleamount) {
		this.totalSaleamount = totalSaleamount;
	}
	public double getTotalRateamount() {
		return totalRateamount;
	}
	public void setTotalRateamount(double totalRateamount) {
		this.totalRateamount = totalRateamount;
	}
	public double getNowDiscountAmount() {
		return nowDiscountAmount;
	}
	public void setNowDiscountAmount(double nowDiscountAmount) {
		this.nowDiscountAmount = nowDiscountAmount;
	}
	public double getAfterDiscountAmount() {
		return afterDiscountAmount;
	}
	public void setAfterDiscountAmount(double afterDiscountAmount) {
		this.afterDiscountAmount = afterDiscountAmount;
	}
	public List<Account> getAccountlist() {
		return accountlist;
	}
	public void setAccountlist(List<Account> accountlist) {
		this.accountlist = accountlist;
	}
	public double getNowLeftAmount() {
		return nowLeftAmount;
	}
	public void setNowLeftAmount(double nowLeftAmount) {
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


	@Override
	public String toString() {
		return "PurchaseBill [id=" + id + ", billNo=" + billNo + ", type=" + type + ", redFlag=" + redFlag
				+ ", approveFlag=" + approveFlag + ", orderBillId=" + orderBillId + ", orderBillNo=" + orderBillNo
				+ ", deliveryType=" + deliveryType + ", workTimeBackup=" + workTimeBackup + ", mpState=" + mpState
				+ ", supplierId=" + supplierId + ", supplierName=" + supplierName + ", workOperId=" + workOperId
				+ ", warehouseId=" + warehouseId + ", workTime=" + workTime + ", remark=" + remark + ", totalAmount="
				+ totalAmount + ", nowPaidAmount=" + nowPaidAmount + ", totalSaleamount=" + totalSaleamount
				+ ", totalRateamount=" + totalRateamount + ", nowDiscountAmount=" + nowDiscountAmount
				+ ", afterDiscountAmount=" + afterDiscountAmount + ", accountlist=" + accountlist + ", nowLeftAmount="
				+ nowLeftAmount + ", detaillist=" + detaillist + ", selectBaseUnit=" + selectBaseUnit + "]";
	}



	public String tourlString() throws IllegalArgumentException, IllegalAccessException {
		String urlparam="?id=" + id + "&billNo=" + billNo + "&type=" + type + "&redFlag=" + redFlag
				+ "&approveFlag=" + approveFlag + "&orderBillId=" + orderBillId + "&orderBillNo=" + orderBillNo
				+ "&deliveryType=" + deliveryType + "&workTimeBackup=" + workTimeBackup + "&mpState=" + mpState
				+ "&supplierId=" + supplierId + "&supplierName=" + supplierName + "&workOperId=" + workOperId
				+ "&warehouseId=" + warehouseId + "&workTime=" + workTime + "&remark=" + remark + "&totalAmount="
				+ totalAmount + "&nowPaidAmount=" + nowPaidAmount + "&totalSaleamount=" + totalSaleamount
				+ "&totalRateamount=" + totalRateamount + "&nowDiscountAmount=" + nowDiscountAmount
				+ "&afterDiscountAmount=" + afterDiscountAmount + "&nowLeftAmount="
				+ nowLeftAmount + "&selectBaseUnit=" + selectBaseUnit;

		for(Account account:accountlist){
			urlparam+=account.tourlString();
		}
		
		for(Detail detail:detaillist){
			urlparam+=detail.tourlString();
			
		}

		return urlparam.replace("null", "");
	}


}
