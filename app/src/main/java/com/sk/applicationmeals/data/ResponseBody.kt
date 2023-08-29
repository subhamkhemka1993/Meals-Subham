package com.sk.applicationmeals.data

data class CategoryListDto(
    val categories: List<CategoryDto>? = null,
)

data class CategoryDto(
    val idCategory: String? = null,
    val strCategory: String? = null,
    val strCategoryDescription: String? = null,
    val strCategoryThumb: String? = null,
)

data class MealsListDto(
    val meals: List<MealDto>? = null,
)

data class MealDto(
    val idMeal: String? = null,
    val strMeal: String? = null,
    val strMealThumb: String? = null,
)

fun CategoryDto.toCategoryUIModel(): CategoryUIModel {
    return CategoryUIModel(categoryName = strCategory.orEmpty(),
        categoryImageURL = strCategoryThumb.orEmpty(),
        categoryDescription = strCategoryDescription.orEmpty())
}

fun MealDto.toMealsUIModel(): MealsUIModel {
    return MealsUIModel(mealName = strMeal.orEmpty(),
        mealImageURL = strMealThumb.orEmpty())
}


data class CategoryUIModel(
    val categoryName: String,
    val categoryDescription: String,
    val categoryImageURL: String,
)

data class MealsUIModel(
    val mealName: String,
    val mealImageURL: String,
)