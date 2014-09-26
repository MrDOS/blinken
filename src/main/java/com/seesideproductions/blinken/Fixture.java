package com.seesideproductions.blinken;

/**
 * A DMX fixture.
 * 
 * Note that once constructed, its universe, starting channel index, and number
 * of channels may <em>not</em> be modified. This is enforced by the
 * finalization of the getter methods for these values. This is done to improve
 * the efficiency of updating the {@link Stage}: if fixtures can move around or
 * change in channel size without being removed from the stage, a lot more work
 * would be required to ensure the consistency of transmitted values.
 * 
 * @author scoleman
 * @since 0.0.1
 */
public abstract class Fixture
{
    private final int universe;
    private final int startingChannel;
    private final int channels;

    public Fixture(int universe, int startingChannel, int channels)
    {
        this.universe = universe;
        this.startingChannel = startingChannel;
        this.channels = channels;
    }

    public final int getUniverse()
    {
        return this.universe;
    }

    public final int getStartingChannel()
    {
        return this.startingChannel;
    }

    public final int getChannels()
    {
        return this.channels;
    }

    /**
     * Get the values for channels of the fixture. The length of the returned
     * array <em>must</em> be equal to the number returned by
     * {@link #getChannels()}.
     * 
     * @return the values for channels of the fixture
     */
    public abstract byte[] getChannelValues();
}
