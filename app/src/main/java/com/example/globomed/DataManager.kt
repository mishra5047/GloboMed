package com.example.globomed

import com.example.globomed.GloboMedDBContract.EmployeeEntry

object DataManager {


    fun fetchAllEmployees(databaseHelper: DatabaseHelper) : ArrayList<Employee> {

        val  emp = ArrayList<Employee>()

        val db = databaseHelper.readableDatabase

        val columns = arrayOf(
            EmployeeEntry.COLUMN_ID,
            EmployeeEntry.COLUMN_NAME,
            EmployeeEntry.COLUMN_DOB,
            EmployeeEntry.COLUMN_DESIGNATION
        )

        //setting up the cursor
        val cursor =  db.query(EmployeeEntry.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null)

        val idPos = cursor.getColumnIndex(EmployeeEntry.COLUMN_ID)
        val namePos = cursor.getColumnIndex(EmployeeEntry.COLUMN_NAME)
        val dobPos = cursor.getColumnIndex(EmployeeEntry.COLUMN_DOB)
        val desiPos = cursor.getColumnIndex(EmployeeEntry.COLUMN_DESIGNATION)

        // fetching data using cursor
        while (cursor.moveToNext()){

            val id = cursor.getString(idPos)
            val name = cursor.getString(namePos)
            val dob = cursor.getLong(dobPos)
            val designation = cursor.getString(desiPos)

            emp.add(Employee(id, name, dob, designation))
        }

        //to free resources
        cursor.close()
        return emp
    }

}