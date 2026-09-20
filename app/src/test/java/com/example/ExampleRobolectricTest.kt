package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Readscape", appName)
  }

  @Test
  fun `catalog and sample data is properly populated`() {
    val catalog = com.example.data.SampleBooksData.getAllCatalog()
    org.junit.Assert.assertTrue(catalog.isNotEmpty())
    org.junit.Assert.assertEquals("The Psychology of Money", com.example.data.SampleBooksData.continueReadingBook.title)
    org.junit.Assert.assertTrue(com.example.data.SampleBooksData.bestSellers.size >= 5)
  }
}
