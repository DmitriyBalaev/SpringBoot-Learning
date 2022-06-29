package dmbl.moneytransferservice.controllers;

import dmbl.moneytransferservice.dto.AccountDto;
import dmbl.moneytransferservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/accounts", produces = "application/json")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public ResponseEntity getAllAccounts(){
        return ResponseEntity.ok(accountService.readAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity getAccount(
            @PathVariable("id") final Long id
    ){
        return ResponseEntity.ok(accountService.readAccount(id));
    }

    @PostMapping
    public ResponseEntity createNewAccount(
            @RequestBody final AccountDto accountDto
    ){
        accountService.createAccount(accountDto);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/add/{amount}")
    public ResponseEntity replenishmentBalance(
            @PathVariable("id") Long id,
            @PathVariable("amount") Long amount
    ){
        accountService.replenishAccount(id, amount);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/withdraw/{amount}")
    public ResponseEntity withdrawalBalance(
            @PathVariable("id") Long id,
            @PathVariable("amount") Long amount
    ){
        accountService.withdrawAccount(id, amount);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{from}/transfer/{to}/{amount}")
    public ResponseEntity transfer(
            @PathVariable("from") Long idFrom,
            @PathVariable("to") Long idTo,
            @PathVariable("amount") Long amount
    ){
        accountService.transfer(idFrom, idTo, amount);
        return ResponseEntity.ok().build();
    }
}
