package app.account.aggregate;

import app.account.commands.CreateAccountCommand;
import app.events.account.AccountCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

public class AccountAggregateTest {
    private AggregateTestFixture<AccountAggregate> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(AccountAggregate.class);
    }

    @Test
    public void testCreateAccount() {
        fixture.givenNoPriorActivity()
                .when(new CreateAccountCommand("account1", "test commands"))
                .expectEvents(new AccountCreatedEvent("account1", "test commands", 0));
    }
}
