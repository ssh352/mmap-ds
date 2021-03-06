package sk.mmap;

public class Utils {
    private static int align(int size) {
        return (size + Constants.ALLOC_SIZE_ALIGNMENT_ADD)
                &
                Constants.ALLOC_SIZE_ALIGNMENT_MASK;
    }

    public static int getAllocSize(int size) {
        // Free list is maintained as doubly linked list.
        // Hence need to keep 2 handles in freed buffer.
        int tmpSize = size >= 2 * Constants.ALLOC_BUFFER_HANDLE_LENGTH ? size : 2 * Constants.ALLOC_BUFFER_HANDLE_LENGTH;
        return align(tmpSize + Constants.ALLOC_BUFFER_HEADER_LENGTH);
    }

    // Format is:
    // 24 bits unused
    // 8 bits of index into mmap'ed array of buffers
    // +
    // 32 bits of index into raw mmap'ed buffer
    public static int getArenaIndex(long handle) {
        return (int) (handle >>> 32 & 0xff);
    }

    public static int getBufferIndex(long handle) {
        return (int) handle;
    }

    public static long getHandle(int arenaIndex, int bufferIndex) {
        return ((long) arenaIndex << 32) | (long) bufferIndex;
    }

    public static int getByteBufferLength(int capacity) {
        return capacity * Constants.SIZE_OF_BYTE;
    }

    public static int getShortBufferLength(int capacity) {
        return capacity * Constants.SIZE_OF_SHORT;
    }

    public static int getIntBufferLength(int capacity) {
        // TODO: Handle overflow
        return capacity * Constants.SIZE_OF_INT;
    }

    public static int getLongBufferLength(final int capacity) {
        return capacity * Constants.SIZE_OF_LONG;
    }

    public static int getFloatBufferLength(final int capacity) {
        return capacity * Constants.SIZE_OF_FLOAT;
    }

    public static int getDoubleBufferLength(final int capacity) {
        return capacity * Constants.SIZE_OF_DOUBLE;
    }

    public static int getCharBufferLength(final int capacity) {
        return capacity * Constants.SIZE_OF_CHAR;
    }

    // Just double the capacity every time any container is full.
    public static int getNextCapacity(int capacity) {
        // TODO: Handle overflow
        return capacity * 2;
    }
}
