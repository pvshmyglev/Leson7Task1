package ru.netology

import org.junit.Test

import org.junit.Assert.*
import ru.netology.WallService.getMaxGuidInList


internal class WallServiceTest {

    @Test
    fun testAdd() {

        val emptyPost: Post = newEmptyPost("Первый пост")
        val post = WallService.add(emptyPost)
        assertNotEquals(post.id, 0)
    }

    @Test
    fun testUpdateWhithId() {

        val emptyPost: Post = newEmptyPost("Первый пост")
        val post = WallService.add(emptyPost)
        val result: Boolean = WallService.update(post)

        assertTrue(result)
    }

    @Test
    fun testUpdateNotId() {
        val emptyPost: Post = newEmptyPost("Первый пост")
        val result: Boolean = WallService.update(emptyPost.copy(id = -1))

        assertFalse(result)
    }

    @Test
    fun testGetMaxIdInPostsEmpty() {

        val posts = mutableListOf<Post>()
        val result: Int = posts.getMaxGuidInList()

        assertNotEquals(result, 0)
    }

    @Test
    fun testGetMaxIdInPostsNotEmpty() {

        val posts = mutableListOf<Post>()
        val emptyPost: Post = newEmptyPost("Первый пост")
        posts += emptyPost.copy(id = 1)
        val result: Int = posts.getMaxGuidInList()

        assertNotEquals(result, 1)
    }

    @Test
    fun getMaxGuidInList_Wall() {
        val comments = mutableListOf<Comment>()
        val comment: Comment = Comment(1, 1, "", 1)
        comments += comment
        val result: Int = comments.getMaxGuidInList()

        assertNotEquals(result, 1)
    }

    @Test
    fun getMaxGuidInList_Int() {
        val test = mutableListOf(1, 3, 2, 4)
        val id = test.getMaxGuidInList()

        assertEquals(id, 5)
    }

    @Test
    fun getMaxGuidInList_Other() {
        val test = mutableListOf(1.1, 32.3, 2.3)
        val id = test.getMaxGuidInList()

        assertEquals(id, 1)
    }

    @Test
    fun createComment_ThereIsPost() {
        val emptyPost: Post = newEmptyPost("Первый пост")
        val post = WallService.add(emptyPost)
        val comment = Comment(1, 1, "Первый комментарий", 1)
        WallService.createComment(comment)

        assertTrue(true)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow_Comment() {
        val comment = Comment(1, -1, "Первый комментарий", 1)
        WallService.createComment(comment)
    }

    @Test
    fun createReportComment_ThereIsComment() {

        val emptyPost: Post = newEmptyPost("Первый пост")
        val post = WallService.add(emptyPost)
        val comment = Comment(1, 1, "Первый комментарий", 1)
        WallService.createComment(comment)

        val report = ReportComment(1, 1, 1)
        WallService.createReport(report)

        assertTrue(true)
    }

    @Test(expected = CommentNotFoundException::class)
    fun shouldThrow_Report() {
        val report = ReportComment(1, -1, 1)
        WallService.createReport(report)
    }

    fun newEmptyPost(text: String): Post {


        val copyright = Copyright(0, "", "", "")
        val like = Like(0, false, true, true)
        val repost = Repost(0, false)
        val view = View(0)
        val donut = Donut(false, 0, "", true, "")
        val nowDateUnixtime = System.currentTimeMillis() / 1000L;  //(TimeZone.getTimeZone("Europe/Moscow"))
        val place = Place(0,"", 0, 0, 0,"", 0, 0, 0, 0, 0, "")
        val geo = Geo("", "", place)
        val postSource = PostSource("", "", "", "")
        val attachments = emptyArray<Attachment>()

        val result = Post(
            0,
            0,
            0,
            0,
            nowDateUnixtime.toInt(),
            text,
            0,
            0,
            false,
            copyright,
            like,
            repost,
            view,
            0,
            postSource,
            attachments,
            geo,
            0,
            null,
            true,
            true,
            true,
            false,
            false,
            false,
            donut,
            0)

        return result;

    }

}