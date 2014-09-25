package com.seesideproductions.blinken.controllers;

/**
 * Interface for the American DJ myDMX controller.
 *
 * While this class implements {@link Closeable}, it is still permissable to
 * reopen the interface (i.e., call {@link #open()}) after {@link #close()} has
 * been called.
 *
 * @author scoleman
 * @since 0.0.1
 */
public class MyDMXController implements Controller
{
    static
    {
        switch (System.getProperty("os.arch"))
        {
            case "i386":
            case "x86":
                System.loadLibrary("mydmx32");
                break;
            case "amd64":
            case "x86_64":
                System.loadLibrary("mydmx64");
                break;
        }
    }

    /**
     * Open a connection to the controller.
     *
     * @throws DMXException upon failure to connect to the controller
     */
    public native void open() throws DMXException;

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