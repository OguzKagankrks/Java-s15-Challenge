package com.librarymanagement.management;
import com.librarymanagement.management.BorrowTransaction;


public class Invoice {

    private BorrowTransaction transaction;
    private double amount;
    private boolean isPaid;
    private boolean isRefunded;



    public Invoice(BorrowTransaction transaction, double amount){
        this.transaction = transaction;
        this.amount = amount;
        this.isPaid = false;
        this.isRefunded = false;
    }

    public BorrowTransaction getTransaction() {
        return transaction;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public boolean isRefunded() {
        return isRefunded;
    }

    public void pay(){
        if(isPaid)
            System.out.println("Invoice is already paid.");
        else {
            this.isPaid = true;
            System.out.println("Invoice paid successfully.");
        }
    }



    public void refund() {
        if (!isPaid) {
            System.out.println("Cannot refund. Invoice is not paid yet.");
            return;
        }
        if (isRefunded) {
            System.out.println("Invoice is already refunded.");
            return;
        }
        this.isRefunded = true;
        System.out.println("Invoice refunded successfully.");
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "transaction=" + transaction +
                ", amount=" + amount +
                ", isPaid=" + isPaid +
                ", isRefunded=" + isRefunded +
                '}';
    }

}
