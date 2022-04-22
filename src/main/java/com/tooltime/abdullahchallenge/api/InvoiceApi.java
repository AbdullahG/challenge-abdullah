package com.tooltime.abdullahchallenge.api;

import com.tooltime.abdullahchallenge.dto.CreateInvoiceDto;
import com.tooltime.abdullahchallenge.entity.Customer;
import com.tooltime.abdullahchallenge.entity.Invoice;
import com.tooltime.abdullahchallenge.entity.InvoicePosition;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceApi {

  @PostMapping
  public Invoice create(@RequestBody CreateInvoiceDto createInvoiceDto) {
    return new Invoice(
        "123",
        "1234",
        createInvoiceDto.getTitle(),
        createInvoiceDto.getDescription(),
        new Date().toString(),
        new Customer("customer-id-1", "customer-name", "customer-address"),
        createInvoiceDto.getPositions()
            .stream().map(dto -> new InvoicePosition(dto.getDescription(), dto.getAmount()))
            .collect(Collectors.toList()),
        12.2f
    );
  }
}
