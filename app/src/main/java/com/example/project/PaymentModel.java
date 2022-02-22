package com.example.project;

public class PaymentModel {

    private String paymentID;
    private String payDate;
    private String payCategory;
    private String amount;

    public PaymentModel() {
    }

    public PaymentModel(String paymentID, String payDate, String payCategory, String amount) {
        this.paymentID = paymentID;
        this.payDate = payDate;
        this.payCategory = payCategory;
        this.amount = amount;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPayCategory() {
        return payCategory;
    }

    public void setPayCategory(String payCategory) {
        this.payCategory = payCategory;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
