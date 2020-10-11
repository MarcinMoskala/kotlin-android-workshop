package com.workshop.universityannouncementsboard

import android.app.*
import android.view.*
import com.workshop.universityannouncementsboard.util.*
import io.mockk.*
import org.junit.*

class BindToVisibilityTest {

//    lateinit var activity: Activity
//    lateinit var view: View
//    val viewId = 12345
//    var visibility: Int? = null
//
//    @Before
//    fun init() {
//        activity = mockk()
//        view = mockk()
//        every { activity.findViewById<View>(viewId) } returns view
//        every { view.visibility } answers { visibility ?: View.GONE }
//        every { view.visibility = any() } answers { visibility = invocation.args[0] as Int }
//    }
//
//    // It is because findViewById will not return correct object before
//    // onCreate, and so we need to set it up no earlier then during the first use
//    @Test
//    fun `Does not find view before the first use`() {
//        activity.apply {
//            var loading by bindToVisibility(viewId)
//        }
//        verifyAll(inverse = true) { activity.findViewById<View>(viewId) }
//    }
//
//    @Test
//    fun `When we use delegate, object is found`() {
//        activity.apply {
//            var loading by bindToVisibility(viewId)
//            loading
//        }
//        verifyAll { activity.findViewById<View>(viewId) }
//    }
//
//    // It would be inefficient as findViewById might be time-consuming
//    @Test
//    fun `Object should not be found more then one time`() {
//        activity.apply {
//            var loading by bindToVisibility(viewId)
//            loading
//            loading
//            loading
//            loading
//        }
//        verifyAll { activity.findViewById<View>(viewId) }
//    }
//
//    @Test
//    fun `Setting visibility to true sets view visibility to VISIBLE`() {
//        activity.apply {
//            var loading by bindToVisibility(viewId)
//            loading = true
//            verifyAll {
//                activity.findViewById<View>(viewId)
//                view.visibility = View.VISIBLE
//            }
//            Assert.assertEquals(View.VISIBLE, visibility)
//        }
//    }
//
//    @Test
//    fun `Setting visibility to false sets view visibility to GONE`() {
//        activity.apply {
//            var loading by bindToVisibility(viewId)
//            loading = false
//            verifyAll {
//                activity.findViewById<View>(viewId)
//                view.visibility = View.GONE
//            }
//            Assert.assertEquals(View.GONE, visibility)
//            loading = true
//            Assert.assertEquals(View.VISIBLE, visibility)
//            loading = false
//            Assert.assertEquals(View.GONE, visibility)
//        }
//    }
//
//    @Test
//    fun `Getter answers if view is VISIBLE`() {
//        activity.apply {
//            var loading by bindToVisibility(viewId)
//            visibility = View.VISIBLE
//            Assert.assertTrue(loading)
//            visibility = View.GONE
//            Assert.assertFalse(loading)
//            visibility = View.VISIBLE
//            Assert.assertTrue(loading)
//            visibility = View.GONE
//            Assert.assertFalse(loading)
//        }
//    }
//
//    @Test
//    fun `Getter and setter are consistent`() {
//        activity.apply {
//            var loading by bindToVisibility(viewId)
//            loading = true
//            Assert.assertTrue(loading)
//            loading = false
//            Assert.assertFalse(loading)
//            loading = true
//            Assert.assertTrue(loading)
//            loading = false
//            Assert.assertFalse(loading)
//        }
//    }
//
//    class ViewMock(var visibility: Int = View.GONE)
}