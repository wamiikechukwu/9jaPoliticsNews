package wami.ikechukwu.kanu.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import wami.ikechukwu.kanu.R
import wami.ikechukwu.kanu.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)


    }

}