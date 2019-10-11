package com.haidela.payment.combination;

import com.haidela.payment.pay.paycustomer.domain.PayCustomer;
import com.haidela.payment.pay.payment.Payment;

/**
 * @author zhanglize
 * @create 2019/9/29
 */
public class PaymentCombination {

    private PayCustomer payCustomer;

    private Payment payment;

    public PayCustomer getPayCustomer() {
        return payCustomer;
    }

    public void setPayCustomer(PayCustomer payCustomer) {
        this.payCustomer = payCustomer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
