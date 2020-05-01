package ca.cjloewen.garments.registries;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBindings {
	public static final KeyBinding GARMENTS_INV_KEY = new KeyBinding("key.garments_inv",
			KeyConflictContext.UNIVERSAL, InputMappings.getInputByName("key.keyboard.g"), "key.categories.inventory");
	
	public void register() {
		MinecraftForge.EVENT_BUS.register(this);
		ClientRegistry.registerKeyBinding(GARMENTS_INV_KEY);
	}
	
	@SubscribeEvent
	public void onInput(KeyInputEvent event) {
		if (GARMENTS_INV_KEY.isPressed()) {
			
		}
	}
}
