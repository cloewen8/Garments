package ca.cjloewen.garments.registries;

import ca.cjloewen.base.BaseRegistry;
import ca.cjloewen.garments.Garments;
import ca.cjloewen.garments.blocks.Fabric;
import ca.cjloewen.garments.blocks.Fabric.FabricColor;
import net.minecraft.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

public class Blocks extends BaseRegistry<Block> {
	public Blocks() {
		super(ForgeRegistries.BLOCKS, Garments.MODID);
		
		// Blocks
		for (FabricColor color : Fabric.COLORS) {
			register(Fabric.NAME + color.name, () -> new Fabric(color));
		}
	}
	
	@Override
	public BaseRegistry<Block> register() {
		super.register();
		FMLJavaModLoadingContext.get().getModEventBus().register(Fabric.FabricColor.class);
		return this;
	}
}
