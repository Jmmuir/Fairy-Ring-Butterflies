package com.fairyRingButterflies;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import java.awt.*;

@ConfigGroup("fairyRingButterflies")
public interface FairyRingButterfliesConfig extends Config
{
	@ConfigSection(
			name = "Behaviour",
			description = "Flags which determine plugin behaviour",
			position = 0
	)
	String behaviourSection = "behaviour";

	@ConfigSection(
			name = "Colours",
			description = "Colours to apply to fairy ring butterflies",
			position = 1
	)
	String colourSection = "colour";

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
			keyName = "colourWingInner",
			name = "Spot Colour",
			description = "Colour of the spots on the inside of the butterfly wings",
			position = 0,
			section = colourSection
	)
	default Color colourWingInner()
	{
		return Color.WHITE;
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
		return Color.LIGHT_GRAY;
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
		return Color.DARK_GRAY;
	}
}
