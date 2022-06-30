package dmbl.moneytransferservice.repository;

import dmbl.moneytransferservice.model.Account;
import dmbl.moneytransferservice.repositry.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void whenFindByBalance_thenReturnAccount (){
        Account testAccount = new Account();
        testAccount.setBalance(133L);
        entityManager.persistAndFlush(testAccount);

        Account found = accountRepository.findByBalance(1000L);
        assertThat(found.getBalance().equals(testAccount.getBalance()));
    }

    @Test
    public void whenInvalidBalance_thenReturnNull() {
        Account fromDb = accountRepository.findByBalance(100L);
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Account fromDb = accountRepository.findById(-11L).orElse(null);
        assertThat(fromDb).isNull();
    }
}
