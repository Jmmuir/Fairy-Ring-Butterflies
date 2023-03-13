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
	private static Integer ARCEUUS_RING_ID = 14839;

	private static List<Integer> fairyRingIds = Arrays.asList(STANDARD_RING_ID, ZANARIS_RING_ID, POH_RING_ID, ARCEUUS_RING_ID);

	private List<FairyRing> fairyRings = new ArrayList<>();
	private FairyRing pohRing = null;
	private int[] defaultColours1 = null;
	private int[] defaultColours2 = null;
	private int[] defaultColours3 = null;

	private int[] defaultHouseColours1 = null;
	private int[] defaultHouseColours2 = null;
	private int[] defaultHouseColours3 = null;

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
        resetToOriginalColours();
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
			for (FairyRing fairyRing : fairyRings) {
                applySettingsToRing(fairyRing);
			}
		}
		if (pohRing != null) {
			applySettingsToRing(pohRing);
		}
	}

	private void applySettingsToRing(FairyRing fairyRing) {
		if (config.removeButterflies()) {
			fairyRing.removeButterflies();
		} else {
			fairyRing.recolourButterflies(config);
		}
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned spawnEvent) {
		GameObject spawnedObject = spawnEvent.getGameObject();
		if (fairyRingIds.contains(spawnedObject.getId())) {
			addToRememberedFairyRings(spawnedObject);
			applySettings();
		}
	}

	private void addToRememberedFairyRings(GameObject gameObject) {
		//Have to hold onto last 3 rings, as that's how many can be on screen.
		//Hold the POH one separately as it's a different model.
		if (gameObject.getId() == POH_RING_ID) {
			if (defaultHouseColours1 == null) {
				storeOriginalColours(gameObject.getRenderable().getModel(), true);
			}
			pohRing = new FairyRing(gameObject, true);
		} else {
			if (defaultColours1 == null) {
				storeOriginalColours(gameObject.getRenderable().getModel(), false);
			}
            //No way to cleanly remove from this list, so just remember at max 3 ring objects
            while (fairyRings.size() > 2) {
            	fairyRings.remove(0);
            }
            fairyRings.add(new FairyRing(gameObject, false));
		}
	}

	private void storeOriginalColours(Model model, boolean isPOH) {
		int[] colours1 = model.getFaceColors1();
		int[] colours2 = model.getFaceColors2();
		int[] colours3 = model.getFaceColors3();
		if (isPOH) {
			defaultHouseColours1 = colours1.clone();
			defaultHouseColours2 = colours2.clone();
			defaultHouseColours3 = colours3.clone();
		} else {
			defaultColours1 = colours1.clone();
			defaultColours2 = colours2.clone();
			defaultColours3 = colours3.clone();
		}
	}

	private void resetToOriginalColours() {
		if (!fairyRings.isEmpty() && defaultColours1 != null) {
			for (FairyRing fairyRing : fairyRings) {
                Model model = fairyRing.gameObject.getRenderable().getModel();
                int[] colours1 = model.getFaceColors1();
                int[] colours2 = model.getFaceColors2();
                int[] colours3 = model.getFaceColors3();
            	for (int i = 0; i < colours1.length && i < defaultColours1.length; i++) {
            		colours1[i] = defaultColours1[i];
            		colours2[i] = defaultColours2[i];
            		colours3[i] = defaultColours3[i];
				}
			}
		}
		if (pohRing != null && defaultHouseColours1 != null) {
			Model model = pohRing.gameObject.getRenderable().getModel();
			int[] colours1 = model.getFaceColors1();
			int[] colours2 = model.getFaceColors2();
			int[] colours3 = model.getFaceColors3();
			for (int i = FairyRing.POH_COLOUR_REGION_OFFSET; i < colours1.length && i < defaultHouseColours1.length; i++) {
				colours1[i] = defaultHouseColours1[i];
				colours2[i] = defaultHouseColours2[i];
				colours3[i] = defaultHouseColours3[i];
			}
		}
	}

}
