package no.example.ormlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: martinmi
 * Date: 03.06.12
 * Time: 17.22
 * To change this template use File | Settings | File Templates.
 */
public class ORMLiteDB extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "demodatabase.db";
    //Database version
    private static final int DATABASE_VERSION = 2;

    //DAOs
    public Dao<Person, Integer> personDao;
    public Dao<BankAccount, Integer> bankAccountDao;

    public ORMLiteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        initDaos();
    }

    private void initDaos(){
        try {
            personDao = getDao(Person.class);
            bankAccountDao = getDao(BankAccount.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Person.class);
            TableUtils.createTable(connectionSource, BankAccount.class);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int i, int i1) {
        //Todo smart uppgrade script!
    }

    public void SaveListOfPersons(List<Person> personList) throws Exception {
        SavePersonsCallable callable = new SavePersonsCallable(personList);
        MyORMLiteDemoActivity.ormLiteDB.personDao.callBatchTasks(callable);
    }

    private class SavePersonsCallable implements Callable<Person> {

        public List<Person> personList;

        public SavePersonsCallable(List<Person> persons){
            this.personList = persons;
        }

        @Override
        public Person call() throws Exception {

            for(Person person : personList){
                MyORMLiteDemoActivity.ormLiteDB.personDao.create(person);
            }

            return null;
        }
    }
}
