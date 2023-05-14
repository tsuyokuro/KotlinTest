package com.example.myapplication.cardlib

import androidx.test.platform.app.InstrumentationRegistry
import com.example.myapp2.JacocoUtils
import org.junit.After
import org.junit.Assert.*
import org.junit.Test

class WebApiResponseTest {
    @After
    @Throws(Exception::class)
    fun tearDown() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        JacocoUtils.generateEcFile(appContext, false)
    }

    @Test
    fun deserializeTest() {
        var data:Map<Any?, Any?> = mapOf(
            "retCode" to "200",
            "status" to "2000",
            "body" to "body001"
        )

        var a = WebApiResponse.deserialize(data)

        assert(a.retCode == "200")
    }
}
