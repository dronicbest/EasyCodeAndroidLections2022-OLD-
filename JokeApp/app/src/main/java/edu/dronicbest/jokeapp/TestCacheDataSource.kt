package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
class TestCacheDataSource : CacheDataSource {
    private val list = ArrayList<Pair<Int, JokeServerModel>>()

    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
        if (list.isEmpty())
            jokeCachedCallback.fail()
        else
            jokeCachedCallback.provide(list.random().second)
    }

    override fun addOrRemove(id: Int, jokeServerModel: JokeServerModel): Joke {
        var found = list.find {it.first == id}

        return if (found != null) {
            val joke = found.second.toBaseJoke()
            list.remove(found)
            joke
        } else {
            list.add(Pair(id, jokeServerModel))
            jokeServerModel.toFavoriteJoke()
        }
    }
}