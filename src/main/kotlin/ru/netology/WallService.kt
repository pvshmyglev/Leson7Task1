package ru.netology

object WallService {

    private var posts = mutableListOf<Post>()
    private var comments = mutableListOf<Comment>()
    private var reports = mutableListOf<ReportComment>()

    fun add(post: Post): Post {

        posts += post.copy(posts.getMaxGuidInList())

        return posts.last()

    }

    fun update(post: Post): Boolean {

        for ((index, value) in posts.withIndex()) {

            if (post.id == value.id) {
                posts[index] = post.copy(ownerId = value.ownerId, date = value.date)
                return  true
            }

        }

        return  false

    }

    fun createComment(comment: Comment) {

        val post = posts.find { it.id == comment.postId }
        if (post == null) throw PostNotFoundException("При добавлении комментария не нашли пост")
        comments += comment.copy(guid = comments.getMaxGuidInList())


    }

    fun createReport(report: ReportComment) {

        val comment = comments.find { it.guid == report.commentId }
        if (comment == null) throw CommentNotFoundException("При добавлении жалобы не нашли комментарий по id = ${report.commentId}")
        reports += report

    }

    //Получает максимальный идентификатор в коллекции, ограниченное использование
    fun <T> MutableList<T>.getMaxGuidInList(): Int {

        var currentMaxId = 0

        for (value in this) {
            val id: Int = when{
                (value is Post) -> (value as Post).id
                (value is Comment) -> (value as Comment).guid
                (value is Int) -> value
                else -> 0}
            if (id >= currentMaxId)  currentMaxId = id
        }

        return  currentMaxId + 1

    }

}