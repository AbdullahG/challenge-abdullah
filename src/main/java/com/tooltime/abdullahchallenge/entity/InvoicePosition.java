package com.tooltime.abdullahchallenge.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class InvoicePosition {
  private String description;
  private Float totalAmount;

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
