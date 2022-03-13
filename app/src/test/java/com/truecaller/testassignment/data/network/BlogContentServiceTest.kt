package com.truecaller.testassignment.data.network

import android.util.Log
import com.truecaller.testassignment.data.repository.BlogContentRepository
import com.truecaller.testassignment.util.constants.ApiConstants.Companion.BASE_URL
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class BlogContentServiceTest {

    private val server: MockWebServer = MockWebServer()
    private val MOCK_WEBSERVER_PORT = 8000
    lateinit var blogContentService: BlogContentService
    lateinit var blogContentRepository: BlogContentRepository

    @Before
    fun init() {
        server.start(MOCK_WEBSERVER_PORT)
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

    @After
    fun shutdown() {
        server.shutdown()
    }

    @Test
    fun `read sample success json file`() {
        val reader = FileReader("blogContent.html")
        assertNotNull(reader.content)
    }


    @Test
    fun `fetch blog content and check if response is not empty`() {
        // Act
        runBlocking {
            val actualResponse = blogContentRepository.getBlogContent()
            // Assert
            assert(actualResponse.toString().isNotEmpty())
        }

    }

}