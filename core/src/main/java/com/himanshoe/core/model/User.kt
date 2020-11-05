package com.himanshoe.core.model

data class User(
    val id: String = "",
    val displayUrl: String = "",
    val name: String = "",
    val email: String = "",
    val description: String = "",
    val source: Source? = null
) {
    fun isLoggedIn(): Boolean {
        return id.isNotEmpty()
    }
}

enum class Source(val value: String) {
    GOOGLE("google"),
    EMAIL("email")
}