package fhnw.emoba.blockbuster.data.models

import junit.framework.TestCase.assertEquals
import org.json.JSONObject
import org.junit.Test

class GenreTest {

    @Test
    fun testGenreInitialization() {
        // Create a sample JSON object
        val jsonString = """
            {
                "id": 1,
                "name": "Action"
            }
        """
        val jsonObject = JSONObject(jsonString)

        // Create a Genre instance using the JSONObject
        val genre = Genre(jsonObject)

        // Assert that the id and name are correctly initialized
        assertEquals(1, genre.id)
        assertEquals("Action", genre.name)
    }
}