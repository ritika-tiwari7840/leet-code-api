package com.ritika.taskapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ritika.taskapi.dataclasses.Title

class SubmissionAdapter(private val submissions: List<Title>) :
    RecyclerView.Adapter<SubmissionAdapter.SubmissionViewHolder>() {

    inner class SubmissionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionTitle: TextView = itemView.findViewById(R.id.question_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubmissionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_submission, parent, false)
        return SubmissionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubmissionViewHolder, position: Int) {
        val submission = submissions[position]
        holder.questionTitle.text = submission.title
    }

    override fun getItemCount(): Int = submissions.size
}