package pt.isel


fun <T> Sequence<T>.collapse(): Sequence<T> {
    TODO("Not yet implemented")
}

fun <T> Sequence<T>.interleave(other: Sequence<T>): Sequence<T> {
    TODO("Not yet implemented")
}

fun <T> Sequence<T>.window(size: Int): Sequence<Sequence<T>> {
    TODO("Not yet implemented")
}

/**
 * Eager implementation of concat for Iterable sequences.
 */
fun <T> Iterable<T>.concat(other: Iterable<T>): Iterable<T> {
    val res = mutableListOf<T>()
    for (item in this) { res.add(item) }
    for (item in other) { res.add(item) }
    return res
}

fun <T> Sequence<T>.concat(other: Sequence<T>): Sequence<T> {
    return sequence {
        for (item in this@concat) { yield(item) }
        for (item in other) { yield(item) }
    }
}



/**
 * Lazy implementation of concat for Iterable sequences.
 */

fun <T> Sequence<T>.concatLazy(other: Sequence<T>): Sequence<T> {
    return object: Sequence<T> {
        override fun iterator(): Iterator<T> {
            return SequenceConcatLazy(this@concatLazy, other)
        }
    }
}

class SequenceConcatLazy<T>(source: Sequence<T>, other: Sequence<T>): Iterator<T> {
    val iterator = source.iterator()
    private val otherIterator = other.iterator()

    override fun hasNext(): Boolean = iterator.hasNext() || otherIterator.hasNext()
    override fun next(): T = if (iterator.hasNext()) iterator.next() else otherIterator.next()
}
