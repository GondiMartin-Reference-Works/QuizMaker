package hu.bme.aut.android.quizmaker.mainPage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class QuizPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    companion object {
        private const val NUM_PAGES: Int = 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> QuizMainFragment()
            1 -> QuizResultFragment()
            else -> QuizMainFragment()
        }
    }

    override fun getItemCount(): Int = NUM_PAGES
}