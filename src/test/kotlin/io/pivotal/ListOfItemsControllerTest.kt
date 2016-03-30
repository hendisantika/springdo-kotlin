package io.pivotal

import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(SpringdoKotlinApplication::class))
@WebAppConfiguration
class ListOfItemsControllerTest {

    @Autowired lateinit var context: WebApplicationContext
    lateinit var mvc: MockMvc

    @Before
    fun setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(this.context).build()
    }

    @Test
    fun whenDefaultPlaceholdersLoadedTwoTasksShouldShow() {
        mvc.perform(get("/resource/list"))
                .andExpect(status().isOk)
                .andDo(print())
                .andExpect(jsonPath("$", hasSize<Any>(2)))
                .andExpect(jsonPath("$[0].id", equalTo("1")))
                .andExpect(jsonPath("$[1].id", equalTo("2")))
                .andExpect(jsonPath("$[0].title", equalTo("Go for a swim")))
                .andExpect(jsonPath("$[1].title", equalTo("Visit farmer's market")))
                .andExpect(jsonPath("$[0].content", equalTo("Go swimming on Monday night")))
                .andExpect(jsonPath("$[1].content", equalTo("Buy dairy and eggs at farmers market")))
    }
}