package com.sk.applicationmeals.common

object AppConstants {


    private const val BASE_DOMAIN = "https://www.themealdb.com/"
    private const val API_PATH = "api/json/v1/1/"
    const val BASE_URL = BASE_DOMAIN.plus(API_PATH)


//    API Endpoints
    const val PATH_CATEGORY = "categories.php"
    const val PATH_MEALS_BY_CATEGORY_FILTER = "filter.php"
}