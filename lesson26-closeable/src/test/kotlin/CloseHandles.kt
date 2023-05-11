import org.junit.jupiter.api.Test
import java.io.BufferedWriter
import java.io.Closeable
import java.io.File
import java.io.FileOutputStream
import java.lang.ref.Cleaner
import java.nio.channels.OverlappingFileLockException
import java.util.concurrent.ThreadFactory
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

private const val OUT = "out.txt"

class CloseHandles {
    /**
     * The garbage collector runs in parallel, and it may take
     * some time after invoking System.gc() before the garbage
     * collector determines the object is no longer referenced.
     *
     * ALERT: DO NOT use this in your code!!!!
     */
    private fun tryGc() {
        for (i in 10 downTo 1) {
            System.gc()
            System.runFinalization()
            System.gc()
            Thread.sleep(500L)
        }
    }

    fun openAndWriteAndFlush() {
        // Acquires a lock on the given region of this channel's file.
        FileOutputStream(OUT)
            .also {  it.channel.lock() }
            .bufferedWriter()
            .also {
                it.write("ola isel");
                it.flush()
            }
    }

    @Test
    fun `fail to acquire a lock on locked file and succeed after GC run`() {
        openAndWriteAndFlush()
        assertEquals("ola isel", File(OUT).readText())
        assertFailsWith<OverlappingFileLockException> {
            FileOutputStream(OUT).channel.lock()
        }
        // ALERT: DO NOT use this in your code!!!!
        tryGc()
        //
        /**
         * If GC cleans the FileOutputStream and the handle and
         * the lock are both released then we may open a new
         * OutputStream and acquire a new lock.
         */
        FileOutputStream(OUT).channel.lock()
    }

    @Test
    fun `fail to acquire a lock on locked file and succeed after close it`() {
        var outStream:FileOutputStream? = null
        try {
            // Acquires a lock on the given region of this channel's file.
            outStream = FileOutputStream(OUT).also { it.channel.lock() }
            //...
            //...
            assertFailsWith<OverlappingFileLockException> {
                FileOutputStream(OUT).channel.lock()
            }
        } finally {
            outStream?.close()
        }
        /**
         * We explicitly closed the OutputStream and released the lock.
         * Then we can acquire a new lock.
         */
        FileOutputStream(OUT).channel.lock()
    }

    fun openAndWriteInUse() {
        // Acquires a lock on the given region of this channel's file.
        FileOutputStream(OUT)
            .also {  it.channel.lock() }
            .bufferedWriter()
            .use {
                assertFailsWith<OverlappingFileLockException> {
                    FileOutputStream(OUT).channel.lock()
                }
                it.write("ola isel");
            }
    }

    @Test
    fun `fail to acquire a lock on locked file and succeed out of the use scope`() {
        openAndWriteInUse()
        assertEquals("ola isel", File(OUT).readText())
        /*
         * We explicitly closed the OutputStream and released the lock.
         * Then we can acquire a new lock.
         */
        FileOutputStream(OUT).channel.lock()
    }
    @Test
    fun `ExclusiveWriter fail to acquire a lock on locked file and succeed out of the use scope`() {
        ExclusiveWriter(OUT).use {
            assertFailsWith<OverlappingFileLockException> {
                FileOutputStream(OUT).channel.lock()
            }
            it.write("ola isel")
        }
        assertEquals("ola isel", File(OUT).readText())
        /*
         * We explicitly closed the OutputStream and released the lock.
         * Then we can acquire a new lock.
         */
        FileOutputStream(OUT).channel.lock()
    }

    fun exclusiveWrite() {
        ExclusiveWriter(OUT).write("ola isel")
    }
    @Test
    fun `ExclusiveWriter write something and check after GC the buffer is flushed`() {
        exclusiveWrite()
        tryGc()
        assertEquals("ola isel", File(OUT).readText())
    }

}

class ExclusiveWriter(path: String) : Closeable{
    companion object {
        val cleaner: Cleaner = Cleaner.create()
    }

    private val writer = FileOutputStream(path)
        .also {  it.channel.lock() }
        .bufferedWriter()

    val cleanable = cleaner.register(this, ExclusiveWriterCleaner(writer))

    override fun close() {
        cleanable.clean()
    }

    fun write(s: String) {
        writer.write(s)
    }
}

class ExclusiveWriterCleaner(val writer: BufferedWriter) : Runnable {
    override fun run() {
        println("Running cleaner")
        writer.close() // To auto flush pending buffer
    }

}
