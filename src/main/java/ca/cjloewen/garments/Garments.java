package ca.cjloewen.garments;

import ca.cjloewen.base.BaseMod;
import ca.cjloewen.base.ConfigHolder;
import ca.cjloewen.garments.config.ClientConfig;
import ca.cjloewen.garments.config.ServerConfig;
import ca.cjloewen.garments.registries.Blocks;
import ca.cjloewen.garments.registries.Items;
import ca.cjloewen.garments.registries.KeyBindings;
import ca.cjloewen.garments.registries.Recipes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Garments.MODID)
public class Garments extends BaseMod {
	public static final String MODID = "garments";
	public static ConfigHolder<ClientConfig, ServerConfig> CONFIGS;
	public static Blocks BLOCKS;
	public static Items ITEMS;
	
	public Garments() {
		MinecraftForge.EVENT_BUS.register(this);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
		CONFIGS = new ConfigHolder<ClientConfig, ServerConfig>(new ClientConfig(), new ServerConfig()).register();
		BLOCKS = (Blocks) new Blocks().register();
		ITEMS = (Items) new Items().register();
		new Recipes().register();
	}
	
	@SubscribeEvent
	public void onClientSetup(FMLClientSetupEvent event) {
		new KeyBindings().register();
	}
}
