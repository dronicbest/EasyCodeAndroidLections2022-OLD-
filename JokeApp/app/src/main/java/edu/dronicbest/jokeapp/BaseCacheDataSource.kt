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
                        JokeServerModel(
                            jokeRealm.id,
                            jokeRealm.type,
                            jokeRealm.text,
                            jokeRealm.text,
                            jokeRealm.punchLine
                        )
                    )
                }
        }
    }

    override fun addOrRemove(id: Int, jokeServerModel: JokeServerModel): Joke {
        realm.let {
            val jokeRealm = it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
            return if (jokeRealm == null) {
                val newJoke = jokeServerModel.toJokeRealm()
                it.executeTransactionAsync {
                    it.insert(newJoke)
                }
                jokeServerModel.toFavoriteJoke()
            } else {
                it.executeTransactionAsync {
                    // TODO
                    jokeRealm.deleteFromRealm()
                }
                jokeServerModel.toBaseJoke()
            }
        }
    }
}