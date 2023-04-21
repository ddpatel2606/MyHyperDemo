package com.dixitpatel.mycoffeevenue.mymodule.domain.usecases

import com.dixitpatel.mycoffeevenue.mymodule.domain.repository.RandomTextRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *  GetCountedWordUseCase
 *  This usecase will used for count the word from text.
 */
class GetCountedWordUseCase @Inject constructor(
    private val randomTextRepository: RandomTextRepository
)
{
    operator fun invoke(text: String): Flow<Int> = flow {
        emit(
            randomTextRepository.getCountWordFromText(text)
        )
    }
}