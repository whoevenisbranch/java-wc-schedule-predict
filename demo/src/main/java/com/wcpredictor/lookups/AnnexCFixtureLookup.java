package com.wcpredictor.lookups;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class AnnexCFixtureLookup 
{

    private static final List<String> GROUP_WINNERS = List.of("1A", "1B", "1D", "1E", "1G", "1I", "1K", "1L");
    private static final String THIRD_PLACE_PREFIX = "3";

    private AnnexCFixtureLookup()
    {
        //Intentionally left blank.
    }

    public static Map<String, String> getR32FixtureSet(String key) throws IOException {

        try (BufferedReader reader = new BufferedReader(Files.newBufferedReader(Path.of("./annex.csv"))))
        {  
            String line;
            while ((line = reader.readLine()) != null)
            {

                String[] arr = line.split(",");

                String rowKey = createSortedFixtureSetKey(arr);
                if (rowKey.equals(key)) 
                {
                    return extractFixtures(arr);
                }
                
            }

            throw new NoSuchElementException("No fixture set found for key: " + key);

        }

    }

    public static String createSortedFixtureSetKey(String[] arr)
    {
        return Arrays.stream(arr).sorted().collect(Collectors.joining());
    }

    public static Map<String, String> extractFixtures(String[] arr)
    {
        if (arr.length != GROUP_WINNERS.size())
        {
            throw new IllegalArgumentException(
                    String.format("Expected %d best 3rd place teams. Got: %d", GROUP_WINNERS.size(), arr.length)
            );
        }

        Map<String, String> fixtures = new HashMap<>(GROUP_WINNERS.size());

        for (int i = 0; i < arr.length; i++) 
        {

            String home = GROUP_WINNERS.get(i);
            String away = THIRD_PLACE_PREFIX + arr[i];

            fixtures.put(home, away);
        }

        return fixtures;
    }

}
