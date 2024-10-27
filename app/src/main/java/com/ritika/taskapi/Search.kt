package com.ritika.taskapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation

class Search : Fragment() {

    private lateinit var navController: NavController

    // Get a reference to SharedViewModel shared across fragments in the activity
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        Log.i("Here the navController", "$navController")

        val navOptions = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setRestoreState(true)
            .setPopUpTo(R.id.nav_graph, true)
            .build()

        val submitBtn = view.findViewById<Button>(R.id.search_button)
        val userText = view.findViewById<EditText>(R.id.user_name)

        submitBtn.setOnClickListener {
            val username = userText.text.toString()

            // Set username in SharedViewModel instead of using a Bundle
            sharedViewModel.setUsername(username)

            // Navigate to the Profile fragment without a Bundle
            navController.navigate(R.id.profile, null, navOptions)
        }
    }
}
