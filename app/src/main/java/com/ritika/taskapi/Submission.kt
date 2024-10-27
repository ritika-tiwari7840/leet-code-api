package com.ritika.taskapi
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class Submission : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var submissionAdapter: SubmissionAdapter

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_submission, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        sharedViewModel.username.observe(viewLifecycleOwner) { username ->
            fetchSubmissions(username)
        }
        return view
    }

    private fun fetchSubmissions(username: String) {
        lifecycleScope.launch {
            try {
                val response = retrofit.api.getSubmission(username)

                val submissions = response.submission

                submissionAdapter = SubmissionAdapter(submissions)
                recyclerView.adapter = submissionAdapter

            }catch (e: Exception) {
                Log.e("fetchSubmission", "fetchSubmissions: $e", )
            }
        }
    }
}