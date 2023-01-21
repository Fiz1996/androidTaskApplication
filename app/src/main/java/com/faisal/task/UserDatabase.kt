package com.faisal.task


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class UserDatabase(
    context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val taskDatabase:String = "CREATE TABLE $TABLE_NAME " +
                "($COLUMN_ID INTEGER PRIMARY KEY, " +
                "$IS_COMPLETED BOOLEAN,"+
                "$PHONE_NUMBER TEXT," +
                "$COLUMN_NAME TEXT)"

        val userDatabase: String = "CREATE TABLE  $USER_TABLE_NAME " +
                "($USER_ID INTEGER PRIMARY KEY, " +
                "$USER_EMAIL TEXT, " +
                "$USER_PHONE TEXT, " +
                "$USER_NAME TEXT)"



        db.execSQL(userDatabase)
        db.execSQL(taskDatabase)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        val dropTable1 = "DROP TABLE IF EXISTS $USER_TABLE_NAME"

        db.execSQL(dropTable)
        db.execSQL(dropTable1)
        onCreate(db)
    }

    fun insertTask(name: String,isCompleted:Boolean,phoneNumber: String?) {
        val db = writableDatabase
        val values = ContentValues()

        values.put(COLUMN_NAME, name)
        values.put(IS_COMPLETED , isCompleted)
        values.put(PHONE_NUMBER,phoneNumber)
        db.insert(TABLE_NAME, null, values)
    }

    fun insertUser(name:String,phone:String,email: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(USER_NAME,name)
        values.put(USER_PHONE,phone)
        values.put(USER_EMAIL,email)
        db.insert(USER_TABLE_NAME,null,values)
    }

    fun getTask(name: String): TaskModel? {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_NAME = '$name'"
        val cursor = db.rawQuery(query, null)
        var taskInfo: TaskModel? = null

        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))

            taskInfo = TaskModel(id = id, name = name)
        }

        return taskInfo
    }

    fun getTasks(phoneNumber: String?): List<TaskModel> {
        val db = readableDatabase
        val tasks = arrayListOf<TaskModel>()
        val query = "SELECT * FROM $TABLE_NAME "+
                "INNER JOIN $USER_TABLE_NAME ON $TABLE_NAME.$PHONE_NUMBER=$USER_TABLE_NAME.$USER_PHONE WHERE $USER_PHONE= $phoneNumber"

        try {
            val cursor = db.rawQuery(query, null)

            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))

                val userModel = TaskModel(id = id, name = name)

                tasks.add(userModel)
            }

        } catch (exception: SQLiteException) {
            Log.d("exception", "getUsers: ${exception.message}")
        }

        return tasks
    }

    fun checkPhoneNumber(phoneNumber:String): Int {
        val db = readableDatabase
        val query = "SELECT * FROM $USER_TABLE_NAME WHERE $USER_PHONE = '$phoneNumber'"
        var res = 0
        try {
            val cursor = db.rawQuery(query, null)
            if (cursor.moveToFirst()) {
                res = cursor.getColumnIndex(USER_PHONE)
                return res
            }
        }
            catch (exception: SQLiteException) {
                Log.d("exception", "getUsers: ${exception.message}")
            }

        return res
    }

    fun changeTask(newName: String, oldName: String) {
        val db = writableDatabase
        val values = ContentValues()
        val where = "$COLUMN_NAME = '$oldName'"

        values.put(COLUMN_NAME, newName)
        db.update(TABLE_NAME, values, where, null)
    }

    fun removeTask(id: Int) {
        val db = writableDatabase
        val where = "$COLUMN_ID = $id"

        db.delete(TABLE_NAME, where, null)
    }

    companion object {
        // Database Info
        const val DATABASE_NAME = "task_database_testing"
        const val DATABASE_VERSION = 1

        // task Table Info
        const val TABLE_NAME = "task_table"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "task_name"
        const val IS_COMPLETED= "is_completed"
        const val PHONE_NUMBER = "fk_phone_number"


        // user Table Info
        const val USER_TABLE_NAME = "user_table"
        const val USER_ID = "id"
        const val USER_NAME = "name"
        const val USER_EMAIL = "email"
        const val USER_PHONE = "phone"


    }



}