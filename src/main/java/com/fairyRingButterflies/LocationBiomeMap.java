package com.fairyRingButterflies;

import java.util.HashMap;
import java.util.Map;

public class LocationBiomeMap {

    private static Map<Integer, Biome> map = null;

    public static Biome getBiomeForCoordinates(int x, int y) {
        if (map == null) {
            initialise();
        }
        if (map.containsKey(x)) {
            return map.get(x);
        } else if (map.containsKey(y)) {
            return map.get(y);
        }
        return null;
    }

    private static void initialise() {
        //Just map the rings by their X-coordinates. Only a few aren't unique, so put those in by their Y.
        map = new HashMap<>();
        map.put(2996, Biome.ISLAND); //AIQ
        map.put(2700, Biome.PLAINS); //AIR - This is an island, but making it island causes it to recolour the two nearby ones when it loads.
        map.put(2735, Biome.CAVE); //AJQ
        map.put(2780, Biome.MOUNTAINS); //AJR
        map.put(2500, Biome.ISLAND); //AJS
        map.put(3284, Biome.DESERT); //AKP
        map.put(2319, Biome.PLAINS); //AKQ
        map.put(2571, Biome.JUNGLE); //AKS
        map.put(2503, Biome.ISLAND); //ALP
        map.put(3597, Biome.SWAMP); //ALQ
        map.put(3059, Biome.ABYSS); //ALR
        map.put(2644, Biome.PLAINS); //ALS

        map.put(3410, Biome.SWAMP); //BIP
        map.put(3251, Biome.DESERT); //BIQ
        map.put(2635, Biome.PLAINS); //BIS
        map.put(2267, Biome.PLAINS); //BJP
        map.put(2650, Biome.PLAINS); //BJR
        map.put(2150, Biome.SWAMP); //BJS
        map.put(2385, Biome.JUNGLE); //BKP
        map.put(3041, Biome.PLAINS); //BKQ
        map.put(3469, Biome.SWAMP); //BKR
        map.put(2412, Biome.EXTRAPLANAR); //BKS:Zanaris
        map.put(2437, Biome.VOLCANIC); //BLP
        map.put(3351, Biome.PLAINS); //BLR - Y

        map.put(2513, Biome.ISLAND); //CIP
        map.put(2528, Biome.PLAINS); //CIQ
        map.put(1302, Biome.VOLCANIC); //CIR
        map.put(1639, Biome.MOUNTAINS); //CIS
        map.put(2705, Biome.PLAINS); //CJR
        map.put(2075, Biome.EXTRAPLANAR); //CKP
        map.put(2801, Biome.JUNGLE); //CKR
        map.put(3470, Biome.SWAMP); //CKS - Y
        map.put(3082, Biome.ISLAND); //CLP
        map.put(2738, Biome.JUNGLE); //CLR -Y
        map.put(2682, Biome.JUNGLE); //CLS

        map.put(3037, Biome.ABYSS); //DIP
        map.put(3038, Biome.EXTRAPLANAR); //DIR
        map.put(3108, Biome.PLAINS); //DIS
        map.put(2658, Biome.PLAINS); //DJP
        map.put(1455, Biome.JUNGLE); //DJR
        map.put(2900, Biome.JUNGLE); //DKP
        map.put(3129, Biome.PLAINS); //DKR
        map.put(2744, Biome.MOUNTAINS); //DKS
        map.put(3423, Biome.DESERT); //DLQ
        map.put(2213, Biome.SWAMP); //DLR
        map.put(9824, Biome.CAVE); //DLS - Y
    }
}
