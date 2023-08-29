package com.sk.applicationmeals.ui.meals

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sk.applicationmeals.base.BaseResponse
import com.sk.applicationmeals.data.MealsUIModel
import com.sk.applicationmeals.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private var _mealsUIState = MutableLiveData<MealsUIState>()
    val mealsUIState: LiveData<MealsUIState> = _mealsUIState

    private val mealsList = ArrayList<MealsUIModel>()

    fun fetchMeals(args: Bundle?) {
        viewModelScope.launch(Dispatchers.IO) {
            mealsList.clear()
            val catName = args?.getString("Category Name").orEmpty()
            if (catName.isNotEmpty()) {
                when (val mealsByCategory = repository.getMealsByCategory(catName)) {
                    is BaseResponse.Success -> {
                        mealsList.addAll(mealsByCategory.data)
                        _mealsUIState.postValue(MealsUIState.MealsList(mealsList))
                    }

                    is BaseResponse.Error -> {
                        _mealsUIState.postValue(MealsUIState.MealsListError(mealsByCategory.errorMessage))
                    }
                }
            }
        }
    }

}