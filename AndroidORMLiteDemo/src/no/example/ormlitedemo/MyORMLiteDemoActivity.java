package no.example.ormlitedemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;

import java.sql.SQLException;
import java.util.List;

public class MyORMLiteDemoActivity extends Activity
{

    public static ORMLiteDB ormLiteDB;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ormLiteDB = new ORMLiteDB(this);

    }

    private void CreatePerson(String name, String adress) throws SQLException {
        Person martin = new Person();
        martin.name = name;
        martin.adress = adress;

        ormLiteDB.personDao.create(martin);
    }

    private void FetchPersons() throws SQLException{
        List<Person> martin = ormLiteDB.personDao.queryForAll();
    }

    private void FetchPerson(String name) throws SQLException {
        Person person = ormLiteDB.personDao.queryForEq(Person.NAME_COLUMN, name).get(0);
        Toast.makeText(this, "Fetched Person Id: " + person.id + " Name: " + person.name +" Adress: " + person.adress, Toast.LENGTH_LONG).show();
    }

    public void createPersonClick(View target){
        try {
            CreatePerson("Martin", "Adresseveien 12");
            Toast.makeText(this, "CreatedPerson", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public void fetchPersonClick(View target){
        try {
            FetchPerson("Martin");
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void AddNewBankAccountToPerson(Person person) throws SQLException {
        //Create new account
        BankAccount bankAccount = new BankAccount();
        bankAccount.accountNumber = 123456;
        bankAccount.balance = 100;
        ormLiteDB.bankAccountDao.create(bankAccount);

        //Fetch from DB to get ID
        bankAccount = ormLiteDB.bankAccountDao.queryForMatching(bankAccount).get(0);

        //Set and update account to person object
        person.bankAccount = bankAccount;
        ormLiteDB.personDao.update(person);
    }

}
