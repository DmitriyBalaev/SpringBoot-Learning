package dmbl.moneytransferservice.service.impl;

import dmbl.moneytransferservice.dto.AccountDto;
import dmbl.moneytransferservice.model.Account;
import dmbl.moneytransferservice.repositry.AccountRepository;
import dmbl.moneytransferservice.service.AccountService;
import dmbl.moneytransferservice.service.Convector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final Convector convector;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, Convector convector) {
        this.accountRepository = accountRepository;
        this.convector = convector;
    }

    @Override
    public AccountDto createAccount(AccountDto newAccount) {
        accountRepository.save(convector.convertToEntity(newAccount));
        return newAccount;
    }

    @Override
    public AccountDto readAccount(Long id) {
        return convector.convertToDto(accountRepository.findById(id).orElseThrow());
    }

    @Override
    public List<AccountDto> readAllAccounts() {
        return convector.convertEntitiesToDto(accountRepository.findAll());    }

    @Override
    public void withdrawAccount(Long id, Long amount) {
        Account account = accountRepository.findById(id).get();
        AccountDto accountDto = convector.convertToDto(account);
        accountDto.balanceWithdraw(amount);
        accountRepository.save(convector.convertToEntity(accountDto));
    }

    @Override
    public void replenishAccount(Long id, Long amount) {
        Account account = accountRepository.findById(id).get();
        AccountDto accountDto = convector.convertToDto(account);
        accountDto.balanceReplenishment(amount);
        accountRepository.save(convector.convertToEntity(accountDto));
    }

    @Override
    public void transfer(Long idFromAccount, Long idToAccount, Long amount) {
        Account fromAccount = accountRepository.findById(idFromAccount).get();
        Account toAccount = accountRepository.findById(idToAccount).get();

        if (fromAccount.getBalance() >= amount){
            AccountDto fromAccountDto = convector.convertToDto(fromAccount);
            AccountDto toAccountDto = convector.convertToDto(toAccount);

            fromAccountDto.balanceWithdraw(amount);
            toAccountDto.balanceReplenishment(amount);

            accountRepository.save(convector.convertToEntity(fromAccountDto));
            accountRepository.save(convector.convertToEntity(toAccountDto));
        }else {
            throw new IllegalArgumentException("Не достаточно средств для перевода");
        }
    }
}
