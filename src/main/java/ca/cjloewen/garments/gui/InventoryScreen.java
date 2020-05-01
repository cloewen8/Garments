package ca.cjloewen.garments.gui;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import ca.cjloewen.garments.Garments;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class InventoryScreen extends net.minecraft.client.gui.screen.inventory.InventoryScreen {
	// TODO A few things:
	// - Change the armor slots for garments slots
	// - Add a button to the regular inventory to view this inventory.

	private static InventoryScreen instance;
	private static Method mainInventoryAddButton;
	private static final Logger LOGGER = LogManager.getLogger();
	private static final ResourceLocation INVENTORY_BACKGROUND = new ResourceLocation(Garments.MODID, "textures/gui/container/inventory.png");
	private static final ResourceLocation TOGGLE_BUTTON = new ResourceLocation(Garments.MODID, "textures/gui/garment_button.png");

	private float oldMouseX;
	private float oldMouseY;

	public InventoryScreen(ClientPlayerEntity player) {
		super(player);
	}

	public static InventoryScreen getInstance() {
		if (instance == null)
			instance = new InventoryScreen(Minecraft.getInstance().player);
		return instance;
	}

	public static void register() {
		try {
			mainInventoryAddButton = net.minecraft.client.gui.screen.Screen.class.getDeclaredMethod("addButton", Widget.class);
			mainInventoryAddButton.setAccessible(true);
			
		} catch (Exception e) {
			LOGGER.error("Unexpected exception while accessing inventory buttons. "
					+ "Won't be able to add the garments inventory toggle!", e);
			mainInventoryAddButton = null;
		}

		MinecraftForge.EVENT_BUS.register(InventoryScreen.class);
	}

	/**
	 * Add the Garments inventory button to the regular inventory.
	 */
	@SubscribeEvent
	public static void onGuiOpen(GuiScreenEvent.InitGuiEvent.Post event) {
		Screen screen = event.getGui();
		if (mainInventoryAddButton != null && screen != instance && screen instanceof net.minecraft.client.gui.screen.inventory.InventoryScreen) {
			try {
				// Enter garments inventory.
				mainInventoryAddButton.invoke(screen, getToggleButton(
					((net.minecraft.client.gui.screen.inventory.InventoryScreen)screen).getGuiLeft() + 104 + 22,
					screen.height / 2 - 22,
					new Button.IPressable() {
						@Override
						public void onPress(Button p_onPress_1_) {
							Minecraft.getInstance().displayGuiScreen(getInstance());
						}
					}));
			} catch (Exception e) {
				LOGGER.error("Unexpected exception while accessing inventory buttons. "
						+ "Won't be able to add the garments inventory toggle!", e);
			}
		}
	}

	private static Button getToggleButton(int offsetX, int offsetY, Button.IPressable onPress) {
		// Could add a config for the width offset to avoid conflicts.
		// x, y, width, height, texX, texY, yDiffText, texture, onPress
		return new ImageButton(offsetX, offsetY, 20, 18, 0, 0, 19, TOGGLE_BUTTON, onPress);
	}
	
	@Override
	protected void init() {
		super.init();
		// Enter regular inventory.
		addButton(getToggleButton(this.guiLeft + 104 + 22, this.height / 2 - 22, new Button.IPressable() {
			@Override
			public void onPress(Button p_onPress_1_) {
				Minecraft mc = Minecraft.getInstance();
				mc.displayGuiScreen(new net.minecraft.client.gui.screen.inventory.InventoryScreen(mc.player));
			}
		}));
	}

	@Override
	public void render(int mouseX, int mouseY, float p_render_3_) {
		super.render(mouseX, mouseY, p_render_3_);
		this.oldMouseX = mouseX;
		this.oldMouseY = mouseY;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(INVENTORY_BACKGROUND);
		int i = this.guiLeft;
		int j = this.guiTop;
		this.blit(i, j, 0, 0, this.xSize, this.ySize);
		func_228187_a_(i + 51, j + 75, 30, (float)(i + 51) - this.oldMouseX, (float)(j + 75 - 50) - this.oldMouseY, this.minecraft.player);
	}

	public static void func_228187_a_(int p_228187_0_, int p_228187_1_, int p_228187_2_, float p_228187_3_, float p_228187_4_, LivingEntity p_228187_5_) {
		float f = (float)Math.atan((double)(p_228187_3_ / 40.0F));
		float f1 = (float)Math.atan((double)(p_228187_4_ / 40.0F));
		RenderSystem.pushMatrix();
		RenderSystem.translatef((float)p_228187_0_, (float)p_228187_1_, 1050.0F);
		RenderSystem.scalef(1.0F, 1.0F, -1.0F);
		MatrixStack matrixstack = new MatrixStack();
		matrixstack.func_227861_a_(0.0D, 0.0D, 1000.0D);
		matrixstack.func_227862_a_((float)p_228187_2_, (float)p_228187_2_, (float)p_228187_2_);
		Quaternion quaternion = Vector3f.field_229183_f_.func_229187_a_(180.0F);
		Quaternion quaternion1 = Vector3f.field_229179_b_.func_229187_a_(f1 * 20.0F);
		quaternion.multiply(quaternion1);
		matrixstack.func_227863_a_(quaternion);
		float f2 = p_228187_5_.renderYawOffset;
		float f3 = p_228187_5_.rotationYaw;
		float f4 = p_228187_5_.rotationPitch;
		float f5 = p_228187_5_.prevRotationYawHead;
		float f6 = p_228187_5_.rotationYawHead;
		p_228187_5_.renderYawOffset = 180.0F + f * 20.0F;
		p_228187_5_.rotationYaw = 180.0F + f * 40.0F;
		p_228187_5_.rotationPitch = -f1 * 20.0F;
		p_228187_5_.rotationYawHead = p_228187_5_.rotationYaw;
		p_228187_5_.prevRotationYawHead = p_228187_5_.rotationYaw;
		EntityRendererManager entityrenderermanager = Minecraft.getInstance().getRenderManager();
		quaternion1.conjugate();
		entityrenderermanager.func_229089_a_(quaternion1);
		entityrenderermanager.setRenderShadow(false);
		IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().func_228019_au_().func_228487_b_();
		entityrenderermanager.func_229084_a_(p_228187_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrixstack, irendertypebuffer$impl, 15728880);
		irendertypebuffer$impl.func_228461_a_();
		entityrenderermanager.setRenderShadow(true);
		p_228187_5_.renderYawOffset = f2;
		p_228187_5_.rotationYaw = f3;
		p_228187_5_.rotationPitch = f4;
		p_228187_5_.prevRotationYawHead = f5;
		p_228187_5_.rotationYawHead = f6;
		RenderSystem.popMatrix();
	}
}
