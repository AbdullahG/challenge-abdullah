package com.tooltime.abdullahchallenge.entity;

public class InvoicePosition {
  private final String description;
  private final Float totalAmount;

  public InvoicePosition(String description, Float totalAmount) {
    this.description = description;
    this.totalAmount = totalAmount;
  }

  public String getDescription() {
    return description;
  }

  public Float getTotalAmount() {
    return totalAmount;
  }
}
