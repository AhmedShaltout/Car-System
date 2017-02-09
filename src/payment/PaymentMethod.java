package payment;

import java.io.Serializable;

public interface PaymentMethod extends Serializable{

    public float getBalance();

    public void takeMoney(float pricePerH);

    public void addBalance(float f);

}
