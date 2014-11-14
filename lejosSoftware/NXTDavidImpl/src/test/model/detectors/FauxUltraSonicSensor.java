package test.model.detectors;

import lejos.util.Delay;

/**
 * Created by Stephen on 11/14/2014.
 * In project: NXTSoftware
 */
public class FauxUltraSonicSensor
{
    private byte[] byteBuff = new byte[8];

    public int ping()
    {
        return 1;
    }

    public int getDistances( int[] receivedData )
    {
        return getDistances(receivedData, 0, receivedData.length);
    }

    private int getDistances( int[] dist, int off, int len )
    {
        if (len <= 0)
        {
            if (len == 0)
                return 0;

            throw new IllegalArgumentException("len is negative");
        }

        int delay = 50; // ms delay between reception, which is 50 on pinging
        int maxlen = 8;

        if (len > maxlen)
            len = maxlen;

        int i;
        for (i = 0; i < len && byteBuff[i] != -1; i++)
            dist[off + i] = byteBuff[i] & 0xff;

        return i;
    }

    private void waitUntil( long when )
    {
        long delayPeriod = when - System.currentTimeMillis();
        Delay.msDelay(delayPeriod );
    }
}
