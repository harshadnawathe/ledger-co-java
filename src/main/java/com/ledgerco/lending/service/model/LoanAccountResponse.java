package com.ledgerco.lending.service.model;

import com.ledgerco.lending.domain.Balance;
import com.ledgerco.lending.domain.LoanAccount;
import lombok.Value;

@Value
public class LoanAccountResponse {
    AccountSpec account;
    BalanceSpec balance;

    public static LoanAccountResponse of(Balance accountBalance) {
        return new LoanAccountResponse(
                new AccountSpec(
                        accountBalance.getBank(),
                        accountBalance.getCustomer()
                ),
                new BalanceSpec(
                        accountBalance.getAmountPaid(),
                        accountBalance.getNumEmiRemaining()
                )
        );
    } 
}
