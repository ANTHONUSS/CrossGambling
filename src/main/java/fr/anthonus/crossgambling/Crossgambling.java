package fr.anthonus.crossgambling;

import fr.anthonus.crossgambling.commands.CrossCommand;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Crossgambling implements ModInitializer {
    public static final String MOD_ID = "crossgambling";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        CrossCommand.register();
        LOGGER.info("CrossGambling initialized");
    }
}
