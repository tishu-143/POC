package com.optum.pathway.poc.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.optum.pathway.poc.model.BankAccount;
import com.optum.pathway.poc.model.Currency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class BankAccountResolver implements GraphQLQueryResolver {
    public BankAccount bankAccount(UUID uuid){
      log.info("Retrieving bank account info:\nID = {}", uuid);
      return BankAccount.builder().id(uuid).currency(Currency.INR).name("HDFC").build();
    }
}
