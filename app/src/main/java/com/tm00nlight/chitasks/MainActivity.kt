package com.tm00nlight.chitasks

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activity: MainActivity = this

        updateUI(this)
    }

    private fun updateUI(activity: MainActivity) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = UserAdapter(activity, userViewModel.users)
        recyclerView.adapter = adapter
    }

    override fun onPause() {
        super.onPause()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        CoroutineScope(Dispatchers.IO).launch {
            userViewModel.clearDB()
            for (i in 0..recyclerView.childCount - 1) {
                userViewModel.users[i].isStudent =
                    recyclerView.get(i).findViewById<SwitchMaterial>(R.id.studentSwitcher).isChecked
                userViewModel.saveUser(userViewModel.users[i])
            }
            Log.d("initial rotate", userViewModel.users.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_user, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.new_user -> {
                val currentFragment = supportFragmentManager
                    .findFragmentById(R.id.fragment_container)
                if (currentFragment == null) {
                    supportFragmentManager
                        .beginTransaction()
                        .add(R.id.fragment_container, NewUserFragment())
                        .addToBackStack(null)
                        .commit()
                } else {
                    supportFragmentManager
                        .beginTransaction()
                        .remove(currentFragment)
                        .commit()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, NewUserFragment())
                        .addToBackStack(null)
                        .commit()
                }
                true
            }
        else -> return super.onOptionsItemSelected(item)
        }
    }

    private class UserHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameField = view.findViewById<TextView>(R.id.nameField)
        val ageField = view.findViewById<TextView>(R.id.ageField)
        val switcher = view.findViewById<SwitchMaterial>(R.id.studentSwitcher)

    }

    private class UserAdapter(val activity: MainActivity, var users: List<User>) : RecyclerView.Adapter<UserHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item, parent, false)
            return UserHolder(view)
        }

        override fun onBindViewHolder(holder: UserHolder, position: Int) {
            val _user = users[position]
            holder.apply {
                nameField.text = _user.name
                ageField.text = _user.age.toString() + " y.o."
                switcher.isChecked = _user.isStudent
            }
            holder.itemView.setOnClickListener {
                val currentFragment = activity.supportFragmentManager
                    .findFragmentById(R.id.fragment_container)
                if (currentFragment == null) {
                    activity.supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, UserFragment.newInstance(position))
                    .addToBackStack(null)
                    .commit()
                } else {
                    activity.supportFragmentManager
                        .beginTransaction()
                        .remove(currentFragment)
                        .commit()
                    activity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, UserFragment.newInstance(position))
                        .addToBackStack(null)
                        .commit()
                }
            }
        }

        override fun getItemCount() = users.size

    }
}


