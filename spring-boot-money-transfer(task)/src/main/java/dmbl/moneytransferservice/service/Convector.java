package dmbl.moneytransferservice.service;

import dmbl.moneytransferservice.dto.AccountDto;
import dmbl.moneytransferservice.model.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class Convector {
    private final ModelMapper modelMapper;

    @Autowired
    public Convector(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AccountDto convertToDto(Account account){
        return Objects.isNull(account) ? null : modelMapper.map(account, AccountDto.class);
    }

    public Account convertToEntity(AccountDto accountDto){
        return Objects.isNull(accountDto) ? null : modelMapper.map(accountDto, Account.class);
    }

    public List<AccountDto> convertEntitiesToDto(List<Account> accounts){
        return accounts.stream()
                .map(this::convertToDto)
                .toList();
    }
}
