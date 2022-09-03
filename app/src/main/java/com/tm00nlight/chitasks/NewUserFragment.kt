package com.tm00nlight.chitasks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class NewUserFragment: Fragment() {
    private val userViewModel: UserViewModel
        by lazy { ViewModelProvider(requireActivity()).get(UserViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameView = view.findViewById<EditText>(R.id.nameEdit)
        val datePicker = view.findViewById<DatePicker>(R.id.datePicker)
        val ageView = view.findViewById<TextView>(R.id.ageEdit)
        val addUserButton = view.findViewById<Button>(R.id.addUserButton)

        datePicker.setOnDateChangedListener { datePicker, year, month, day ->
            ageView.text = getAge(year, month, day).toString()
        }

        addUserButton.setOnClickListener {
            if (nameView.text.toString() != "" && ageView.text.toString().toInt() > 0) {
                CoroutineScope(Dispatchers.IO).launch {
                    userViewModel.createUser(
                        nameView.text.toString(),
                        ageView.text.toString().toInt()
                    )
                    Log.i("NEW USER", userViewModel.getUsers().value.toString())
                }
                Toast.makeText(context, "User added successfully", Toast.LENGTH_LONG).show()
                val currentFragment = requireActivity().supportFragmentManager
                    .findFragmentById(R.id.fragment_container)
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .remove(currentFragment!!)
                    .commit()
                //updateUI()
            }
            else {
                if(nameView.text.toString() == "") Toast.makeText(context, "Enter a correct name!", Toast.LENGTH_LONG).show()
                else Toast.makeText(context, "Choose a correct date of birth!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
        }
    }

    fun getAge(year: Int, month: Int, day: Int): Int {
        val todayYear = Calendar.getInstance().get(Calendar.YEAR)
        val todayMonth = Calendar.getInstance().get(Calendar.MONTH)
        val todayDayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        if (year < todayYear) {
            if (month > todayMonth || (month == todayMonth && day > todayDayOfMonth)) return todayYear - year - 1
            else return todayYear - year
        }
        else return 0
    }
}