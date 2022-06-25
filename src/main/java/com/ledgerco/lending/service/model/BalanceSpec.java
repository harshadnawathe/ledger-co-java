package com.ledgerco.lending.service.model;

import lombok.Value;

@Value
public class BalanceSpec {
    int amountPaid;
    int numEmiRemaining;
}
