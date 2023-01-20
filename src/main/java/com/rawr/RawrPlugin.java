package com.rawr;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Player;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.OverheadTextChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Rawr"
)
public class RawrPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private RawrConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}
	@Subscribe
	public void onOverheadTextChanged(OverheadTextChanged e) {
		Player player = client.getLocalPlayer();
		if(e.getActor().equals(player) && e.getOverheadText().equals("Raarrrrrgggggghhhhhhh!"))
			client.getLocalPlayer().setOverheadText("Rawr XD");
	}
	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}
	@Provides
	RawrConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RawrConfig.class);
	}
}
