package wami.ikechukwu.kanu.util

import wami.ikechukwu.kanu.`interface`.APIService

class Repository constructor(private val apiService: APIService) {

    fun fetchTitle(): List<String> {
//        TODO WRITE YOUR INTERNET CONNECTION
        return apiService.getTitle()
    }
}
