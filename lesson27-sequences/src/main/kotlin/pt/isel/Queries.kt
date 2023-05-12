package pt.isel

import java.util.NoSuchElementException
import java.util.Optional

fun <T, R> Iterable<T>.convert(transform: (T) -> R): Iterable<R> {
    val destination = mutableListOf<R>()
    for (item in this)
        destination.add(transform(item))
    return destination
}

fun <T, R> Sequence<T>.convert(transform: (T) -> R): Sequence<R> {
    return sequence {
        for (item in this@convert)
            yield(transform(item))
    }
    /*
    return object : Sequence<R> {
        override fun iterator(): Iterator<R> {
            return IteratorConvert(this@convert, transform)
        }
    }
    */
}

class IteratorConvert<T, R>(source: Sequence<T>, val transform: (T) -> R) : Iterator<R> {
    val iter = source.iterator()
    override fun hasNext() = iter.hasNext()
    override fun next(): R = transform(iter.next())
}


fun <T> Iterable<T>.where(predicate: (T) -> Boolean): Iterable<T> {
    val destination = mutableListOf<T>()
    for (item in this)
        if(predicate(item))
            destination.add(item)
    return destination
}

fun <T> Sequence<T>.where(predicate: (T) -> Boolean): Sequence<T> {
    return sequence {
        for (item in this@where)
            if(predicate(item))
                yield(item)
    }
    /*
    return object : Sequence<T> {
        override fun iterator(): Iterator<T> {
            return IteratorFilter(this@where, predicate)
        }
    }
    */
}

class IteratorFilter<T>(source: Sequence<T>, val predicate: (T) -> Boolean) : Iterator<T> {
    private val iter = source.iterator()
    private var curr: Optional<T>? = null

    private fun advance() : Optional<T> {
        val now = curr
        if(now!= null && now.isEmpty) throw NoSuchElementException()
        while (iter.hasNext()) {
            val item = iter.next()
            if(predicate(item))
                return Optional.of(item)
        }
        return Optional.empty()
    }

    override fun hasNext(): Boolean {
        val now = curr
        if(now != null) return now.isPresent
        curr = advance()
        return curr?.isPresent ?: false
    }

    override fun next(): T {
        if(curr == null) curr = advance() // For cases of two consecutive calls to next() without hasNext
        val item = curr?.get() ?: throw NoSuchElementException()
        curr = null
        return item
    }

}
