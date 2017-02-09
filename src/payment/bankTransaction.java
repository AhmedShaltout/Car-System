package payment;

public class bankTransaction implements PaymentMethod {

    private static final long serialVersionUID = 1L;

    private String BankName;

    private String BankAccountNummber;

    private float Balance;
    public bankTransaction(){
        this.Balance=100000000f;
    }

    @Override
    public float getBalance() {
        return this.Balance;
    }

    @Override
    public void takeMoney(float pricePerH) {
        this.Balance-=pricePerH;
    }

    @Override
    public void addBalance(float f) {
        this.Balance+=f;
    }
    public void setBankName(String Name){
        this.BankName=Name;
    }
    public String getBankName(){
        return this.BankName;
    }
    public void setAccountNumber(String Number){
        this.BankAccountNummber=Number;
    }
    public String getAccountNumber(){
        return this.BankAccountNummber;
    }



}   