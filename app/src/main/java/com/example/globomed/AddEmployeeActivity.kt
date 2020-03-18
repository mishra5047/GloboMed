package com.globomed.learn

import android.app.Activity
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Toast
import com.example.globomed.DatabaseHelper
import com.example.globomed.GloboMedDBContract
import com.example.globomed.R
import kotlinx.android.synthetic.main.activity_add.*
import java.text.SimpleDateFormat
import java.util.*

class AddEmployeeActivity : Activity() {

    private val myCalendar = Calendar.getInstance()
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        databaseHelper = DatabaseHelper(this)


        // on clicking ok on the calender dialog
        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            etDOB.setText(getFormattedDate(myCalendar.timeInMillis))
        }

        etDOB.setOnClickListener {
            setUpCalender(date)
        }

        bCancel.setOnClickListener {

        }

        bSave.setOnClickListener {
            saveEmployee()
        }

    }

    private fun saveEmployee() {

        // checking if the data is not null
        var isValid = true

      etEmpName.error = if (etEmpName?.text.toString().isEmpty()) {
            isValid = false
          "Required Field"
    } else null

        etDesignation.error = if (etDesignation?.text.toString().isEmpty()) {
            isValid = false
            "Required Field"
        } else null

        if(isValid) {
            val name: String = etEmpName?.text.toString()
            val designation: String = etDesignation?.text.toString()
            val dob: Long = myCalendar.timeInMillis

            val db:SQLiteDatabase = databaseHelper.writableDatabase

            val values = ContentValues()
            values.put(GloboMedDBContract.EmployeeEntry.COLUMN_NAME, name)
            values.put(GloboMedDBContract.EmployeeEntry.COLUMN_DESIGNATION, designation)
            values.put(GloboMedDBContract.EmployeeEntry.COLUMN_DOB, dob)

            val result:Long = db.insert(GloboMedDBContract.EmployeeEntry.TABLE_NAME, null , values)

            setResult(RESULT_OK, Intent())

            Toast.makeText(applicationContext, "employee added", Toast.LENGTH_LONG).show()
        }
        finish()
    }


    private fun setUpCalender(date: DatePickerDialog.OnDateSetListener) {

        DatePickerDialog(
            this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun getFormattedDate(dobInMilis: Long?): String {

        return dobInMilis?.let {
            val sdf = SimpleDateFormat("d MMM, yyyy", Locale.getDefault())
            sdf.format(dobInMilis)
        } ?: "Not Found"
    }
}