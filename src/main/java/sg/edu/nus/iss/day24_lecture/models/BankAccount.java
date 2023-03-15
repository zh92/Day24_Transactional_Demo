package sg.edu.nus.iss.day24_lecture.models;

public class BankAccount {
    
    private int id;
    private String fullName;
    private Boolean isActive;
    private String acctType;
    private float balance;

    public BankAccount() {
    }
    
    public BankAccount(int id, String fullName, Boolean isActive, String acctType, float balance) {
        this.id = id;
        this.fullName = fullName;
        this.isActive = isActive;
        this.acctType = acctType;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public String getAcctType() {
        return acctType;
    }
    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }
    public float getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }

    
}
