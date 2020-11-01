package com.himanshoe.core.navigator.event

/**
 * Created by Himanshu Singh on 24-10-2020.
 **/

/**
 * Simple base event with state of its consumption.
 */
abstract class BaseEvent {

    /**
     * Represents state of an event, true if consumed and false if not.
     */
    abstract var consumed: Boolean
}

/**
 * Used as a wrapper for data that is exposed as an event.
 */
data class Event<T>(
    val data: T,
    override var consumed: Boolean = false
) : BaseEvent()

/**
 * To consume this event. [Consumer][consumer] will be not be called if event is already
 * consumed.
 */
inline fun <T : BaseEvent> T.consume(consumer: (T) -> Unit) {
    if (!consumed) {
        consumed = true
        consumer(this)
    }
}
