package com.sk.applicationmeals.ui.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sk.applicationmeals.base.BaseResponse
import com.sk.applicationmeals.data.CategoryUIModel
import com.sk.applicationmeals.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "CategoryViewModel"

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _categoryUIState = MutableLiveData<CategoryUIState>()
    val categoryUIState: LiveData<CategoryUIState> = _categoryUIState

    private val listOfCategories = ArrayList<CategoryUIModel>()

    fun fetchCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            launch {  }
            launch {  }
            try {
                launch {
                    throw Exception()
                }
            }catch (e:Exception){

            }


            listOfCategories.clear()
            val categories = repository.getCategories()
            Log.d(TAG, "$categories")
            when (categories) {
                is BaseResponse.Success -> {
                    listOfCategories.addAll(categories.data)
                    _categoryUIState.postValue(CategoryUIState.CategoryList(listOfCategories))
                }

                is BaseResponse.Error -> {
                    _categoryUIState.postValue(CategoryUIState.CategoryListError(categories.errorMessage))
                }
            }
        }
    }

    fun onItemClick(position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (position in 0 until listOfCategories.size){
                val categoryUIModel = listOfCategories[position]
                _categoryUIState.postValue(CategoryUIState.OnCategorySelected(categoryUIModel.categoryName))
            }
        }
    }

}