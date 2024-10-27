package com.ritika.taskapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.ritika.taskapi.dataclasses.UserProfile
import kotlinx.coroutines.launch


class Profile : Fragment() {

    private lateinit var profileText: TextView
    private lateinit var profileDetailText: TextView
    private lateinit var profileImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         profileText= view.findViewById<TextView>(R.id.profile_text)
        profileDetailText= view.findViewById<TextView>(R.id.profile_detail_text)
         profileImage = view.findViewById<ImageView>(R.id.profile_image)
        sharedViewModel.username.observe(viewLifecycleOwner) { username ->
            apiCall(username)
        }
    }

    private fun apiCall(userName: String) {
        lifecycleScope.launch {
            try {
                val service = retrofit.api.getUserProfile(userName)
                profileDetailText.text = formatUserProfile(service)
                profileText.text="hello ${service.name} !!!"
                loadImage(service.avatar)
            } catch (e: Exception) {
                profileText.text = "$e"
            }
        }
    }

    private fun formatUserProfile(userProfile: UserProfile): String {
        return """
            Username: ${userProfile.username ?: "N/A"}
            Name: ${userProfile.name ?: "N/A"}
            About: ${userProfile.about ?: "N/A"}
            Birthday: ${userProfile.birthday ?: "N/A"}
            Company: ${userProfile.company ?: "N/A"}
            Country: ${userProfile.country ?: "N/A"}
            GitHub: ${userProfile.gitHub ?: "N/A"}
            LinkedIn: ${userProfile.linkedIN ?: "N/A"}
            Ranking: ${userProfile.ranking ?: "N/A"}
            Reputation: ${userProfile.reputation ?: "N/A"}
            School: ${userProfile.school ?: "N/A"}
            Skills: ${userProfile.skillTags?.joinToString(", ") ?: "N/A"}
            Twitter: ${userProfile.twitter ?: "N/A"}
            Website: ${userProfile.website?.joinToString(", ") ?: "N/A"}
        """.trimIndent()
    }

    private fun loadImage(avatarUrl: String?) {
        if (!avatarUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(avatarUrl)
                .into(profileImage)
        }
    }
}
