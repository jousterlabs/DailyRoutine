package com.jousterlabs.dailyroutine.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DailyroutineDataBaseClass {

	SQLiteDatabase sqLiteDatabase;
	Context context;
	String DATABASE_NAME = "dailyRoutineDataBase";
	int DB_VIRSION_NAME = 1;
	DailyRoutineSqliteOpenHelper dailyRoutineSqliteOpenHelper;

	public DailyroutineDataBaseClass(Context mContext) {
		this.context = mContext;
		dailyRoutineSqliteOpenHelper = new DailyRoutineSqliteOpenHelper(
				context, DATABASE_NAME, null, DB_VIRSION_NAME);
	}

	// ---DataBase Write---
	public void db_Write() {
		sqLiteDatabase = dailyRoutineSqliteOpenHelper.getWritableDatabase();
	}

	// ---DataBase Read---
	public void db_Read() {
		sqLiteDatabase = dailyRoutineSqliteOpenHelper.getReadableDatabase();
	}

	// ---Start Insert Data into DataBase---
	public void db_Insert_dailyRoutine_addToList(ContentValues contentValues) {
		sqLiteDatabase.insert("dailyRoutine_Table", null, contentValues);
	}

	// ---End Insert Data into DataBase---

	// ---Start Delete DataBase---
	public void db_Delete_dailyRoutine_deleteData() {
		sqLiteDatabase.delete("dailyRoutine_Table", null, null);
	}

	// ---End Delete DataBase---

	// ---Start Fatch Data From DataBase---
	public Cursor db_fatch_dailyRoutine(String string) {
		Cursor cursor = sqLiteDatabase.query("dailyRoutine_Table", null,
				"work_listStatus=?", new String[] { string }, null, null, null);
		return cursor;

	}

	// ---End Fatch Data From DataBase---

	// ---Start Fatch Checked Data---
	public Cursor db_fatechSpecficItem_dailyRoutineCheckedItem(String string) {
		Cursor cursor = sqLiteDatabase.query("dailyRoutine_Table", null,
				"work_listCheckedStatus=?", new String[] { string }, null,
				null, null);
		return cursor;

	}

	// ---End Fatch Checked Data---
	// ---Start Update Data in to Table---
	public void db_update_dailyRoutineCheckedItemStatus(
			ContentValues contentValues, String string) {
		sqLiteDatabase.update("dailyRoutine_Table", contentValues,
				"work_timeStamp=?", new String[] { string });
	}

	// ---Start Update Data in to Table---
	public void db_update_dailyRoutineDeleteItems(ContentValues contentValues,
			String string) {
		sqLiteDatabase.update("dailyRoutine_Table", contentValues,
				"work_timeStamp=?", new String[] { string });
	}

	// ---End Update Data in to Table---

	// ---Start Delete Specfic rom from Database---
	public void db_delete_dailyRoutine_row(String string) {
		sqLiteDatabase.delete("dailyRoutine_Table", "work_timeStamp=?",
				new String[] { string });
	}

	// ---End Delete Specfic row from Database---

	// ---Start Delete History from Database---
	public void db_delete_dailyRoutine_history(String string) {
		sqLiteDatabase.delete("dailyRoutine_Table", "work_timeStamp=?", new String[]{string});
	}

	// ---End Delete History from Database---

	public class DailyRoutineSqliteOpenHelper extends SQLiteOpenHelper {

		public DailyRoutineSqliteOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE dailyRoutine_Table(id integer primary key,imei_number text,work_name text,work_date text,work_time text,work_place text,work_remark text,work_listStatus text,work_listCheckedStatus text,work_timeStamp text)");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}

	}

}
