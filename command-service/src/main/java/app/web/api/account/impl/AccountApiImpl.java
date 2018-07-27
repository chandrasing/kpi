package app.web.api.account.impl;


import app.account.aggregate.AccountOwner;
import app.account.commands.AccountCommandFactory;
import app.web.api.account.AccountCommandApi;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class AccountApiImpl implements AccountCommandApi {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CommandGateway commandGateway;
    private final AccountCommandFactory commandFactory = AccountCommandFactory.INSTANCE;

    @Autowired
    public AccountApiImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createAccount(AccountOwner user) {
        Assert.hasLength(user.getName(), "Missing commands creator");
        String accountId = UUID.randomUUID().toString();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() ->
                commandGateway.sendAndWait(commandFactory.newCreateAccountCommand(accountId, user.getName()))
        );
        return completableFuture
                .thenApply(s -> s)
                .exceptionally((e) -> {
                    logger.warn("Create Account {} failed", user);
                    throw new RuntimeException(String.format("Create Account %s failed", user), e);
                });
    }

    @Override
    public CompletableFuture update(String accountId, double amount) {
        try {
            CompletableFuture<ResponseEntity> completableFuture = CompletableFuture.supplyAsync(() -> amount > 0
                    ? commandGateway.sendAndWait(commandFactory.newDepositMoneyCommand(accountId, amount))
                    : commandGateway.sendAndWait(commandFactory.newWithdrawMoneyCommand(accountId, amount)));

            return completableFuture.thenApply((s) -> ResponseEntity.ok().build());
        } catch (AssertionError | IllegalArgumentException e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().build());
        } catch (CommandExecutionException cex) {
            return CompletableFuture.completedFuture(new ResponseEntity(HttpStatus.CONFLICT));
        }
    }

    @Override
    public CompletableFuture delete(String accountId) {
        try {
            return commandGateway.sendAndWait(commandFactory.newCloseAccountCommand(accountId));
        } catch (AssertionError | IllegalArgumentException e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().build());
        } catch (CommandExecutionException cex) {
            return CompletableFuture.completedFuture(new ResponseEntity(HttpStatus.CONFLICT));
        }
    }
}
