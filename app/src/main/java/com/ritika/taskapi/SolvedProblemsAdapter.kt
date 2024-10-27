package com.ritika.taskapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ritika.taskapi.dataclasses.SolvedProblem

class SolvedProblemsAdapter(problems: List<SolvedProblem>) : RecyclerView.Adapter<SolvedProblemsAdapter.SolvedProblemViewHolder>() {

    private var problems: List<SolvedProblem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolvedProblemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_solved_problem, parent, false)
        return SolvedProblemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SolvedProblemViewHolder, position: Int) {
        holder.bind(problems[position])
    }

    override fun getItemCount(): Int {
        return problems.size
    }

    fun setProblems(newProblems: List<SolvedProblem>) {
        problems = newProblems
        notifyDataSetChanged()
    }

    class SolvedProblemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.problemTitleTextView)
        private val difficultyTextView: TextView = itemView.findViewById(R.id.problemDifficultyTextView)
        private val solvedAtTextView: TextView = itemView.findViewById(R.id.problemSolvedAtTextView)
        private val tagsTextView: TextView = itemView.findViewById(R.id.problemTagsTextView)

        fun bind(problem: SolvedProblem) {
            titleTextView.text = problem.title
            difficultyTextView.text = "Difficulty: ${problem.difficulty}"
            solvedAtTextView.text = "Solved At: ${problem.solvedAt}"
            tagsTextView.text = "Tags: ${problem.tags?.joinToString(", ")}"
        }
    }
}
