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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//      HIDDING THE ACTION BAR
        supportActionBar?.hide()

//        BINDING THE LAYOUT TO DATABINDING
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)

//        ACCESSING THE VIEW MODEL CLASS
        onboardViewModel = ViewModelProvider(this)[OnboardingViewModel::class.java]

//        CALLING THE FUNCTION IN THE VIEW MODEL CLASS
        onboardViewModel.initDataModelForTheOnboarding()

//        CALL A METHOD IN THE VIEW MODEL CLASS
        onboardViewModel.initTheAdapter()

//        VIEWPAGER WIDGET FROM THE LAYOUT
        binding.onboardingViewpager.adapter = onboardViewModel.initTheAdapter()
        binding.onboardingViewpager.setPadding(40, 0, 40, 0)

    }
}


