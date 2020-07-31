package wami.ikechukwu.kanu.view.ui

//import wami.ikechukwu.kanu.databinding.ActivityOnboardingBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import wami.ikechukwu.kanu.R
import wami.ikechukwu.kanu.viewmodel.OnboardingViewModel

class OnboardingActivity : AppCompatActivity() {

    //    FOR THE DATA BINDING
//    private lateinit var binding: ActivityOnboardingBinding

    //    FOR THE VIEW MODEL
    private lateinit var onboardViewModel: OnboardingViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)

        onboardViewModel = ViewModelProvider(this)[OnboardingViewModel::class.java]

        onboardViewModel.initTheDataModel()

        onboardViewModel.initTheAdapter()


        val ik: ViewPager2 = findViewById(R.id.onboarding_viewpager2)
        ik.adapter = onboardViewModel.initTheAdapter()

    }

}