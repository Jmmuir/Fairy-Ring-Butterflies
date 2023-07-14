package com.fairyRingButterflies;

import net.runelite.client.config.ConfigManager;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class ConfigExportImport {

    public static void exportConfigToTextBox(ConfigManager configManager) {
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(convertConfigToString(configManager.getConfig(FairyRingButterfliesConfig.class))), null);
        JOptionPane.showMessageDialog(null,
                "Settings data copied to clipboard",
                "Export Data",
                JOptionPane.INFORMATION_MESSAGE);
        configManager.setConfiguration(FairyRingButterfliesConfig.CONFIG_NAME, "export", false);
    }

    public static void ImportConfigFromTextBox(ConfigManager configManager) {
        String importData = configManager.getConfig(FairyRingButterfliesConfig.class).data();
        String [] settingsByBiome = importData.split("\\|");
        try {
            for (String biomeSettings : settingsByBiome) {
                applyImportedConfig(configManager, biomeSettings);
            }
            JOptionPane.showMessageDialog(null,
                    "Imported config loaded. Close and reopen the plugin config to further customise.",
                    "Export Data",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Import Error", JOptionPane.ERROR_MESSAGE);
        }
        configManager.setConfiguration(FairyRingButterfliesConfig.CONFIG_NAME, "import", false);
        configManager.setConfiguration(FairyRingButterfliesConfig.CONFIG_NAME, "data", "");
    }

    private static String convertConfigToString(FairyRingButterfliesConfig config) {
        StringBuilder sb = new StringBuilder();
        if (config.biomeColours()) {
            sb.append("Mountains:");
            sb.append(config.removeButterfliesMountains());
            sb.append(":");
            sb.append(config.colourBodyMountains().getRGB());
            sb.append(":");
            sb.append(config.colourWingOuterMountains().getRGB());
            sb.append(":");
            sb.append(config.colourWingInnerMountains().getRGB());
            sb.append("|");
            sb.append("Plains:");
            sb.append(config.removeButterfliesPlains());
            sb.append(":");
            sb.append(config.colourBodyPlains().getRGB());
            sb.append(":");
            sb.append(config.colourWingOuterPlains().getRGB());
            sb.append(":");
            sb.append(config.colourWingInnerPlains().getRGB());
            sb.append("|");
            sb.append("Desert:");
            sb.append(config.removeButterfliesDesert());
            sb.append(":");
            sb.append(config.colourBodyDesert().getRGB());
            sb.append(":");
            sb.append(config.colourWingOuterDesert().getRGB());
            sb.append(":");
            sb.append(config.colourWingInnerDesert().getRGB());
            sb.append("|");
            sb.append("Island:");
            sb.append(config.removeButterfliesIsland());
            sb.append(":");
            sb.append(config.colourBodyIsland().getRGB());
            sb.append(":");
            sb.append(config.colourWingOuterIsland().getRGB());
            sb.append(":");
            sb.append(config.colourWingInnerIsland().getRGB());
            sb.append("|");
            sb.append("Cave:");
            sb.append(config.removeButterfliesCave());
            sb.append(":");
            sb.append(config.colourBodyCave().getRGB());
            sb.append(":");
            sb.append(config.colourWingOuterCave().getRGB());
            sb.append(":");
            sb.append(config.colourWingInnerCave().getRGB());
            sb.append("|");
            sb.append("Swamp:");
            sb.append(config.removeButterfliesSwamp());
            sb.append(":");
            sb.append(config.colourBodySwamp().getRGB());
            sb.append(":");
            sb.append(config.colourWingOuterSwamp().getRGB());
            sb.append(":");
            sb.append(config.colourWingInnerSwamp().getRGB());
            sb.append("|");
            sb.append("Jungle:");
            sb.append(config.removeButterfliesJungle());
            sb.append(":");
            sb.append(config.colourBodyJungle().getRGB());
            sb.append(":");
            sb.append(config.colourWingOuterJungle().getRGB());
            sb.append(":");
            sb.append(config.colourWingInnerJungle().getRGB());
            sb.append("|");
            sb.append("Abyss:");
            sb.append(config.removeButterfliesAbyss());
            sb.append(":");
            sb.append(config.colourBodyAbyss().getRGB());
            sb.append(":");
            sb.append(config.colourWingOuterAbyss().getRGB());
            sb.append(":");
            sb.append(config.colourWingInnerAbyss().getRGB());
            sb.append("|");
            sb.append("Extraplanar:");
            sb.append(config.removeButterfliesExtraplanar());
            sb.append(":");
            sb.append(config.colourBodyExtraplanar().getRGB());
            sb.append(":");
            sb.append(config.colourWingOuterExtraplanar().getRGB());
            sb.append(":");
            sb.append(config.colourWingInnerExtraplanar().getRGB());
            sb.append("|");
            sb.append("Volcanic:");
            sb.append(config.removeButterfliesVolcanic());
            sb.append(":");
            sb.append(config.colourBodyVolcanic().getRGB());
            sb.append(":");
            sb.append(config.colourWingOuterVolcanic().getRGB());
            sb.append(":");
            sb.append(config.colourWingInnerVolcanic().getRGB());
        } else {
            sb.append("Base:");
            sb.append(config.removeButterflies());
            sb.append(":");
            sb.append(config.colourBody().getRGB());
            sb.append(":");
            sb.append(config.colourWingOuter().getRGB());
            sb.append(":");
            sb.append(config.colourWingInner().getRGB());
        }
        return sb.toString();
    }

    private static void applyImportedConfig(ConfigManager configManager, String settings) throws Exception {
        String[] configBits = settings.split(":");
        if (configBits.length != 5){
            throw new Exception("Bad import string: " + settings + " wrong number of settings included.");
        }
        String configSuffix;
        if (configBits[0].equals("Base")) {
            configSuffix = "";
        } else if (configManager.getConfiguration(FairyRingButterfliesConfig.CONFIG_NAME,"removeButterflies" + configBits[0]) != null) {
            configSuffix = configBits[0];
        } else {
            throw new Exception("unknown biome group: \"" + configBits[0] + "\". check data format is correct.");
        }

        configManager.setConfiguration(FairyRingButterfliesConfig.CONFIG_NAME, "removeButterflies" + configBits[0], Boolean.valueOf(configBits[1]));
        configManager.setConfiguration(FairyRingButterfliesConfig.CONFIG_NAME, "colourBody" + configBits[0], new Color(Integer.parseInt(configBits[2])));
        configManager.setConfiguration(FairyRingButterfliesConfig.CONFIG_NAME, "colourWingOuter" + configBits[0], new Color(Integer.parseInt(configBits[3])));
        configManager.setConfiguration(FairyRingButterfliesConfig.CONFIG_NAME, "colourWingInner" + configBits[0], new Color(Integer.parseInt(configBits[4])));

    }

}
