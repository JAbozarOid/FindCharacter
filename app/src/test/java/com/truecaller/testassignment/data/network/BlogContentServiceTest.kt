package com.truecaller.testassignment.data.network

import com.truecaller.testassignment.data.repository.BlogContentRepository
import com.truecaller.testassignment.util.StringUtil
import com.truecaller.testassignment.util.StringUtil.findWordsFromString
import com.truecaller.testassignment.util.constants.ApiConstants.Companion.BASE_URL
import com.truecaller.testassignment.util.network.ApiResponse
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class BlogContentServiceTest {

    lateinit var blogContentService: BlogContentService
    lateinit var blogContentRepository: BlogContentRepository

    val stringUtil = StringUtil

    @Before
    fun init() {
        val okhttpBuilder = OkHttpClient().newBuilder().readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS).build()

        blogContentService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttpBuilder)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(BlogContentService::class.java)
        blogContentRepository = BlogContentRepository(blogContentService)
    }

    @Test
    fun `fetch blog content and check if response is not empty`() {
        // Act
        runBlocking {
            val actualResponse = blogContentRepository.getBlogContent()
            // Assert
            assert(actualResponse is ApiResponse.Success)
        }
    }


    @Test
    fun `func findTenthChar`() {
        val findTenthChar: String = stringUtil.findTenthCharacter("abozar raghibdoust")
        assertThat(findTenthChar, equalTo("g"))
    }

    @Test
    fun `func findEveryTenthChar`() {
        val findEveryTenthChar: List<Char> = stringUtil.findEveryTenthCharacter("abozar raghibdoust")
        assertThat(findEveryTenthChar.toString(), equalTo("[g]"))
    }

    @Test
    fun `func findOccurrenceOfEachWord`() {
        val findOccurrenceOfEachWord: Map<String, Int> = stringUtil.findOccurrenceOfEachWord(findWordsFromString("abozarraghibdoust completed his test assignment"))
        assertThat(findOccurrenceOfEachWord.toString(), equalTo("{abozarraghibdoust=1, his=1, test=1, assignment=1, completed=1}"))
    }

}