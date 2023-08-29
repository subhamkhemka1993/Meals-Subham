package com.sk.applicationmeals.data.repository

import com.sk.applicationmeals.base.BaseResponse
import com.sk.applicationmeals.data.CategoryListDto
import com.sk.applicationmeals.data.CategoryUIModel
import com.sk.applicationmeals.data.MealsListDto
import com.sk.applicationmeals.data.MealsUIModel

interface Repository {

    suspend fun getCategories(): BaseResponse<List<CategoryUIModel>>

    suspend fun getMealsByCategory(categoryName: String): BaseResponse<List<MealsUIModel>>
}