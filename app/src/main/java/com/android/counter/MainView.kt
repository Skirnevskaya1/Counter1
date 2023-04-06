package com.android.counter

//@AddToEndSingle - есть ещё такой алиас
//@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: MvpView {
    fun setButtonOneText(text: String)
    fun setButtonTwoText(text: String)
    fun setButtonThreeText(text: String)
}