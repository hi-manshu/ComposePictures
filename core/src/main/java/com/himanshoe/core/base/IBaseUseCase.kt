package com.himanshoe.core.base

import com.roomiapp.core.util.IResult
import kotlinx.coroutines.flow.Flow

/**
 * Created by Himanshu Singh on 23-10-2020.
 **/
/** A Use Case that takes an argument and returns a result. */
interface IBaseUseCase<in I, out R : Any> {

    /** Executes this use case with given input. */
    suspend operator fun invoke(input: I): Flow<IResult<R>>
}
