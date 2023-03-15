package sg.edu.nus.iss.day24_lecture.payloads;

public class TransferRequest {
    
    private int accountFrom;
    private int accountTo;
    private Float amount;
    
    public TransferRequest() {
    }
    
    public TransferRequest(int accountFrom, int accountTo, Float amount) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public int getAccountFrom() {
        return accountFrom;
    }
    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }
    public int getAccountTo() {
        return accountTo;
    }
    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
