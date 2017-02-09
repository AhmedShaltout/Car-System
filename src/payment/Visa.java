package payment;

public class Visa implements PaymentMethod {

    private static final long serialVersionUID = 1L;

    private long Number;
    private String Type;
    private short Pin;
    private float Balance;
    private int ExpireMonth;
    private int ExpireYear;

    public Visa() {
        this.ExpireYear=0;
        this.ExpireMonth=0;
        this.Balance=100000000f;
        this.Pin=0;
        this.Type="";
        this.Number=0L;
    }

    public void setNumber(long Visa) {
        this.Number=Visa;
    }

    public void setType(String Type) {
        this.Type=Type;
    }

    public void setExpireDate(int month,int year) {
        this.ExpireMonth=month;
        this.ExpireYear=year;
    }

    public void setPin(short Pin) {
        this.Pin=Pin;
    }

    public long getNumber() {
        return this.Number;
    }

    public String getType() {
        return this.Type;
    }

    public StringBuilder getExpireDate() {
        StringBuilder ExpireDate = new StringBuilder();
        ExpireDate.append(this.ExpireYear).append(" ").append(this.ExpireYear);
        return ExpireDate;
    }

    public short getPin() {
        return this.Pin;
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

}
