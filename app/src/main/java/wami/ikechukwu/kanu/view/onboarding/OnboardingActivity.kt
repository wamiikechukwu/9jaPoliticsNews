package wami.ikechukwu.kanu.view.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import wami.ikechukwu.kanu.R
import wami.ikechukwu.kanu.databinding.ActivityOnboardingBinding
import wami.ikechukwu.kanu.view.overview.OverViewActivity
import wami.ikechukwu.kanu.viewmodel.onboarding.OnboardingViewModel

class OnboardingActivity : AppCompatActivity() {


    //        FOR THE DATA BINDING
    private lateinit var binding: ActivityOnboardingBinding

    //    FOR THE VIEW MODEL
    private lateinit var onboardViewModel: OnboardingViewModel

    //    CURRENT VIEW PAGER ADAPTER POSITION
    var currentViewPagerPosition = 0

    //    GET STARTED BUTTON ANIMATION
    lateinit var getStartedBtnAnim: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        CHECK IF THE ONBOARDING SCREEN HAS BEEN SHOWN BEFORE
//        IF IT HAS THEN SHOW THE OVER VIEW ACTIVITY

        if (restoredPrefState()) {
//      OPEN THE OVER VIEW  ACTIVITY
            val intent = Intent(applicationContext, OverViewActivity::class.java)
            startActivity(intent)

//            STOP THE PREVIOUS ACTIVITY FROM SHOWING
            finish()
        }

//      HIDING THE ACTION BAR
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

//      WHEN THE NEXT BUTTON IS CLICKED DO THE FOLLOWING
        binding.onboardingNextButton.setOnClickListener {

//            FOR MOVING THE VIEW PAGER WHEN THE NEXT BUTTON IS CLICKED
            if (currentViewPagerPosition < onboardViewModel.onArrayListSize()) {
                currentViewPagerPosition++

//                GET THE CURRENT ADAPTERS POSITION
                setCurrentPosition()
            }

//            IF WE HAVE REACHED THE END OF THE VIEW PAGER
            if (currentViewPagerPosition == onboardViewModel.onArrayListSize()) {

//                SHOW OR NOT TO SHOW THE CORRESPONDING VIEW
                viewVisibility()

//                SHOW THE ANIMATION
                getStartedButtonAnimation()
            }

        }

//      TAKE US TO THE MAIN APP
        binding.getStartedButton.setOnClickListener {

//            SAVE A VALUE TO SHAREDPREF. TO SHOW THAT THE USER HAS SEEN THE ONBOARDING SCREEN
            savedPrefState()

//            OPEN THE OVER VIEW ACTIVITY ACTIVITY
            val intent = Intent(applicationContext, OverViewActivity::class.java)
            startActivity(intent)

//            END THE PREVIOUS ACTIVITY
            finish()
        }

//        WHEN THE USER SKIPS THE ONBOARDING
        binding.onboardingSkipText.setOnClickListener {
            //      OPEN THE OVER VIEW ACTIVITY
            val intent = Intent(applicationContext, OverViewActivity::class.java)
            startActivity(intent)

//            SAVE A VALUE TO SHAREDPREF. TO SHOW THAT THE USER HAS SEEN THE ONBOARDING SCREEN
            savedPrefState()

//            END THE PREVIOUS ACTIVITY
            finish()
        }
    }

    // SHOW ANIMATION
    private fun getStartedButtonAnimation() {
        getStartedBtnAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.getstarted_btn_anim)
        binding.getStartedButton.animation = getStartedBtnAnim
    }

    //    GET CURRENT VIEW PAGER ADAPTERS POSITION
    private fun setCurrentPosition() {
        binding.onboardingViewpager.currentItem = currentViewPagerPosition
    }

    private fun viewVisibility() {
//            SET THE GET STARTED BUTTON TO BE VISIBLE
        binding.getStartedButton.visibility = View.VISIBLE

//            SET THE NEXT BUTTON INVISIBLE
        binding.onboardingNextButton.visibility = View.INVISIBLE

//            SET THE TAB LAYOUT INVISIBLE
        binding.onboardingTabLayout.visibility = View.INVISIBLE

//        SET THE SKIP BUTTON INVISIBLE
        binding.onboardingSkipText.visibility = View.INVISIBLE
    }

    //    CALLED WHEN THE USER HAS SEEN THE ONBOARDING SCREEN
    private fun savedPrefState() {
        //    SET UP THE SHARED PREFERENCES
        val onBoardingSharedPreferences: SharedPreferences = this.getSharedPreferences("ONBOARDING", Context.MODE_PRIVATE)

//          PUTTING THE VALUES INTO THE SHARED PREF
        val sharedPrefEditor: SharedPreferences.Editor = onBoardingSharedPreferences.edit()
        sharedPrefEditor.putBoolean("ONBOARDING", true)
        sharedPrefEditor.apply()
        sharedPrefEditor.commit()
    }

    //    CALLED TO CHECK IF THE USER HAS SEEN THE ONBOARDING SCREEN
    private fun restoredPrefState(): Boolean {
        //    SET UP THE SHARED PREFERENCES
        val onBoardingSharedPreferences: SharedPreferences = this.getSharedPreferences("ONBOARDING", Context.MODE_PRIVATE)

//        GETTING THE VALUES FROM THE SHARED PREF
        return onBoardingSharedPreferences.getBoolean("ONBOARDING", false)
    }
}


