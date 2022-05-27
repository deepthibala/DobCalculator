package com.example.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
   var selectedDate : TextView? = null
    private var ageInMinsTv : TextView? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDatePkr:Button =findViewById(R.id.date_picker_btn)
        ageInMinsTv = findViewById(R.id.age_mins_tv)
        selectedDate = findViewById(R.id.sel_date_tv)
        buttonDatePkr.setOnClickListener {
            clickDatePicker()
        }
    }

    fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd =      DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{
                    view, year, month, day ->

                val selecteDate = "$day/${month+1}/$year"
                selectedDate?.text = selecteDate

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val dat = sdf.parse(selecteDate)
                dat?.let {
                    val selectedDateInMins = dat.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val theCurrentDateInMins = currentDate.time / 60000
                    val differenceInMins = theCurrentDateInMins - selectedDateInMins
                    ageInMinsTv?.text = differenceInMins.toString()
                }
            },
     year,
        month,
        day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 8640000
            dpd.show()
        Toast.makeText(this,"selected year was $year, selected month was ${month+1}, selected day was $day", Toast.LENGTH_LONG).show()

    }
}