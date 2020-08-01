package wami.ikechukwu.kanu.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import wami.ikechukwu.kanu.R
import wami.ikechukwu.kanu.databinding.ActivityOnboardingBinding
import wami.ikechukwu.kanu.viewmodel.OnboardingViewModel

class OnboardingActivity : AppCompatActivity() {

    //        FOR THE DATA BINDING
    private lateinit var binding: ActivityOnboardingBinding

    //    FOR THE VIEW MODEL
    private lateinit var onboardViewModel: OnboardingViewModel

    //    CURRENT VIEW PAGER ADAPTER POSITION
    var currentViewPagerPosition = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//      HIDDING THE ACTION BAR
        supportActionBar?.hide()

//        BINDING THE LAYOUT TO DATABINDING
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)

//        ACCESSING THE VIEW MODEL CLASS
        onboardViewModel = ViewModelProvider(this)[OnboardingViewModel::class.java]

//        VIEWPAGER WIDGET FROM THE LAYOUT
        binding.onboardingViewpager.adapter = onboardViewModel.initTheAdapter()
        binding.onboardingTabLayout.setupWithViewPager(binding.onboardingViewpager)
        binding.onboardingViewpager.setPadding(40, 0, 40, 0)

//        SET THE CURRENT ITEM FROM THE VIEWPAGER
        currentViewPagerPosition = binding.onboardingViewpager.currentItem

        fun setCurrentPosition() {
            binding.onboardingViewpager.currentItem = currentViewPagerPosition
        }

//      WHEN THE NEXT BUTTON IS CLICKED DO THE FOLLOWING
        binding.onboardingNextButton.setOnClickListener {

            if (currentViewPagerPosition < onboardViewModel.onArrayListSize()) {
                currentViewPagerPosition++

                setCurrentPosition()
            }

        }

    }
}


