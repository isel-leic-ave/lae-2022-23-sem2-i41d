package pt.isel

import pt.isel.IteratorState.*
import java.lang.IllegalStateException
import java.util.NoSuchElementException

fun <T : Any> chequence(block: SequenceScopeIterator<T>.(Int) -> Unit): Sequence<T> {
    return object : Sequence<T> {
        override fun iterator(): Iterator<T> {
            return SequenceScopeIterator(block)
        }
    }
}

/**
 * Based on kotlin.sequences.SequenceBuilderIterator of SequenceBuilder.kt
 */
class SequenceScopeIterator<T: Any>(private val block: SequenceScopeIterator<T>.(Int) -> Unit) : Iterator<T> {
    var label = 0
    var value: T? = null
    private var iterState = NotReady

    fun yield(newValue: T, nextLabel: Int) {
        value = newValue
        label = nextLabel
        iterState = Ready
    }

    private fun tryAdvance(): Boolean {
        return when(iterState) {
            Ready -> true
            Done -> false
            Failed -> throw IllegalStateException()
            NotReady -> {
                try {
                    iterState = Done // stays Done unless block() changes it to Ready or throws
                    this.block(label) // May change iterState to Ready
                    iterState != Done
                } catch (err: Throwable) {
                    iterState = Failed
                    throw err
                }
            }
        }
    }

    override fun hasNext() = tryAdvance()

    override fun next(): T {
        if(!tryAdvance()) throw NoSuchElementException()
        val curr = value!!
        value = null
        iterState = NotReady
        return curr
    }
}

enum class IteratorState { NotReady, Ready, Done, Failed}