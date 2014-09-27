package com.seesideproductions.blinken.controllers;

/**
 * Interface for DMX controllers accessible via the {@link DasHard2006.dll}
 * library.
 *
 * While this class implements {@link Closeable}, it is still permissable to
 * reopen the interface (i.e., call {@link #open()}) after {@link #close()} has
 * been called.
 *
 * @author scoleman
 * @since 0.0.1
 */
public class DasController extends Controller
{
    static
    {
        System.loadLibrary("DasJni");
    }

    /**
     * Open a connection to the controller.
     *
     * @throws DMXException upon failure to connect to the controller
     */
    public native void open();

    /**
     * Close a connection to the controller.
     */
    public native void close();

    public void set(int universe, byte[] values) throws DMXException
    {
        if (universe > 0)
            throw new DMXException("Only one DMX universe is supported by this controller.");

        this.set(values);
    }

    /**
     * Actually set the DMX values.
     *
     * @param values the values to set
     */
    private native void set(byte[] values);
}