package dmbl.moneytransferservice.service;

import dmbl.moneytransferservice.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto newAccount);

    AccountDto readAccount(Long id);

    List<AccountDto> readAllAccounts();

    void withdrawAccount (Long id, Long amount);

    void replenishAccount (Long id, Long amount);

    void transfer (Long idFromAccount, Long idToAccount, Long amount);

}
