package com.sk.applicationmeals.ui.category

import com.sk.applicationmeals.data.CategoryUIModel

sealed class CategoryUIState {
    object ShowProgress : CategoryUIState()
    data class CategoryList(val listOfCategories: List<CategoryUIModel>) : CategoryUIState()
    data class CategoryListError(val errorMessage: String) : CategoryUIState()
    data class OnCategorySelected(val categoryName: String) : CategoryUIState()
}