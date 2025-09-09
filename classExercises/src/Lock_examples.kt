import java.util.concurrent.atomic.AtomicBoolean

// TASLock (Test-And-Set Lock)
class TASLock {
    private val state = AtomicBoolean(false)

    fun lock() {
        while (state.getAndSet(true)) {
            // Spin while locked
        }
    }

    fun unlock() {
        state.set(false)
    }
}

// TTASLock (Test-Test-And-Set Lock)
class TTASLock {
    private val state = AtomicBoolean(false)

    fun lock() {
        while (true) {
            // First test - local spinning
            while (state.get()) {
                // Spin locally while locked
            }
            // Second test - atomic attempt
            if (!state.getAndSet(true)) {
                return
            }
        }
    }

    fun unlock() {
        state.set(false)
    }
}

/*
TTAS is faster because:

It reads from local cache first (cheap), only writing to memory when needed. TAS constantly writes to memory (expensive),
causing traffic on the memory bus and invalidating other CPUs' caches.

TTAS = check locally → if free, try to grab
TAS = constantly try to grab (slows everyone down)
*/