package com.dixitpatel.mycoffeevenue.mymodule.data.repositoryIml


import com.dixitpatel.mycoffeevenue.mymodule.data.datasource.countWordsFromString
import com.dixitpatel.mycoffeevenue.mymodule.domain.repository.RandomTextRepository
import com.dixitpatel.mycoffeevenue.mymodule.data.datasource.getRandomParagraph
import javax.inject.Inject
import javax.inject.Singleton

/**
 *  RandomTextRepositoryImpl
 *  This is implementation of RandomTextRepository interface, DI will inject this implementation to the app.
 */
@Singleton
open class RandomTextRepositoryImpl @Inject constructor(): RandomTextRepository {

    override suspend fun getRandomParagraphText(): String = getRandomParagraph()

    override suspend fun getCountWordFromText(text: String): Int = countWordsFromString(text)

}