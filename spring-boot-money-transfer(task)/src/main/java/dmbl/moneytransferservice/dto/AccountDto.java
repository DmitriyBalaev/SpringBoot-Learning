package dmbl.moneytransferservice.dto;

public class AccountDto {
    private Long id;
    private Long balance;

    public AccountDto() {
    }

    public AccountDto(Long id, Long balance) {
        this.id = id;
        this.balance = balance;
    }

    public AccountDto(Long balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public void balanceReplenishment(Long replenishmentAmount) {
        if (replenishmentAmount > 0) {
            balance += replenishmentAmount;
        } else {
            throw new IllegalArgumentException("сумма пополнения не может быть отрицательной");
        }
    }

    public void balanceWithdraw(Long withdrawAmount) {
        if (withdrawAmount <= balance) {
            balance -= withdrawAmount;
        } else {
            throw new IllegalArgumentException("недостаточно средств");
        }

    }
}
