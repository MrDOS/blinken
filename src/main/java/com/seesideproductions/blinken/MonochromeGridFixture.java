package com.seesideproductions.blinken;

/**
 * A fixture which presents values in a grid.
 * 
 * @author scoleman
 * @since 0.0.1
 */
public class MonochromeGridFixture extends Fixture
{
    private int[] mapping;
    private byte[] values;

    public MonochromeGridFixture(int universe, int startingChannel, int width, int height)
    {
        super(universe, startingChannel, width * height);

        this.mapping = new int[width * height];
        this.values = new byte[width * height];
    }

    public void setMapping(int[] mapping)
    {
        this.mapping = mapping;
    }

    public void setDisplayValues(boolean[] values)
    {
        for (int i = 0; i < values.length; i++)
        {
            int channel = this.mapping[i];
            byte value = values[i] ? (byte) 0xFF : (byte) 0x00;
            this.values[channel] = value;
        }
    }

    @Override
    public byte[] getChannelValues()
    {
        return this.values;
    }
}
