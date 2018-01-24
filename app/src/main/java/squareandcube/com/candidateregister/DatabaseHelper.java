package squareandcube.com.candidateregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "squareandcube.db";



    // User table name
    private static final String TABLE_CANDIDATE = "candidate_personal_details";
    private static final String TABLE_CANDIDATE_EDUCATION = "candidate_educational_details";


    // User Table Columns names
    private static final String COLUMN_CANDIDATE_FIRST_NAME = "candidate_firstname";
    private static final String COLUMN_CANDIDATE_LAST_NAME = "candidate_lastname";
    private static final String COLUMN_CANDIDATE_EMAIL_ID = "candidate_email_id";
    private static final String COLUMN_CANDIDATE_MOBILE_NUMBER = "candidate_mobile_number";
    private static final String COLUMN_CANDIDATE_DATE_OF_BIRTH = "candidate_date_of_birth";
    private static final String COLUMN_CANDIDATE_GENDER = "candidate_gender";
    private static final String COLUMN_CANDIDATE_PASSWORD = "candidate_password";

    //Educational Table of Candidate
    private static final String COLUMN_CANDIDATE_SECONDARY_TOTAL_NUMBER = "candidate_secondary_total_number";
    private static final String COLUMN_CANDIDATE_SECONDARY_INSTITUTION_NAME = "candidate_secondary_institution_name";
    private static final String COLUMN_CANDIDATE_SECONDARY_UNIVERSITY_NAME = "candidate_secondary_university_name";
    private static final String COLUMN_CANDIDATE_SECONDARY_YEAR_OF_PASSING = "candidate_secondary_year_of_passing";
    private static final String COLUMN_CANDIDATE_HIGHER_SECONDARY_TOTAL_NUMBER = "candidate_higher_secondary_total_number";
    private static final String COLUMN_CANDIDATE_HIGHER_SECONDARY_INSTITUTION_NAME = "candidate_higher_secondary_institution_name";
    private static final String COLUMN_CANDIDATE_HIGHER_SECONDARY_UNIVERSITY_NAME = "candidate_higher_secondary_university_name";
    private static final String COLUMN_CANDIDATE_HIGHER_SECONDARY_YEAR_OF_PASSING = "candidate_higher_secondary_year_of_passing";
    private static final String COLUMN_CANDIDATE_GRADUATION_TOTAL_NUMBER = "candidate_graduation_total_number";
    private static final String COLUMN_CANDIDATE_GRADUATION_INSTITUTION_NAME = "candidate_graduation_institution_name";
    private static final String COLUMN_CANDIDATE_GRADUATION_UNIVERSITY_NAME = "candidate_graduation_university_name";
    private static final String COLUMN_CANDIDATE_GRADUATION_YEAR_OF_PASSING = "candidate_graduation_year_of_passing";
    private static final String COLUMN_CANDIDATE_POST_GRADUATION_TOTAL_NUMBER = "candidate_post_graduation_total_number";
    private static final String COLUMN_CANDIDATE_POST_GRADUATION_INSTITUTION_NAME = "candidate_post_graduation_institution_name";
    private static final String COLUMN_CANDIDATE_POST_GRADUATION_UNIVERSITY_NAME = "candidate_post_graduation_university_name";
    private static final String COLUMN_CANDIDATE_POST_GRADUATION_YEAR_OF_PASSING = "candidate_post_graduation_year_of_passing";
    private static final String COLUMN_CANDIDATE_YEAR_GAP = "candidate_year_gap";
    private static final String COLUMN_CANDIDATE_AVERAGE_MARKS= "candidate_average_marks";

    // create table sql query
    private String CREATE_CANDIDATE_TABLE = "CREATE TABLE " + TABLE_CANDIDATE + "("
            + COLUMN_CANDIDATE_FIRST_NAME + " TEXT," +
            COLUMN_CANDIDATE_LAST_NAME + " TEXT," + COLUMN_CANDIDATE_EMAIL_ID + " TEXT PRIMARY KEY," + COLUMN_CANDIDATE_MOBILE_NUMBER + " TEXT,"
            + COLUMN_CANDIDATE_DATE_OF_BIRTH + " TEXT," + COLUMN_CANDIDATE_GENDER + " TEXT, "
            + COLUMN_CANDIDATE_PASSWORD + " TEXT" + ")";


    private String CREATE_CANDIDATE_EDUCATION_TABLE = "CREATE TABLE " + TABLE_CANDIDATE_EDUCATION + "("+
            COLUMN_CANDIDATE_EMAIL_ID + " TEXT," +
            COLUMN_CANDIDATE_SECONDARY_TOTAL_NUMBER +" TEXT,"+
            COLUMN_CANDIDATE_SECONDARY_INSTITUTION_NAME +" TEXT,"+
            COLUMN_CANDIDATE_SECONDARY_UNIVERSITY_NAME +" TEXT,"+
            COLUMN_CANDIDATE_SECONDARY_YEAR_OF_PASSING +" TEXT,"+
            COLUMN_CANDIDATE_HIGHER_SECONDARY_TOTAL_NUMBER+" TEXT,"+
            COLUMN_CANDIDATE_HIGHER_SECONDARY_INSTITUTION_NAME+" TEXT,"+
            COLUMN_CANDIDATE_HIGHER_SECONDARY_UNIVERSITY_NAME+" TEXT,"+
            COLUMN_CANDIDATE_HIGHER_SECONDARY_YEAR_OF_PASSING+" TEXT,"+
            COLUMN_CANDIDATE_GRADUATION_TOTAL_NUMBER+" TEXT,"+
            COLUMN_CANDIDATE_GRADUATION_INSTITUTION_NAME+" TEXT,"+
            COLUMN_CANDIDATE_GRADUATION_UNIVERSITY_NAME+" TEXT,"+
            COLUMN_CANDIDATE_GRADUATION_YEAR_OF_PASSING+" TEXT,"+
            COLUMN_CANDIDATE_POST_GRADUATION_TOTAL_NUMBER+" TEXT,"+
            COLUMN_CANDIDATE_POST_GRADUATION_INSTITUTION_NAME+" TEXT,"+
            COLUMN_CANDIDATE_POST_GRADUATION_UNIVERSITY_NAME+" TEXT,"+
            COLUMN_CANDIDATE_POST_GRADUATION_YEAR_OF_PASSING+" TEXT,"+
            COLUMN_CANDIDATE_YEAR_GAP+" TEXT,"+
            COLUMN_CANDIDATE_AVERAGE_MARKS+" TEXT,"+
            " FOREIGN KEY(" + COLUMN_CANDIDATE_EMAIL_ID + ") REFERENCES "
            + TABLE_CANDIDATE + "(candidate_email_id)"+")";



    // drop table sql query
    private String DROP_CANDIDATE_TABLE = "DROP TABLE IF EXISTS " + TABLE_CANDIDATE;

    private String DROP_CANDIDATE_EDUCATION = "DROP TABLE IF EXISTS "+ TABLE_CANDIDATE_EDUCATION;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CANDIDATE_TABLE);
        db.execSQL(CREATE_CANDIDATE_EDUCATION_TABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys='ON';");
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_CANDIDATE_TABLE);
        db.execSQL(DROP_CANDIDATE_EDUCATION);

        // Create tables again
        onCreate(db);

    }


    /**
     * This method is to create user record
     *
     * @param candidateDataModel
     */
    public void addUser(CandidateDataModel candidateDataModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CANDIDATE_FIRST_NAME, candidateDataModel.getFirstName());
        values.put(COLUMN_CANDIDATE_LAST_NAME, candidateDataModel.getLastName());
        values.put(COLUMN_CANDIDATE_EMAIL_ID, candidateDataModel.getEmailId());
        values.put(COLUMN_CANDIDATE_MOBILE_NUMBER, candidateDataModel.getMobileNumber());
        values.put(COLUMN_CANDIDATE_DATE_OF_BIRTH, candidateDataModel.getDateOfBirth());
        values.put(COLUMN_CANDIDATE_GENDER, candidateDataModel.getGender());
        values.put(COLUMN_CANDIDATE_PASSWORD, candidateDataModel.getPassword());

        // Inserting Row
        db.insert(TABLE_CANDIDATE, null, values);
        db.close();
    }


    public void addUserEducationDetails(CandidateDataModel candidateDataModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CANDIDATE_EMAIL_ID,candidateDataModel.getEmailId());
        values.put(COLUMN_CANDIDATE_SECONDARY_TOTAL_NUMBER,candidateDataModel.getSecondaryTotalnumber());
        values.put(COLUMN_CANDIDATE_SECONDARY_INSTITUTION_NAME,candidateDataModel.getSecondaryInstitutionName());
        values.put(COLUMN_CANDIDATE_SECONDARY_UNIVERSITY_NAME,candidateDataModel.getHigherSecondaryUniversityName());
        values.put(COLUMN_CANDIDATE_SECONDARY_YEAR_OF_PASSING,candidateDataModel.getSecondaryYOP());

        values.put(COLUMN_CANDIDATE_HIGHER_SECONDARY_TOTAL_NUMBER,candidateDataModel.getHigherSecondaryTotalNumber());
        values.put(COLUMN_CANDIDATE_HIGHER_SECONDARY_INSTITUTION_NAME,candidateDataModel.getHigherSecondaryInstitutionName());
        values.put(COLUMN_CANDIDATE_HIGHER_SECONDARY_UNIVERSITY_NAME,candidateDataModel.getHigherSecondaryUniversityName());
        values.put(COLUMN_CANDIDATE_HIGHER_SECONDARY_YEAR_OF_PASSING,candidateDataModel.getHigherSecondaryYOP());

        values.put(COLUMN_CANDIDATE_GRADUATION_TOTAL_NUMBER,candidateDataModel.getGraduationTotalNumber());
        values.put(COLUMN_CANDIDATE_GRADUATION_UNIVERSITY_NAME,candidateDataModel.getGraduationUniversityName());
        values.put(COLUMN_CANDIDATE_GRADUATION_INSTITUTION_NAME,candidateDataModel.getGraduationInstitutionName());
        values.put(COLUMN_CANDIDATE_GRADUATION_YEAR_OF_PASSING,candidateDataModel.getGraduationYOP());

        values.put(COLUMN_CANDIDATE_POST_GRADUATION_TOTAL_NUMBER,candidateDataModel.getPostGraduationTotalNumber());
        values.put(COLUMN_CANDIDATE_POST_GRADUATION_UNIVERSITY_NAME,candidateDataModel.getPostGraduationUniversityName());
        values.put(COLUMN_CANDIDATE_POST_GRADUATION_INSTITUTION_NAME,candidateDataModel.getPostGraduationInstitutionName());
        values.put(COLUMN_CANDIDATE_POST_GRADUATION_YEAR_OF_PASSING,candidateDataModel.getPostGraduationYOP());

        values.put(COLUMN_CANDIDATE_YEAR_GAP,candidateDataModel.getYearGap());
        values.put(COLUMN_CANDIDATE_AVERAGE_MARKS,candidateDataModel.getAvg());

        db.insert(TABLE_CANDIDATE_EDUCATION, null, values);
        db.close();
    }


    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */

    public List<CandidateDataModel> getAllUser() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_CANDIDATE + " , " + TABLE_CANDIDATE_EDUCATION + " WHERE "
                + TABLE_CANDIDATE + "." + COLUMN_CANDIDATE_EMAIL_ID + " = " + TABLE_CANDIDATE_EDUCATION + "."
                + COLUMN_CANDIDATE_EMAIL_ID, null);


        List<CandidateDataModel> candidateList = new ArrayList<CandidateDataModel>();

        if (c.moveToFirst()) {
            do {
                CandidateDataModel candidateDataModel = new CandidateDataModel();
                candidateDataModel.setFirstName(c.getString(0));
                candidateDataModel.setLastName(c.getString(1));
                candidateDataModel.setEmailId(c.getString(2));
                candidateDataModel.setMobileNumber(c.getString(3));
                candidateDataModel.setAvg(c.getString(25));
                candidateList.add(candidateDataModel);
            }while (c.moveToNext()) ;
        }
        c.close();
        db.close();

        // return user list
        return candidateList;
    }


    /**
     * This method to update user record
     *
     * @param candiadateDataModel
     */
    public void updateUser(CandidateDataModel candiadateDataModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CANDIDATE_FIRST_NAME, candiadateDataModel.getFirstName());
        values.put(COLUMN_CANDIDATE_LAST_NAME, candiadateDataModel.getLastName());
        values.put(COLUMN_CANDIDATE_EMAIL_ID, candiadateDataModel.getEmailId());
        values.put(COLUMN_CANDIDATE_MOBILE_NUMBER, candiadateDataModel.getMobileNumber());
        values.put(COLUMN_CANDIDATE_DATE_OF_BIRTH, candiadateDataModel.getDateOfBirth());
        values.put(COLUMN_CANDIDATE_GENDER, candiadateDataModel.getGender());
        values.put(COLUMN_CANDIDATE_PASSWORD, candiadateDataModel.getPassword());

        // updating row
        db.update(TABLE_CANDIDATE, values, COLUMN_CANDIDATE_EMAIL_ID + " = ?",
                new String[]{String.valueOf(candiadateDataModel.getEmailId())});
        db.close();
    }

    public void updateCandidateEducationalDetails(CandidateDataModel candidateDataModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CANDIDATE_SECONDARY_TOTAL_NUMBER,candidateDataModel.getSecondaryTotalnumber());
        values.put(COLUMN_CANDIDATE_SECONDARY_INSTITUTION_NAME,candidateDataModel.getSecondaryInstitutionName());
        values.put(COLUMN_CANDIDATE_SECONDARY_UNIVERSITY_NAME,candidateDataModel.getSecondaryUniversityName());
        values.put(COLUMN_CANDIDATE_SECONDARY_YEAR_OF_PASSING,candidateDataModel.getSecondaryYOP());

        values.put(COLUMN_CANDIDATE_HIGHER_SECONDARY_TOTAL_NUMBER,candidateDataModel.getHigherSecondaryTotalNumber());
        values.put(COLUMN_CANDIDATE_HIGHER_SECONDARY_INSTITUTION_NAME,candidateDataModel.getHigherSecondaryInstitutionName());
        values.put(COLUMN_CANDIDATE_HIGHER_SECONDARY_UNIVERSITY_NAME,candidateDataModel.getHigherSecondaryUniversityName());
        values.put(COLUMN_CANDIDATE_HIGHER_SECONDARY_YEAR_OF_PASSING,candidateDataModel.getHigherSecondaryYOP());

        values.put(COLUMN_CANDIDATE_GRADUATION_TOTAL_NUMBER,candidateDataModel.getGraduationTotalNumber());
        values.put(COLUMN_CANDIDATE_GRADUATION_UNIVERSITY_NAME,candidateDataModel.getGraduationUniversityName());
        values.put(COLUMN_CANDIDATE_GRADUATION_INSTITUTION_NAME,candidateDataModel.getGraduationInstitutionName());
        values.put(COLUMN_CANDIDATE_GRADUATION_YEAR_OF_PASSING,candidateDataModel.getGraduationYOP());

        values.put(COLUMN_CANDIDATE_POST_GRADUATION_TOTAL_NUMBER,candidateDataModel.getPostGraduationTotalNumber());
        values.put(COLUMN_CANDIDATE_POST_GRADUATION_UNIVERSITY_NAME,candidateDataModel.getPostGraduationUniversityName());
        values.put(COLUMN_CANDIDATE_POST_GRADUATION_INSTITUTION_NAME,candidateDataModel.getPostGraduationInstitutionName());
        values.put(COLUMN_CANDIDATE_POST_GRADUATION_YEAR_OF_PASSING,candidateDataModel.getPostGraduationYOP());

        values.put(COLUMN_CANDIDATE_YEAR_GAP,candidateDataModel.getYearGap());
        values.put(COLUMN_CANDIDATE_AVERAGE_MARKS,candidateDataModel.getAvg());

        db.update(TABLE_CANDIDATE_EDUCATION, values, /*COLUMN_CANDIDATE_ID*/COLUMN_CANDIDATE_EMAIL_ID + " = ?",
                new String[]{String.valueOf(candidateDataModel.getEmailId())});
        db.close();
    }


    /**
     * This method is to delete user record
     *
     * @param candidateDataModel
     */
    public void deleteUser(CandidateDataModel candidateDataModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_CANDIDATE,COLUMN_CANDIDATE_EMAIL_ID + " = ?",
                new String[]{String.valueOf(candidateDataModel.getId())});
        db.close();
    }


    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_CANDIDATE_EMAIL_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_CANDIDATE_EMAIL_ID + " = ?";

        // selection argument
        String[] selectionArgs = {email};


        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_CANDIDATE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
               // COLUMN_CANDIDATE_ID
                COLUMN_CANDIDATE_EMAIL_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_CANDIDATE_EMAIL_ID + " = ?" + " AND " + COLUMN_CANDIDATE_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};


        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_CANDIDATE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }



        return false;
    }
}
