package com.ritika.taskapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.ritika.taskapi.dataclasses.UserBadges
import kotlinx.coroutines.launch

class Badge : Fragment() {

    private lateinit var badgesCountTextView: TextView
    private lateinit var activeBadgeTextView: TextView
    private lateinit var badgesContainer: LinearLayout
    private lateinit var upcomingBadgesContainer: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_badge, container, false)
    }
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        badgesCountTextView = view.findViewById(R.id.badgesCountTextView)
        activeBadgeTextView = view.findViewById(R.id.activeBadgeTextView)
        badgesContainer = view.findViewById(R.id.badgesContainer)
        upcomingBadgesContainer = view.findViewById(R.id.upcomingBadgesContainer)

        sharedViewModel.username.observe(viewLifecycleOwner) { username ->
            apiCall(username)
        }
    }

    private fun apiCall(userName: String) {
        lifecycleScope.launch {
            try {
                val service = retrofit.api.getUserBadges(userName)
                displayBadges(service)
            } catch (e: Exception) {
                badgesCountTextView.text = "Error"
            }
        }
    }

    private fun displayBadges(userBadges: UserBadges) {
        badgesCountTextView.text = "Badges Count: ${userBadges.badgesCount}"
        activeBadgeTextView.text = "Active Badge: ${userBadges.activeBadge}"

        badgesContainer.removeAllViews()
        var i=1
        userBadges.badges?.forEach { badge ->
            val badgeTextView = TextView(context).apply {
                text = "Badge: $i) ${badge.displayName} (ID: ${badge.id})"
                textSize = 16f
                setPadding(0, 8, 0, 8)
                i=i+1
            }
            badgesContainer.addView(badgeTextView)
        }

        upcomingBadgesContainer.removeAllViews()
       i=1
        userBadges.upcomingBadges?.forEach { upcomingBadge ->
            val upcomingBadgeTextView = TextView(context).apply {
                text = "$i) ${upcomingBadge.name}"
                textSize = 16f
                setPadding(0, 8, 0, 8)
                i=i+1
            }
            upcomingBadgesContainer.addView(upcomingBadgeTextView)
        }
    }
}