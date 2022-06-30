package dmbl.moneytransferservice.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountDtoTest {
    @Test
    void balanceWithdrawTest() {
        AccountDto testAccount = new AccountDto(100L);
        testAccount.balanceWithdraw(100L);
        Long actual = testAccount.getBalance();
        assertEquals(0, actual);
    }

    @Test
    void balanceReplenishmentTest() {
        AccountDto testAccount = new AccountDto(100L);
        testAccount.balanceReplenishment(100L);
        Long actual = testAccount.getBalance();
        assertEquals(200L, actual);
    }


}
