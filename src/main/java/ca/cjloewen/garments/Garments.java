package ca.cjloewen.garments;

import ca.cjloewen.base.BaseMod;
import ca.cjloewen.base.ConfigHolder;
import ca.cjloewen.garments.config.ClientConfig;
import ca.cjloewen.garments.config.ServerConfig;
import ca.cjloewen.garments.registries.Blocks;
import ca.cjloewen.garments.registries.Items;
import ca.cjloewen.garments.registries.Recipes;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Garments.MODID)
public class Garments extends BaseMod {
	public static final String MODID = "garments";
	public static ConfigHolder<ClientConfig, ServerConfig> CONFIGS;
	public static Blocks BLOCKS;
	public static Items ITEMS;
	
	public Garments() {
		CONFIGS = new ConfigHolder<ClientConfig, ServerConfig>(new ClientConfig(), new ServerConfig()).register();
		BLOCKS = (Blocks) new Blocks().register();
		ITEMS = (Items) new Items().register();
		new Recipes().register();
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}
	
	@SubscribeEvent
	public void onHandleItemColors(ColorHandlerEvent.Item event) {
		event.getItemColors().register((stack, tintIndex) -> {
			return tintIndex > 0 ? -1 : ((IDyeableArmorItem)stack.getItem()).getColor(stack);
		}, Items.UNDERWEAR);
	}
}
