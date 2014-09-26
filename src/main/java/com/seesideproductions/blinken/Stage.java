package com.seesideproductions.blinken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A stage represents a collection of fixtures.
 * 
 * @author scoleman
 * @since 0.0.1
 */
public class Stage extends ArrayList<Fixture>
{
    private static final long serialVersionUID = 1L;

    HashMap<Integer, byte[]> universes = new HashMap<Integer, byte[]>();

    @Override
    public boolean add(Fixture fixture)
    {
        if (super.add(fixture))
        {
            int universe = fixture.getUniverse();
            if (!this.universes.containsKey(universe))
                this.universes.put(universe, new byte[DMX.UNIVERSE_SIZE]);

            return true;
        }

        return false;
    }

    /**
     * Render the DMX values of all fixtures on a stage.
     * 
     * @return a map of all DMX values: keys as 0-based universe indices, values
     *         as arrays of DMX values per universe
     */
    public Map<Integer, byte[]> render()
    {
        for (Fixture fixture : this)
            System.arraycopy(
                    fixture.getChannelValues(), 0,
                    this.universes.get(fixture.getUniverse()),
                    fixture.getStartingChannel(), fixture.getChannels());

        return Collections.unmodifiableMap(this.universes);
    }
}