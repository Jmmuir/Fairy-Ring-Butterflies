package com.fairyRingButterflies;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import java.awt.*;

import static com.fairyRingButterflies.FairyRingButterfliesConfig.CONFIG_NAME;


@ConfigGroup(CONFIG_NAME)
public interface FairyRingButterfliesConfig extends Config
{

	String CONFIG_NAME = "fairyRingButterflies";

	@ConfigSection(
			name = "Behaviour",
			description = "Flags which determine plugin behaviour",
			position = 0
	)
	String behaviourSection = "behaviour";

	@ConfigSection(
			name = "Colours",
			description = "Colours to apply to fairy ring butterflies globally",
			position = 1
	)
	String colourSection = "colour";

	@ConfigSection(
			name = "Plains Colours",
			description = "colour overrides to apply to butterflies in plains",
			position = 2,
			closedByDefault = true
	)
	String plainsSection = "plainsColours";
	@ConfigSection(
			name = "Desert Colours",
			description = "colour overrides to apply to butterflies in deserts",
			position = 3,
			closedByDefault = true
	)
	String desertSection = "desertColours";
	@ConfigSection(
			name = "Islands Colours",
			description = "colour overrides to apply to butterflies on islands",
			position = 4,
			closedByDefault = true
	)
	String islandSection = "islandColours";
	@ConfigSection(
			name = "Cave Colours",
			description = "colour overrides to apply to butterflies in caves",
			position = 5,
			closedByDefault = true
	)
	String caveSection = "caveColours";
	@ConfigSection(
			name = "Mountain Colours",
			description = "colour overrides to apply to butterflies in mountains",
			position = 6,
			closedByDefault = true
	)
	String mountainsSection = "mountainsColours";
	@ConfigSection(
			name = "Swamp Colours",
			description = "colour overrides to apply to butterflies in swamps",
			position = 7,
			closedByDefault = true
	)
	String swampSection = "swampColours";
	@ConfigSection(
			name = "Jungle Colours",
			description = "colour overrides to apply to butterflies in jungles",
			position = 8,
			closedByDefault = true
	)
	String jungleSection = "jungleColours";
	@ConfigSection(
			name = "Abyss Colours",
			description = "colour overrides to apply to butterflies in the abyss",
			position = 9,
			closedByDefault = true
	)
	String abyssSection = "abyssColours";
	@ConfigSection(
			name = "Extraplanar Colours",
			description = "colour overrides to apply to butterflies in the outer realms",
			position = 10,
			closedByDefault = true
	)
	String extraplanarSection = "extraplanarColours";
	@ConfigSection(
			name = "Volcanic Colours",
			description = "colour overrides to apply to butterflies in volcanic areas",
			position = 11,
			closedByDefault = true
	)
	String volcanicSection = "volcanicColours";
	@ConfigSection(
			name = "Export/Import",
			description = "Export or Import your colour settings",
			position = 12,
			closedByDefault = true
	)
	String exportImport = "exportImport";


	@ConfigItem(
			keyName = "removeButterflies",
			name = "Remove Butterflies",
			description = "Remove butterflies from every fairy ring",
			position = 0,
			section = behaviourSection
	)
	default boolean removeButterflies()
	{
		return false;
	}

	@ConfigItem(
			keyName = "biomeColours",
			name = "Use Biome Overrides",
			description = "Use the biome specific colour overrides, only using the default colours for the POH ring, or newly added ones.",
			position = 1,
			section = behaviourSection
	)
	default boolean biomeColours()
	{
		return true;
	}


	@ConfigItem(
			keyName = "colourWingInner",
			name = "Spot Colour",
			description = "Colour of the spots on the inside of the butterfly wings",
			position = 0,
			section = colourSection
	)
	default Color colourWingInner()
	{
		return new Color(-675597);
	}
	@ConfigItem(
			keyName = "colourWingOuter",
			name = "Wing Colour",
			description = "Main colour of the butterfly wings",
			position = 1,
			section = colourSection
	)
	default Color colourWingOuter()
	{
		return new Color(-7784534);
	}
	@ConfigItem(
			keyName = "colourBody",
			name = "Body Colour",
			description = "Colour of the itty bitty butterfly bodies",
			position = 2,
			section = colourSection
	)
	default Color colourBody()
	{
		return new Color(-15463673);
	}


