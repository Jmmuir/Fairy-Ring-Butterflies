package com.fairyRingButterflies;

import net.runelite.api.GameObject;
import net.runelite.api.Model;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class FairyRing {
    private static List<Integer> INNER_COLOUR_POSITIONS = Arrays.asList(148,149,150,151,152,153,154,155,156,157,170,171,172,173,174,199,200,201,202,203);
    private static List<Integer> OUTER_COLOUR_POSITIONS = Arrays.asList(127,128,129,130,134,135,158,175,177,178,179,180,181,182,183,184,188,189,190,191,192);
    private static List<Integer> EDGE_COLOUR_POSITIONS = Arrays.asList(159,162,163,164,165,166,167,168,169,176,196);
    private static List<Integer> REVERSE_COLOUR_POSITIONS = Arrays.asList(125,126,131,132,133,136,160,161,185,186,187,193,194,195,197,198);
    
    public static int POH_COLOUR_REGION_OFFSET = 2469;

    GameObject gameObject;
    boolean isPOH;
    Biome biome;

    public FairyRing (GameObject gameObject, boolean isPOH, Biome biome) {
        this.gameObject = gameObject;
        this.isPOH = isPOH;
        this.biome = biome;
    }

    private boolean isButterflyBody(int arrayPosition) {
        if (isPOH) {
            arrayPosition = arrayPosition - POH_COLOUR_REGION_OFFSET;
        }
        if (arrayPosition < 116) {
            return false;
        } else if (arrayPosition < 125) {
            return true;
        } else if (arrayPosition < 137) {
            return false;
        } else if (arrayPosition < 148) {
            return true;
        } else if (arrayPosition < 204) {
            return false;
        } else if (arrayPosition < 213) {
            return true;
        } else if (arrayPosition < 225) {
            return false;
        } else if (arrayPosition < 236) {
            return true;
        } else if (arrayPosition < 292) {
            return false;
        } else if (arrayPosition < 301) {
            return true;
        } else if (arrayPosition < 313) {
            return false;
        } else if (arrayPosition < 324) {
            return true;
        }
        return false;
    }

    public void recolourButterflies(Color bodyColour, Color innerColour, Color outerColour) {
        int hsbColour1 = colorToRs2hsb(bodyColour.getRed(), bodyColour.getGreen(), bodyColour.getBlue());
        int hsbColour2 = colorToRs2hsb(innerColour.getRed(), innerColour.getGreen(), innerColour.getBlue());
        int hsbColour3 = colorToRs2hsb(outerColour.getRed(), outerColour.getGreen(), outerColour.getBlue());
        applyColourCode(hsbColour1, hsbColour2, hsbColour3);
    }

    public void removeButterflies() {
        applyColourCode(-2,-2, -2);
    }

    private void applyColourCode(int bodyColour, int innerColour, int outerColour) {
        Model model = gameObject.getRenderable().getModel();
        int[] colours1 = model.getFaceColors1();
        int[] colours2 = model.getFaceColors2();
        int[] colours3 = model.getFaceColors3();
        int i = 0;
        int relativePos = 0;
        if (isPOH) {
            i = POH_COLOUR_REGION_OFFSET;
            relativePos = POH_COLOUR_REGION_OFFSET;
        }
        for (; i < colours1.length; i++) {
            if (isButterflyBody(i)) {
                colours1[i] = bodyColour;
                colours2[i] = bodyColour;
                colours3[i] = bodyColour;
            } else if (i > 115) {
                if (INNER_COLOUR_POSITIONS.contains(i - relativePos)) {
                    recolourRegion(i, colours1, colours2, colours3, innerColour);
                } else if (OUTER_COLOUR_POSITIONS.contains(i - relativePos) || REVERSE_COLOUR_POSITIONS.contains(i - relativePos) || EDGE_COLOUR_POSITIONS.contains(i - relativePos)) {
                    recolourRegion(i, colours1, colours2, colours3, outerColour);
                }
            }
        }
    }

    private int colorToRs2hsb(int red, int green, int blue) {
        float[] hsbVals = Color.RGBtoHSB(red, green, blue, null);

        //"Correct" the brightness level to avoid going to white at full saturation, or having a low brightness at
        //low saturation
        hsbVals[2] -= Math.min(hsbVals[1], hsbVals[2] / 2);
        //hsbVals[2] = 0.1f;

        int encode_hue = (int)(hsbVals[0] * 63);
        int encode_saturation = (int)(hsbVals[1] * 7);
        int encode_brightness = (int)(hsbVals[2] * 127);
        return (encode_hue << 10) + (encode_saturation << 7) + (encode_brightness);
    }
    
    private void recolourRegion(int startPosition, int[] colours1, int[] colours2, int[] colours3, int colourToApply) {
        colours1[startPosition] = colourToApply;
        colours1[startPosition + 88] = colourToApply;
        colours1[startPosition + 176] = colourToApply;
        colours2[startPosition] = colourToApply;
        colours2[startPosition + 88] = colourToApply;
        colours2[startPosition + 176] = colourToApply;
        colours3[startPosition] = colourToApply;
        colours3[startPosition + 88] = colourToApply;
        colours3[startPosition + 176] = colourToApply;
    }

}
