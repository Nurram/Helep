package com.rex.project.helep.view.activities.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.rex.project.helep.R
import com.rex.project.helep.databinding.ActivityHomeBinding
import com.rex.project.helep.view.fragments.dashboard.DashBoardFragment
import com.rex.project.helep.view.fragments.find.FindFragment
import com.rex.project.helep.view.fragments.posts.PostFragment
import com.rex.project.helep.view.fragments.profile.ProfileFragment
import com.simform.custombottomnavigation.Model

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menuItems = arrayOf(
            Model(
                icon = R.drawable.ic_outline_home_24,
                id = HOME_ID,
                text = R.string.home,
            ),
            Model(
                icon = R.drawable.ic_outline_search_24,
                id = FIND_ID,
                text = R.string.find,
            ),
            Model(
                R.drawable.ic_outline_add_to_photos_24,
                id = POST_ID,
                text = R.string.post,
            ),
            Model(
                R.drawable.ic_outline_mark_unread_chat_alt_24,
                id = CHAT_ID,
                text = R.string.chat,
                count = R.string.chat_count
            ),
            Model(
                R.drawable.ic_outline_person_24,
                id = PROFILE_ID,
                text = R.string.profile,
            )
        )
        binding.botNavMain.apply {
            setSelectedIndex(0)
            setMenuItems(menuItems, 0)
            setOnMenuItemClickListener { model, _ ->
                when(model.id) {
                    HOME_ID -> replaceFragment(DashBoardFragment())
                    POST_ID -> replaceFragment(PostFragment())
                    FIND_ID -> replaceFragment(FindFragment())
                    PROFILE_ID -> replaceFragment(ProfileFragment())
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fr_container, fragment).commit()
    }

    companion object {
        const val HOME_ID = 0
        const val FIND_ID = 1
        const val POST_ID = 2
        const val CHAT_ID = 3
        const val PROFILE_ID = 4
    }
}