	@ConfigItem(
			keyName = "removeButterfliesPlains",
			name = "Remove Butterflies",
			description = "Remove butterflies from plains fairy rings",
			position = 0,
			section = plainsSection
	)
	default boolean removeButterfliesPlains()
	{
		return false;
	}
	@ConfigItem(
			keyName = "colourWingInnerPlains",
			name = "Spot Colour",
			description = "Colour of the spots on the inside of the butterfly wings",
			position = 1,
			section = plainsSection
	)
	default Color colourWingInnerPlains()
	{
		return new Color(-1);
	}
	@ConfigItem(
			keyName = "colourWingOuterPlains",
			name = "Wing Colour",
			description = "Main colour of the butterfly wings",
			position = 2,
			section = plainsSection
	)
	default Color colourWingOuterPlains()
	{
		return new Color(-15977106);
	}
	@ConfigItem(
			keyName = "colourBodyPlains",
			name = "Body Colour",
			description = "Colour of the itty bitty butterfly bodies",
			position = 3,
			section = plainsSection
	)
	default Color colourBodyPlains()
	{
		return new Color(-15791611);
	}

	@ConfigItem(
			keyName = "removeButterfliesDesert",
			name = "Remove Butterflies",
			description = "Remove butterflies from desert fairy rings",
			position = 0,
			section = desertSection
	)
	default boolean removeButterfliesDesert()
	{
		return false;
	}
	@ConfigItem(
			keyName = "colourWingInnerDesert",
			name = "Spot Colour",
			description = "Colour of the spots on the inside of the butterfly wings",
			position = 1,
			section = desertSection
	)
	default Color colourWingInnerDesert()
	{
		return new Color(-2372541);
	}
	@ConfigItem(
			keyName = "colourWingOuterDesert",
			name = "Wing Colour",
			description = "Main colour of the butterfly wings",
			position = 2,
			section = desertSection
	)
	default Color colourWingOuterDesert()
	{
		return new Color(-10202598);
	}
	@ConfigItem(
			keyName = "colourBodyDesert",
			name = "Body Colour",
			description = "Colour of the itty bitty butterfly bodies",
			position = 3,
			section = desertSection
	)
	default Color colourBodyDesert()
	{
		return new Color(-14215401);
	}

	@ConfigItem(
			keyName = "removeButterfliesIsland",
			name = "Remove Butterflies",
			description = "Remove butterflies from island fairy rings",
			position = 0,
			section = islandSection
	)
	default boolean removeButterfliesIsland()
	{
		return false;
	}
	@ConfigItem(
			keyName = "colourWingInnerIsland",
			name = "Spot Colour",
			description = "Colour of the spots on the inside of the butterfly wings",
			position = 1,
			section = islandSection
	)
	default Color colourWingInnerIsland()
	{
		return new Color(-9266246);
	}
	@ConfigItem(
			keyName = "colourWingOuterIsland",
			name = "Wing Colour",
			description = "Main colour of the butterfly wings",
			position = 2,
			section = islandSection
	)
	default Color colourWingOuterIsland()
	{
		return new Color(-5843480);
	}
	@ConfigItem(
			keyName = "colourBodyIsland",
			name = "Body Colour",
			description = "Colour of the itty bitty butterfly bodies",
			position = 3,
			section = islandSection
	)
	default Color colourBodyIsland()
	{
		return new Color(-11054510);
	}

