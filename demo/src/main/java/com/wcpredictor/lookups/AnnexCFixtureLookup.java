package com.wcpredictor.lookups;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnnexCFixtureLookup 
{

    public static final String[] groupWinners = new String[]{"1A", "1B", "1D", "1E", "1G", "1I", "1K", "1L"};

    public static Map<String, String> getR32FixtureSet(String key) 
    {

        Map<String, String> fixtures = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("./annex.csv")))
        {  
            String line;
            while ((line = reader.readLine()) != null) 
            {

                String[] arr = line.split(",");

                String rowKey = extractKey(Arrays.copyOf(arr, arr.length));
                if (rowKey.equals(key)) 
                {
                    fixtures = extractFixtures(key, arr);
                    break;
                }
                
            }

            reader.close();
            return fixtures;
        } 
        catch (Exception e) 
        {
            System.err.println(e.getMessage());
            return fixtures;
        }
    }

    public static String extractKey(String[] arr) 
    {
        Arrays.sort(arr);
        return String.join("", arr);
    }

    public static Map<String, String> extractFixtures(String key, String[] arr) 
    {
        Map<String, String> fixtures = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++) 
        {

            String home = groupWinners[i];
            String away = "3" + arr[i];

            fixtures.put(home, away);
        }

        return fixtures;
    }

}
