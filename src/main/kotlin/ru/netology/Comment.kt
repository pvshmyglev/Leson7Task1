package ru.netology

data class Comment(
    val ownerId: Int,
    val postId: Int,
    val message: String,
    val guid: Int)