	@ConfigItem(
			keyName = "removeButterfliesCave",
			name = "Remove Butterflies",
			description = "Remove butterflies from cave fairy rings",
			position = 0,
			section = caveSection
	)
	default boolean removeButterfliesCave()
	{
		return true;
	}
	@ConfigItem(
			keyName = "colourWingInnerCave",
			name = "Spot Colour",
			description = "Colour of the spots on the inside of the butterfly wings",
			position = 1,
			section = caveSection
	)
	default Color colourWingInnerCave()
	{
		return Color.WHITE;
	}
	@ConfigItem(
			keyName = "colourWingOuterCave",
			name = "Wing Colour",
			description = "Main colour of the butterfly wings",
			position = 2,
			section = caveSection
	)
	default Color colourWingOuterCave()
	{
		return Color.LIGHT_GRAY;
	}
	@ConfigItem(
			keyName = "colourBodyCave",
			name = "Body Colour",
			description = "Colour of the itty bitty butterfly bodies",
			position = 3,
			section = caveSection
	)
	default Color colourBodyCave()
	{
		return Color.DARK_GRAY;
	}

	@ConfigItem(
			keyName = "removeButterfliesMountains",
			name = "Remove Butterflies",
			description = "Remove butterflies from mountain fairy rings",
			position = 0,
			section = mountainsSection
	)
	default boolean removeButterfliesMountains()
	{
		return false;
	}
	@ConfigItem(
			keyName = "colourWingInnerMountains",
			name = "Spot Colour",
			description = "Colour of the spots on the inside of the butterfly wings",
			position = 1,
			section = mountainsSection
	)
	default Color colourWingInnerMountains()
	{
		return new Color(-13219102);
	}
	@ConfigItem(
			keyName = "colourWingOuterMountains",
			name = "Wing Colour",
			description = "Main colour of the butterfly wings",
			position = 2,
			section = mountainsSection
	)
	default Color colourWingOuterMountains()
	{
		return new Color(-15660277);
	}
	@ConfigItem(
			keyName = "colourBodyMountains",
			name = "Body Colour",
			description = "Colour of the itty bitty butterfly bodies",
			position = 3,
			section = mountainsSection
	)
	default Color colourBodyMountains()
	{
		return new Color(-16242420);
	}

	@ConfigItem(
			keyName = "removeButterfliesSwamp",
			name = "Remove Butterflies",
			description = "Remove butterflies from swamp fairy rings",
			position = 0,
			section = swampSection
	)
	default boolean removeButterfliesSwamp()
	{
		return false;
	}
	@ConfigItem(
			keyName = "colourWingInnerSwamp",
			name = "Spot Colour",
			description = "Colour of the spots on the inside of the butterfly wings",
			position = 1,
			section = swampSection
	)
	default Color colourWingInnerSwamp()
	{
		return new Color(-15884248);
	}
	@ConfigItem(
			keyName = "colourWingOuterSwamp",
			name = "Wing Colour",
			description = "Main colour of the butterfly wings",
			position = 2,
			section = swampSection
	)
	default Color colourWingOuterSwamp()
	{
		return new Color(-15979004);
	}
	@ConfigItem(
			keyName = "colourBodySwamp",
			name = "Body Colour",
			description = "Colour of the itty bitty butterfly bodies",
			position = 3,
			section = swampSection
	)
	default Color colourBodySwamp()
	{
		return new Color(-14082016);
	}

	@ConfigItem(
			keyName = "removeButterfliesJungle",
			name = "Remove Butterflies",
			description = "Remove butterflies from jungle fairy rings",
			position = 0,
			section = jungleSection
	)
	default boolean removeButterfliesJungle()
	{
		return false;
	}
	@ConfigItem(
			keyName = "colourWingInnerJungle",
			name = "Spot Colour",
			description = "Colour of the spots on the inside of the butterfly wings",
			position = 1,
			section = jungleSection
	)
	default Color colourWingInnerJungle()
	{
		return new Color(-2487260);
	}
	@ConfigItem(
			keyName = "colourWingOuterJungle",
			name = "Wing Colour",
			description = "Main colour of the butterfly wings",
			position = 2,
			section = jungleSection
	)
	default Color colourWingOuterJungle()
	{
		return new Color(-679905);
	}
	@ConfigItem(
			keyName = "colourBodyJungle",
			name = "Body Colour",
			description = "Colour of the itty bitty butterfly bodies",
			position = 3,
			section = jungleSection
	)
	default Color colourBodyJungle()
	{
		return new Color(-14277365);
	}

