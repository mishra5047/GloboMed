package com.example.globomed

import android.provider.BaseColumns
import android.provider.BaseColumns._ID

object GloboMedDBContract {

    object EmployeeEntry : BaseColumns {
        const val TABLE_NAME = "employee"
        const val COLUMN_ID = _ID
        const val COLUMN_NAME = "name"
        const val COLUMN_DOB = "dob"
        const val COLUMN_DESIGNATION = "designation"


        const val SQL_CREATE_ENTERIES =
            "CREATE TABLE ${EmployeeEntry.TABLE_NAME}  (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${EmployeeEntry.COLUMN_NAME} TEXT NOT NULL, "+
                    "${EmployeeEntry.COLUMN_DOB} INTEGER NOT NULL, " +
                    "${EmployeeEntry.COLUMN_DESIGNATION} TEXT NOT NULL)"


        const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS ${EmployeeEntry.TABLE_NAME}"

    }
}