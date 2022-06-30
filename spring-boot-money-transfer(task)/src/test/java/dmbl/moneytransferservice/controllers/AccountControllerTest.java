package dmbl.moneytransferservice.controllers;

import dmbl.moneytransferservice.dto.AccountDto;
import dmbl.moneytransferservice.service.AccountService;
import dmbl.moneytransferservice.util.JsonUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;



import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService service;

    private static final Long id = 1L;
    private static final Long amount = 100L;

    @Test
    public void whenPostAccount_thenCreateAccount() throws Exception {
        AccountDto accountToSave = new AccountDto();
        accountToSave.setBalance(100L);
        accountToSave.setId(1L);
        given(service.createAccount(Mockito.any())).willReturn(accountToSave);


        mvc.perform(post("/api/v1/accounts").contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(accountToSave)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.balance", is(100)))
                        .andExpect(jsonPath("$.id", is(1)));
        verify(service, VerificationModeFactory.times(1)).createAccount(Mockito.any());
        reset(service);
    }

    @Test
    public void givenAccount_whenReadAccount_thenReturnAccount() throws Exception {
        AccountDto accountToSave = new AccountDto();
        accountToSave.setBalance(100L);
        accountToSave.setId(1L);
        given(service.readAccount(id)).willReturn(accountToSave);

        mvc.perform(get("/api/v1/accounts/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.balance", is(100)));
        verify(service, VerificationModeFactory.times(1)).readAccount(id);
        reset(service);
    }

    @Test
    public void givenAccounts_whenReadAllAccounts_thenReturnJsonArray() throws Exception {
        AccountDto firstAccount = new AccountDto(0L,100L);
        AccountDto secondAccount = new AccountDto(1L,200L);
        AccountDto thirdAccount = new AccountDto(2L, 300L);

        List<AccountDto> allAccounts = Arrays.asList(firstAccount, secondAccount, thirdAccount);

        given(service.readAllAccounts()).willReturn(allAccounts);

        mvc.perform(get("/api/v1/accounts/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].balance", is(100)))
                .andExpect(jsonPath("$[1].balance", is(200)))
                .andExpect(jsonPath("$[2].balance", is(300)));
        verify(service, VerificationModeFactory.times(1)).readAllAccounts();
        reset(service);
    }

}