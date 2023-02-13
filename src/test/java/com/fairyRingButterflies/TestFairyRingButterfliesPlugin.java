package com.fairyRingButterflies;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TestFairyRingButterfliesPlugin
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(FairyRingButterfliesPlugin.class);
		RuneLite.main(args);
	}
}