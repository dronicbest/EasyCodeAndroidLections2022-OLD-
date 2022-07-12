package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
class TestCacheDataSource : CacheDataSource {
    private val map = HashMap<Int, JokeServerModel>()

    override fun addOrRemove(id: Int, jokeServerModel: JokeServerModel): Joke {
        return if (map.containsKey(id)) {
            val joke = map[id]!!.toBaseJoke()
            joke
        } else {
            map[id] = jokeServerModel
            jokeServerModel.toFavoriteJoke()
        }
    }
}