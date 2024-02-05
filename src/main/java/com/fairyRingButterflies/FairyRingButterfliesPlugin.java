package com.fairyRingButterflies;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
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

	private static List<Integer> fairyRingIds = Arrays.asList(STANDARD_RING_ID, ZANARIS_RING_ID, POH_RING_ID);

	private List<FairyRing> fairyRings = new ArrayList<>();
	private FairyRing pohRing = null;
	private int[] defaultColours1 = null;
	private int[] defaultColours2 = null;
	private int[] defaultColours3 = null;

	private int[] defaultHouseColours1 = null;
	private int[] defaultHouseColours2 = null;
	private int[] defaultHouseColours3 = null;

	private static int stateOfRingConfigure = 0;

	@Inject
	private Client client;

	@Inject
	private FairyRingButterfliesConfig config;

	@Inject
	private ConfigManager configManager;

	@Override
	protected void startUp() throws Exception {
		applySettings();
	}

	@Override
	protected void shutDown() throws Exception {
        resetToOriginalColours();
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged) {
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN) {
			Scene scene = client.getScene();
			Tile[][] tiles = scene.getTiles()[0];
			for (int x = 0; x < Constants.SCENE_SIZE; ++x) {
				for (int y = 0; y < Constants.SCENE_SIZE; ++y) {
					Tile tile = tiles[x][y];
					if (tile == null) {
						continue;
					}
					for (GameObject gameObject : tile.getGameObjects()) {
						if (gameObject == null) {
							continue;
						}
						if (fairyRingIds.contains(gameObject.getId())) {
							addToRememberedFairyRings(gameObject);
							break;
						}
					}
				}
			}
		}
	}

	@Provides
	FairyRingButterfliesConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(FairyRingButterfliesConfig.class);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event) {
		if (event.getGroup().equals(FairyRingButterfliesConfig.CONFIG_NAME) && !event.getKey().equals("data")) {
			if (event.getKey().equals("export") && event.getNewValue().equals("true")) {
				ConfigExportImport.exportConfigToTextBox(configManager);
			} else {
				if (event.getKey().equals("import") && event.getNewValue().equals("true")) {
					ConfigExportImport.ImportConfigFromTextBox(configManager);
				}
				applySettings();
			}
		}
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
		if (!config.biomeColours() || fairyRing.biome == null) {
			if (config.removeButterflies()) {
				fairyRing.removeButterflies();
			} else {
				fairyRing.recolourButterflies(config.colourBody(), config.colourWingInner(), config.colourWingOuter());
			}
		} else {
			switch (fairyRing.biome) {
				case PLAINS:
					if (config.removeButterfliesPlains()) {
						fairyRing.removeButterflies();
					} else {
						fairyRing.recolourButterflies(config.colourBodyPlains(), config.colourWingInnerPlains(), config.colourWingOuterPlains());
					}
					break;
				case DESERT:
					if (config.removeButterfliesDesert()) {
						fairyRing.removeButterflies();
					} else {
						fairyRing.recolourButterflies(config.colourBodyDesert(), config.colourWingInnerDesert(), config.colourWingOuterDesert());
					}
					break;
				case ISLAND:
					if (config.removeButterfliesIsland()) {
						fairyRing.removeButterflies();
					} else {
						fairyRing.recolourButterflies(config.colourBodyIsland(), config.colourWingInnerIsland(), config.colourWingOuterIsland());
					}
					break;
				case CAVE:
					if (config.removeButterfliesCave()) {
						fairyRing.removeButterflies();
					} else {
						fairyRing.recolourButterflies(config.colourBodyCave(), config.colourWingInnerCave(), config.colourWingOuterCave());
					}
					break;
				case MOUNTAINS:
					if (config.removeButterfliesMountains()) {
						fairyRing.removeButterflies();
					} else {
						fairyRing.recolourButterflies(config.colourBodyMountains(), config.colourWingInnerMountains(), config.colourWingOuterMountains());
					}
					break;
				case SWAMP:
					if (config.removeButterfliesSwamp()) {
						fairyRing.removeButterflies();
					} else {
						fairyRing.recolourButterflies(config.colourBodySwamp(), config.colourWingInnerSwamp(), config.colourWingOuterSwamp());
					}
					break;
				case JUNGLE:
					if (config.removeButterfliesJungle()) {
						fairyRing.removeButterflies();
					} else {
						fairyRing.recolourButterflies(config.colourBodyJungle(), config.colourWingInnerJungle(), config.colourWingOuterJungle());
					}
					break;
				case ABYSS:
					if (config.removeButterfliesAbyss()) {
						fairyRing.removeButterflies();
					} else {
						fairyRing.recolourButterflies(config.colourBodyAbyss(), config.colourWingInnerAbyss(), config.colourWingOuterAbyss());
					}
					break;
				case EXTRAPLANAR:
					if (config.removeButterfliesExtraplanar()) {
						fairyRing.removeButterflies();
					} else {
						fairyRing.recolourButterflies(config.colourBodyExtraplanar(), config.colourWingInnerExtraplanar(), config.colourWingOuterExtraplanar());
					}
					break;
				case VOLCANIC:
					if (config.removeButterfliesVolcanic()) {
						fairyRing.removeButterflies();
					} else {
						fairyRing.recolourButterflies(config.colourBodyVolcanic(), config.colourWingInnerVolcanic(), config.colourWingOuterVolcanic());
					}
					break;
			}
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
			pohRing = new FairyRing(gameObject, true, null);
		} else {
			if (defaultColours1 == null) {
				storeOriginalColours(gameObject.getRenderable().getModel(), false);
			}
            //No way to cleanly remove from this list, so just remember at max 3 ring objects
            while (fairyRings.size() > 2) {
            	fairyRings.remove(0);
            }
			WorldPoint location = gameObject.getWorldLocation();
            fairyRings.add(new FairyRing(gameObject, false, LocationBiomeMap.getBiomeForCoordinates(location.getX(), location.getY())));
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

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event) {
		//Record that a configure action was started/completed on a ring.
		//This lets us selectively jump in and reapply the settings immediately after the model reloads from it.
		//It's possible we could enter that state incorrectly, but the load from doing so should be fairly light.
		if (event.getMenuOption().equals("Configure")) {
			stateOfRingConfigure = 1;
		} else if (event.getMenuOption().equals("Confirm") && stateOfRingConfigure == 1) {
			stateOfRingConfigure = 2;
		}
		//User's just finished logging in, so do the same as we do when a ring is configured.
		//This gives us a chance to pass over any rings that were found when logging in and recolour them now that they're rendered.
		if (event.getMenuOption().equals("Play") && event.getMenuTarget().equals("")) {
			stateOfRingConfigure = 2;
		}
	}

	@Subscribe
	public void onGameTick(GameTick event) {
		if (stateOfRingConfigure > 1) {
			applySettings();

			//Bit sloppy, but make sure the next four ticks after we think we've completed a ring configure or login, we recolour.
			//There's no event that triggers right on the configure action, but it replaces the model with default colours.
			if (stateOfRingConfigure > 5) {
				stateOfRingConfigure = 0;
			} else {
				stateOfRingConfigure++;
			}
		}
	}
}
