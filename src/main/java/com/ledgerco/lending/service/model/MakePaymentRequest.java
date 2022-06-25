package com.ledgerco.lending.service.model;

import lombok.Value;

@Value
public class MakePaymentRequest {
    AccountSpec account;
    PaymentSpec payment;
}
