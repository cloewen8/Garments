package ca.cjloewen.garments.gui;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.cjloewen.garments.Garments;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class InventoryScreen extends net.minecraft.client.gui.screen.inventory.InventoryScreen {
	// TODO A few things:
	// - Change the armor slots for garments slots
	// - Add a button to the regular inventory to view this inventory.
	// - Add a button to go back to the regular inventory.
	
	private static final Logger LOGGER = LogManager.getLogger();
	private static final ResourceLocation TOGGLE_BUTTON = new ResourceLocation(Garments.MODID, "textures/gui/recipe_button.png");
	
	private final Field mainInventoryButtonsField;
	private final Button.IPressable enterButtonPress;
	
	public InventoryScreen(PlayerEntity player) throws NoSuchFieldException, SecurityException {
		super(player);
		mainInventoryButtonsField = net.minecraft.client.gui.screen.inventory.InventoryScreen.class.getField("buttons");
		mainInventoryButtonsField.setAccessible(true);
		enterButtonPress = new Button.IPressable() {
			@Override
			public void onPress(Button p_onPress_1_) {
				// TODO Go to the garments inventory.
				LOGGER.info("go to garments inventory");
			}
		};
		buttons.add(getToggleButton(new Button.IPressable() {
			@Override
			public void onPress(Button p_onPress_1_) {
				// TODO Go to the regular inventory.
				LOGGER.info("go to regular inventory");
			}
		}));
	}
	
	@SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
		Screen screen = event.getGui();
		if (screen instanceof net.minecraft.client.gui.screen.inventory.InventoryScreen) {
			try {
				@SuppressWarnings("unchecked")
				List<Widget> buttons = (List<Widget>) mainInventoryButtonsField.get(screen);
				buttons.add(getToggleButton(enterButtonPress));
			} catch (Exception e) {
				LOGGER.error("Unexpected exception while accessing inventory buttons. "
						+ "Won't be able to add the garments inventory toggle!", e);
			}
		}
	}
	
	// TODO Accept a custom action to perform.
	private Button getToggleButton(Button.IPressable onPress) {
		// TODO Design a button both to enter and go back.
		// Perhaps a button with a smaller underwear ino in the player viewport.
		// Could make it configurable in case of conflict with another mod.
		return null; // new ImageButton(TOGGLE_BUTTON, onPress);
	}
}
