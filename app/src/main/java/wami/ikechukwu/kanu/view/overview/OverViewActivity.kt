package wami.ikechukwu.kanu.view.overview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import wami.ikechukwu.kanu.R
import wami.ikechukwu.kanu.databinding.ActivityOverViewBinding

class OverViewActivity : AppCompatActivity() {
    //    DATA BINDING
    private lateinit var binding: ActivityOverViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        BINDING
        binding = DataBindingUtil.setContentView(this, R.layout.activity_over_view)

//CALLING THE BOTTOM NAVIGATION
        setupBottomNavigation()
    }

    //    BOTTOM NAVIGATION
    private fun setupBottomNavigation() {
        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    Toast.makeText(this, "home", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.recent -> {
                    Toast.makeText(this, "recent", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.more -> {
                    Toast.makeText(this, "more", Toast.LENGTH_LONG).show()
                    true
                }
                else -> true
            }

        }
    }
}