package com.fairyRingButterflies;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@PluginDescriptor(
	name = "Fairy Ring Butterflies"
)
public class FairyRingButterfliesPlugin extends Plugin {

	private static Integer STANDARD_RING_ID = 29495;
	private static Integer ZANARIS_RING_ID = 29560;
	private static Integer POH_RING_ID = 29228;

	private static List<Integer> INNER_COLOUR_POSITIONS = Arrays.asList(148,149,150,151,152,153,154,155,156,157,170,171,172,173,174,199,200,201,202,203);
	private static List<Integer> OUTER_COLOUR_POSITIONS = Arrays.asList(127,128,129,130,134,135,158,175,177,178,179,180,181,182,183,184,188,189,190,191,192);
	private static List<Integer> EDGE_COLOUR_POSITIONS = Arrays.asList(159,162,163,164,165,166,167,168,169,176,196);
	private static List<Integer> REVERSE_COLOUR_POSITIONS = Arrays.asList(125,126,131,132,133,136,160,161,185,186,187,193,194,195,197,198);

	//POH Fairy ring is excluded atm, need to isolate colour regions for the butterflies
	private static List<Integer> fairyRingIds = Arrays.asList(STANDARD_RING_ID, ZANARIS_RING_ID);

	private List<GameObject> fairyRings = new ArrayList<>();
	private int[] defaultColours1 = null;
	private int[] defaultColours2 = null;
	private int[] defaultColours3 = null;

	@Inject
	private Client client;

	@Inject
	private FairyRingButterfliesConfig config;

	@Override
	protected void startUp() throws Exception
	{
		applySettings();
	}

	@Override
	protected void shutDown() throws Exception
	{
		if (!fairyRings.isEmpty()) {
			for (GameObject fairyRing : fairyRings) {
				Model model = fairyRing.getRenderable().getModel();
				int[] colours1 = model.getFaceColors1();
				int[] colours2 = model.getFaceColors2();
				int[] colours3 = model.getFaceColors3();
				for (int i = 0; i < colours1.length; i++) {
					colours1[i] = defaultColours1[i];
					colours2[i] = defaultColours2[i];
					colours3[i] = defaultColours3[i];
				}
			}
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			//once we have known areas containing fairy rings, apply settings on login.
		}
	}

	@Provides
	FairyRingButterfliesConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(FairyRingButterfliesConfig.class);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event) {
		applySettings();
	}

	private void applySettings() {
		if (!fairyRings.isEmpty()) {
			for (GameObject fairyRing : fairyRings) {
				if (config.removeButterflies()) {
					removeButterflies(fairyRing);
				} else {
					recolourButterflies(fairyRing);
				}
			}
		}
	}
	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned spawnEvent) {
		GameObject spawnedObject = spawnEvent.getGameObject();
		if (fairyRingIds.contains(spawnedObject.getId()) && !fairyRings.contains(spawnedObject)) {
			addToRememberedFairyRings(spawnedObject);
			applySettings();
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

	private boolean isButterflyBody(int arrayPosition) {
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

	private void recolourButterflies(GameObject fairyRing) {
		Model model = fairyRing.getRenderable().getModel();
		Color bodyColour = config.colourBody();
		Color innerColour = config.colourWingInner();
		Color outerColour = config.colourWingOuter();
		int hsbColour1 = colorToRs2hsb(bodyColour.getRed(), bodyColour.getGreen(), bodyColour.getBlue());
		int hsbColour2 = colorToRs2hsb(innerColour.getRed(), innerColour.getGreen(), innerColour.getBlue());
		int hsbColour3 = colorToRs2hsb(outerColour.getRed(), outerColour.getGreen(), outerColour.getBlue());
		int[] colours1 = model.getFaceColors1();
		if (defaultColours1 == null) {
			defaultColours1 = colours1.clone();
		}
		int[] colours2 = model.getFaceColors2();
		if (defaultColours2 == null) {
			defaultColours2 = colours2.clone();
		}
		int[] colours3 = model.getFaceColors3();
		if (defaultColours3 == null) {
			defaultColours3 = colours3.clone();
		}
		for (int i = 0; i < colours1.length; i++) {
			if (isButterflyBody(i)) {
				colours1[i] = hsbColour1;
				colours2[i] = hsbColour1;
				colours3[i] = hsbColour1;
			} else if (i > 115) {
				if (INNER_COLOUR_POSITIONS.contains(i)) {
					colours1[i] = hsbColour2;
					colours1[i + 88] = hsbColour2;
					colours1[i + 176] = hsbColour2;
					colours2[i] = hsbColour2;
					colours2[i + 88] = hsbColour2;
					colours2[i + 176] = hsbColour2;
					colours3[i] = hsbColour2;
					colours3[i + 88] = hsbColour2;
					colours3[i + 176] = hsbColour2;
				} else if (OUTER_COLOUR_POSITIONS.contains(i) || REVERSE_COLOUR_POSITIONS.contains(i) || EDGE_COLOUR_POSITIONS.contains(i)) {
					colours1[i] = hsbColour3;
					colours1[i + 88] = hsbColour3;
					colours1[i + 176] = hsbColour3;
					colours2[i] = hsbColour3;
					colours2[i + 88] = hsbColour3;
					colours2[i + 176] = hsbColour3;
					colours3[i] = hsbColour3;
					colours3[i + 88] = hsbColour3;
					colours3[i + 176] = hsbColour3;
				}
			}
		}
	}

	private void removeButterflies(GameObject fairyRing) {
		Model model = fairyRing.getRenderable().getModel();
		int[] colours1 = model.getFaceColors1();
		int[] colours2 = model.getFaceColors2();
		int[] colours3 = model.getFaceColors3();
		for (int i = 0; i < colours1.length; i++) {
				colours1[i] = -1;
				colours2[i] = -1;
				colours3[i] = -1;
		}
	}

	private void addToRememberedFairyRings(GameObject newRing) {
		//No way to cleanly remove from this list, so remember at max 3 ring objects
		while (fairyRings.size() > 2) {
			fairyRings.remove(0);
		}
		fairyRings.add(newRing);
	}
}