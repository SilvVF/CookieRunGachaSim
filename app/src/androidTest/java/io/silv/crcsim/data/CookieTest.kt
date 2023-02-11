package io.silv.crcsim.data

import org.junit.Assert.*
import org.junit.Test


class CookieTest {


    @Test
    fun allImageUrlsAreUnique() {

        val urls = mutableSetOf<String>()

        Cookie.values().forEach { cookie: Cookie ->
            assertNotEquals(cookie.soulstoneUrl, cookie.imageUrl)
            assertTrue("already contains ${cookie.imageUrl}", urls.add(cookie.imageUrl))
            assertTrue("already contains ${cookie.soulstoneUrl}", urls.add(cookie.soulstoneUrl))
        }
    }
}