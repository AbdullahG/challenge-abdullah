package com.tooltime.abdullahchallenge.entity;

import java.util.List;

public class Invoice {
  private final String id;
  private final String code;
  private final String title;
  private final String description;
  private final String issuedAt;
  private final Customer customer;
  private final List<InvoicePosition> positions;
  private final Float totalAmount;

  public Invoice(String id, String code, String title, String description, String issuedAt,
      Customer customer, List<InvoicePosition> positions, Float totalAmount) {
    this.id = id;
    this.code = code;
    this.title = title;
    this.description = description;
    this.issuedAt = issuedAt;
    this.customer = customer;
    this.positions = positions;
    this.totalAmount = totalAmount;
  }

  public String getId() {
    return id;
  }

  public String getCode() {
    return code;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getIssuedAt() {
    return issuedAt;
  }

  public Customer getCustomer() {
    return customer;
  }

  public List<InvoicePosition> getPositions() {
    return positions;
  }

  public Float getTotalAmount() {
    return totalAmount;
  }
}
