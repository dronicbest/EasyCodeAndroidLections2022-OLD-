package edu.dronicbest.jokeapp

import io.realm.Realm

/**
 * JokeApp
 * @author dronicbest on 13.07.2022
 */
class BaseCacheDataSource(private val realm: Realm) : CacheDataSource {

    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
        realm.let {
            val jokes = it.where(JokeRealm::class.java).findAll()
            if (jokes.isEmpty())
                jokeCachedCallback.fail()
            else
                jokes.random().let { jokeRealm ->
                    jokeCachedCallback.provide(
                        Joke(
                            jokeRealm.id,
                            jokeRealm.type,
                            jokeRealm.text,
                            jokeRealm.punchLine
                        )
                    )
                }
        }
    }

    override fun addOrRemove(id: Int, joke: Joke): JokeUiModel {
        realm.let {
            val jokeRealm = it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
            return if (jokeRealm == null) {
                val newJoke = joke.toJokeRealm()
                it.executeTransactionAsync {
                    it.insert(newJoke)
                }
                joke.toFavoriteJoke()
            } else {
                it.executeTransaction {
                    // TODO
                    jokeRealm.deleteFromRealm()
                }
                joke.toBaseJoke()
            }
        }
    }
}