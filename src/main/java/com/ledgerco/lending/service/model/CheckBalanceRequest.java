package com.ledgerco.lending.service.model;

import lombok.Value;

@Value
public class CheckBalanceRequest {
    AccountSpec account;
    int monthNo;
}
