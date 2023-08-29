package com.sk.applicationmeals.data.repository

import com.sk.applicationmeals.base.BaseResponse
import com.sk.applicationmeals.data.CategoryListDto
import com.sk.applicationmeals.data.CategoryUIModel
import com.sk.applicationmeals.data.MealsListDto
import com.sk.applicationmeals.data.MealsUIModel
import com.sk.applicationmeals.data.network.ApiService
import com.sk.applicationmeals.data.toCategoryUIModel
import com.sk.applicationmeals.data.toMealsUIModel
import java.lang.Exception

class RepositoryImpl constructor(val apiService: ApiService) : Repository {

    override suspend fun getCategories(): BaseResponse<List<CategoryUIModel>> {
        return try {
            val categoriesResponse = apiService.getCategories()
            val categoryUIModelList = categoriesResponse.categories?.map { it.toCategoryUIModel() }.orEmpty()
            BaseResponse.Success(categoryUIModelList)
        } catch (e: Exception) {
            BaseResponse.Error(e.message.orEmpty())
        }
    }

    override suspend fun getMealsByCategory(categoryName: String): BaseResponse<List<MealsUIModel>> {
        return try {
            val mealsResponse = apiService.getMealsByCategory(categoryName)
            val mealsUIModelList = mealsResponse.meals?.map { it.toMealsUIModel() }.orEmpty()
            BaseResponse.Success(mealsUIModelList)
        } catch (e: Exception) {
            BaseResponse.Error(e.message.orEmpty())
        }
    }
}