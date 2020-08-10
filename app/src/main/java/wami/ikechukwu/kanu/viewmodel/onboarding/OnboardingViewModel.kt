package wami.ikechukwu.kanu.viewmodel.onboarding

import androidx.lifecycle.ViewModel
import wami.ikechukwu.kanu.R
import wami.ikechukwu.kanu.adapter.onboarding.OnboardingAdapter
import wami.ikechukwu.kanu.model.onboarding.OnboardingModel

class OnboardingViewModel : ViewModel() {
    //    DECLARED ARRAYLIST
    private val onArrayList: ArrayList<OnboardingModel> = ArrayList()

    init {
        initDataModelForTheOnboarding()
    }

    //SETTING UP THE ARRAYLIST
    private fun initDataModelForTheOnboarding() {
        onArrayList.add(OnboardingModel("Get All Latest News On Your Mobile", "Read With Ease", R.drawable.app, R.color.firstColor))
        onArrayList.add(OnboardingModel("Be Updated With Whats Happening", "Educate YourSelf", R.drawable.knowledge, R.color.secondColor))
        onArrayList.add(OnboardingModel("Read", "Be Inspire", R.drawable.student, R.color.thirdColor))
        onArrayList.add(OnboardingModel("See The World", "From One Spot", R.drawable.world, R.color.fourthColor))
    }

    //    PASSING IN THE ARRAYLIST TO THE ADAPTER
    fun initTheAdapter(): OnboardingAdapter {
        return OnboardingAdapter(onArrayList)
    }

    fun onArrayListSize(): Int {
        return onArrayList.size
    }
}