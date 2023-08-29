package com.sk.applicationmeals.data.network

import com.sk.applicationmeals.common.AppConstants
import com.sk.applicationmeals.data.CategoryListDto
import com.sk.applicationmeals.data.MealsListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(value = AppConstants.PATH_CATEGORY)
    suspend fun getCategories(): CategoryListDto

    @GET(value = AppConstants.PATH_MEALS_BY_CATEGORY_FILTER)
    suspend fun getMealsByCategory(@Query("c") categoryName: String): MealsListDto
}