// SolvedProblemsFragment.kt
package com.ritika.taskapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ritika.taskapi.dataclasses.SolvedResponse

import kotlinx.coroutines.launch

class Solved : Fragment() {

    private lateinit var totalSolvedTextView: TextView
    private lateinit var solvedProblemsRecyclerView: RecyclerView
    private lateinit var solvedProblemsAdapter: SolvedProblemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_solved, container, false)
    }
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        totalSolvedTextView = view.findViewById(R.id.totalSolvedTextView)
        solvedProblemsRecyclerView = view.findViewById(R.id.solvedProblemsRecyclerView)
        solvedProblemsRecyclerView.layoutManager = LinearLayoutManager(context)
        sharedViewModel.username.observe(viewLifecycleOwner) { username ->
            val userName=username.toString()

            fetchSolvedProblems(userName)

        }

    }

    private fun fetchSolvedProblems(username: String) {
        lifecycleScope.launch {
            try {
                val response: SolvedResponse = retrofit.api.getSolvedProblems(username)
                displaySolvedProblems(response)
            } catch (e: Exception) {
                totalSolvedTextView.text = "$e"
            }
        }
    }

    private fun displaySolvedProblems(response: SolvedResponse) {
        if (response.problems.isNullOrEmpty()) {
            totalSolvedTextView.text = "No problems solved."
            return
        }
        // Display total solved problems and set up the adapter
        totalSolvedTextView.text = "Total Solved Problems: ${response.totalSolved}"
        solvedProblemsAdapter = SolvedProblemsAdapter(response.problems)
        solvedProblemsRecyclerView.adapter = solvedProblemsAdapter
    }
}
