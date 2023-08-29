package com.sk.applicationmeals.ui.meals

import com.sk.applicationmeals.data.MealsUIModel


sealed class MealsUIState {

    data class MealsList(val listOfMeals: List<MealsUIModel>) : MealsUIState()
    data class MealsListError(val errorMessage: String) : MealsUIState()
}