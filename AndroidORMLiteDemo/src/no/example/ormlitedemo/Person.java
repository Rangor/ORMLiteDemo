package no.example.ormlitedemo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created with IntelliJ IDEA.
 * User: martinmi
 * Date: 03.06.12
 * Time: 17.13
 * To change this template use File | Settings | File Templates.
 */
@DatabaseTable(tableName = "person_table")
public class Person {

    public static final String NAME_COLUMN = "name";

    @DatabaseField(generatedId = true)
    public int id;

    @DatabaseField(canBeNull = false)
    public String name;

    @DatabaseField
    public String adress;

    @DatabaseField(foreign = true)
    public BankAccount bankAccount;

}
