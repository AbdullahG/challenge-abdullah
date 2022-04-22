package com.tooltime.abdullahchallenge.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceDto {
  private String title;
  private String description;
  private long customerId;
  private List<InvoicePositionDto> positions;
}
