package dmbl.moneytransferservice.service.impl;

import dmbl.moneytransferservice.dto.AccountDto;
import dmbl.moneytransferservice.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AccountServiceImpl {

    @MockBean
    private AccountService accountService;

    @Test
    public void shouldReturnAccount() {
        Long balance = 100L;
        AccountDto first = new AccountDto(1L, 100L);
        when(accountService.readAccount(first.getId())).thenReturn(first);

        AccountDto found = accountService.readAccount(1L);

        assertThat(found.getBalance()).isEqualTo(balance);
    }

    @Test
    public void shouldReturnAllAccounts() {
        AccountDto first = new AccountDto(1L, 100L);
        AccountDto second = new AccountDto(2L, 200L);
        AccountDto third = new AccountDto(3L, 300L);

        List<AccountDto> allAccounts = Arrays.asList(first, second, third);

        when(accountService.readAllAccounts()).thenReturn(allAccounts);

        assertThat(allAccounts).hasSize(3).extracting(AccountDto::getBalance).containsOnly(first.getBalance(), second.getBalance(), third.getBalance());
    }

    @Test
    public void whenCreatedAccountShouldReturnAccount() {
        AccountDto newAccount = new AccountDto(1L, 100L);

        when(accountService.createAccount(newAccount)).thenReturn(newAccount);

        assertThat(newAccount.getId().equals(1L));
    }
}
