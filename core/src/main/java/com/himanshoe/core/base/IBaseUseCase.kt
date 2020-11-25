package com.himanshoe.core.base

import kotlinx.coroutines.flow.Flow

/**
 * Created by Himanshu Singh on 23-10-2020.
 **/
/** A Use Case that takes an argument and returns a result. */
interface IBaseUseCase<in I, out R : Any> {

    /** Executes this use case with given input. */
     operator fun invoke(input: I): Flow<R>
}