	@ConfigItem(
			keyName = "removeButterfliesAbyss",
			name = "Remove Butterflies",
			description = "Remove butterflies from abyssal fairy rings",
			position = 0,
			section = abyssSection
	)
	default boolean removeButterfliesAbyss()
	{
		return false;
	}
	@ConfigItem(
			keyName = "colourWingInnerAbyss",
			name = "Spot Colour",
			description = "Colour of the spots on the inside of the butterfly wings",
			position = 1,
			section = abyssSection
	)
	default Color colourWingInnerAbyss()
	{
		return new Color(-14361252);
	}
	@ConfigItem(
			keyName = "colourWingOuterAbyss",
			name = "Wing Colour",
			description = "Main colour of the butterfly wings",
			position = 2,
			section = abyssSection
	)
	default Color colourWingOuterAbyss()
	{
		return new Color(-7334121);
	}
	@ConfigItem(
			keyName = "colourBodyAbyss",
			name = "Body Colour",
			description = "Colour of the itty bitty butterfly bodies",
			position = 3,
			section = abyssSection
	)
	default Color colourBodyAbyss()
	{
		return new Color(-16647463);
	}

	@ConfigItem(
			keyName = "removeButterfliesExtraplanar",
			name = "Remove Butterflies",
			description = "Remove butterflies from far-flung fairy rings",
			position = 0,
			section = extraplanarSection
	)
	default boolean removeButterfliesExtraplanar()
	{
		return false;
	}
	@ConfigItem(
			keyName = "colourWingInnerExtraplanar",
			name = "Spot Colour",
			description = "Colour of the spots on the inside of the butterfly wings",
			position = 1,
			section = extraplanarSection
	)
	default Color colourWingInnerExtraplanar()
	{
		return new Color(-15672800);
	}
	@ConfigItem(
			keyName = "colourWingOuterExtraplanar",
			name = "Wing Colour",
			description = "Main colour of the butterfly wings",
			position = 2,
			section = extraplanarSection
	)
	default Color colourWingOuterExtraplanar()
	{
		return new Color(-16221014);
	}
	@ConfigItem(
			keyName = "colourBodyExtraplanar",
			name = "Body Colour",
			description = "Colour of the itty bitty butterfly bodies",
			position = 3,
			section = extraplanarSection
	)
	default Color colourBodyExtraplanar()
	{
		return new Color(-2989073);
	}

	@ConfigItem(
			keyName = "removeButterfliesVolcanic",
			name = "Remove Butterflies",
			description = "Remove butterflies from volcanic fairy rings",
			position = 0,
			section = volcanicSection
	)
	default boolean removeButterfliesVolcanic()
	{
		return false;
	}
	@ConfigItem(
			keyName = "colourWingInnerVolcanic",
			name = "Spot Colour",
			description = "Colour of the spots on the inside of the butterfly wings",
			position = 1,
			section = volcanicSection
	)
	default Color colourWingInnerVolcanic()
	{
		return new Color(-15137531);
	}
	@ConfigItem(
			keyName = "colourWingOuterVolcanic",
			name = "Wing Colour",
			description = "Main colour of the butterfly wings",
			position = 2,
			section = volcanicSection
	)
	default Color colourWingOuterVolcanic()
	{
		return new Color(-15132391);
	}
	@ConfigItem(
			keyName = "colourBodyVolcanic",
			name = "Body Colour",
			description = "Colour of the itty bitty butterfly bodies",
			position = 3,
			section = volcanicSection
	)
	default Color colourBodyVolcanic()
	{
		return new Color(-12566464);
	}

	@ConfigItem(
			keyName = "export",
			name = "Export data",
			description = "Exports the current colour settings. Will export biome data only if biome mode is on",
			position = 0,
			section = exportImport
	)
	default boolean exportBool()
	{
		return false;
	}
	@ConfigItem(
			keyName = "import",
			name = "Import from data text field",
			description = "Imports the settings defined in the text field below, produced by an export",
			position = 1,
			section = exportImport
	)
	default boolean importBool()
	{
		return false;
	}
	@ConfigItem(
			keyName = "data",
			name = "Data",
			description = "Data to import",
			position = 2,
			section = exportImport
	)
	default String data()
	{
		return "";
	}
}
