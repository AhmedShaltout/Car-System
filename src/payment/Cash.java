package payment;

public class Cash implements PaymentMethod {

    private static final long serialVersionUID = 1L;

    private float Balance;
    public Cash(){
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

    



}
