package com.tm00nlight.chitasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

private const val POSITION = "UserNumber"

class UserFragment: Fragment() {
    private lateinit var user: User
    private val userViewModel: UserViewModel by lazy { ViewModelProvider(requireActivity()).get(UserViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = this.requireArguments().getInt(POSITION)
        user = userViewModel.users[position]

        view.findViewById<TextView>(R.id.nameView).text = "Name: " + user.name
        view.findViewById<TextView>(R.id.ageView).text = "Age: " + user.age.toString() + " y.o."
        view.findViewById<TextView>(R.id.studentView).text = "Student: " + user.isStudent.toString()

    }


    companion object {
        fun newInstance(i: Int) = UserFragment().apply {
            arguments = Bundle().apply { putInt(POSITION, i)}
        }
    }
}