package wami.ikechukwu.kanu.view.ui

//import wami.ikechukwu.kanu.databinding.ActivityOnboardingBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
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


        val ik: ViewPager = findViewById(R.id.onboarding_viewpager2)
        ik.adapter = onboardViewModel.initTheAdapter()
        ik.setPadding(40, 0, 40, 0)

        // val marginPageTransformer = MarginPageTransformer(50)

//        ik.setPageTransformer(CompositePageTransformer().also {
//
//            //it.addTransformer(marginPageTransformer)
//
//          it.addTransformer(transformer)
//        })

    }


    val transformer = ViewPager2.PageTransformer { page, position ->
        page.apply {
//          translationX = Math.abs(position) * 100f
//           scaleX = 1f
//           scaleY
        }
    }


}


