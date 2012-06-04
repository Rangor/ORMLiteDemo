package no.example.ormlitedemo;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created with IntelliJ IDEA.
 * User: martinmi
 * Date: 03.06.12
 * Time: 20.47
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount {
    @DatabaseField(generatedId = true)
    public int id;

    @DatabaseField(canBeNull = false)
    public int accountNumber;

    @DatabaseField
    public double balance;
}
