import java.lang.ClassCastException
import java.time.LocalDate
import kotlin.reflect.KClass

fun main() {
    val labels: List<*> = listOf("ola", LocalDate.now(), 7, "super", "isel")
    // val strings = labels.uselessCast<String>() // No Exception
    // val strings = labels.checkedCast(String::class) // Exception
    val strings: List<String> = labels.checkedCast() // Exception
    println((strings[0]).length)
    println((strings[1]).length)
}

/*
 * Useless cast because it is not validating the
 * items of the list.
 */
fun <T : Any> List<*>.uselessCast() : List<T> {
    return this as List<T>
}
/**
 * Succeeds only if all elements of the list are
 * compatible with T.
 * Throws CastClassException otherwise.
 */
fun <T : Any> List<*>.checkedCast(klass: KClass<T>) : List<T> {
    /**
     * Not possible due to Type Erasure T does not exist in bytecode
     */
    // if(this.any { it !is T })
    forEach {
        if(it != null) {
            if(!klass.java.isAssignableFrom(it::class.java))
                throw ClassCastException("${it::class.java} not compatible with ${klass.simpleName}")
        }
    }
    return this as List<T>
}

inline fun <reified T : Any> List<*>.checkedCast() : List<T> {
    return this.checkedCast(T::class)
}