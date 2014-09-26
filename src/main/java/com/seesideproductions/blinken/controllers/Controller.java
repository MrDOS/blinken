package com.seesideproductions.blinken.controllers;

import java.io.Closeable;
import java.util.Map;

/**
 * A DMX controller.
 *
 * @author scoleman
 * @since 0.0.1
 */
public abstract class Controller implements Closeable
{
    /**
     * Set the values on all DMX channels in a universe. {@code values} is
     * expected to be of length 512; it is up to the implementation to decide
     * what to do in any other event.
     *
     * It is expected that this method may need to be called many times per
     * second (20-30Hz), or more in systems with multiple universes. As such, it
     * is expected of implementations that this method will not do anything
     * which could be deemed computationally intensive.
     *
     * @param universe the 0-based DMX universe to control
     * @param values the values to set for all channels in the universe
     * @throws DMXException in the event of failure to set the values, if the
     *         controller fails to set the values, if the values
     *         are inappropriate, etc.
     */
    public abstract void set(int universe, byte[] values) throws DMXException;

    /**
     * The same as (@link {@link #set(int, byte[])}), but takes multiple
     * universes of values simultaneously.
     * 
     * @param values the universes to set
     * @throws DMXException in the event of failure to set the values, if the
     *         controller fails to set the values, if the values
     *         are inappropriate, etc.
     */
    public void set(Map<Integer, byte[]> values) throws DMXException
    {
        for (Map.Entry<Integer, byte[]> universe : values.entrySet())
        {
            this.set(universe.getKey(), universe.getValue());
        }
    }
}