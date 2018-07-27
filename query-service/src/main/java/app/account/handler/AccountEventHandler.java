package app.account.handler;

import app.account.entity.AccountEntity;
import app.account.repository.AccountWriteOnlyRepository;
import app.events.account.AccountCreatedEvent;
import app.events.account.MoneyDepositedEvent;
import app.events.account.MoneyWithdrawnEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("accountEvents")
public class AccountEventHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final AccountWriteOnlyRepository repository;

    public AccountEventHandler(AccountWriteOnlyRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(AccountCreatedEvent event) {
        AccountEntity createdAccount = repository.save(new AccountEntity(
                event.getId(),
                event.getBalance(),
                event.getAccountCreator()));

        logger.info("Account {} is saved", createdAccount);
    }

    @EventHandler
    public void on(MoneyDepositedEvent event) {
        AccountEntity entity = repository.findById(event.getId()).orElseThrow(() -> new RuntimeException("Account not found"));
        AccountEntity newEntity = new AccountEntity(entity.getId(), entity.getBalance() + event.getAmount(), entity.getOwner());

        repository.save(newEntity);
    }

    @EventHandler
    public void on(MoneyWithdrawnEvent event) {
        AccountEntity entity = repository.findById(event.getId()).orElseThrow(() -> new RuntimeException("Account not found"));
        AccountEntity newEntity = new AccountEntity(entity.getId(), entity.getBalance() - event.getAmount(), entity.getOwner());

        repository.save(newEntity);
    }
}
