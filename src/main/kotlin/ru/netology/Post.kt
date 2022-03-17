package ru.netology

import java.time.LocalDateTime

data class Post(
val id: Int,
val ownerId: Int,
val fromId: Int,
val createdBy: Int,
val date: Int,
val text: String,
val replyOwnerId: Int,
val replyPostId: Int,
val friendsOnly: Boolean,
val copyright: Copyright,
val likes: Like,
val reposts: Repost,
val views: View,
val postType: Int,
val postSource: PostSource?,
val attachments: Array<Attachment>,
val geo: Geo,
val signerId: Int,
val copyHistory: Array<Post>?,
val canPin: Boolean,
val canDelete: Boolean,
val canEdit: Boolean,
val isPinned: Boolean,
val markedAsAds: Boolean,
val isFavorite: Boolean,
val donut: Donut,
val postponedId: Int)