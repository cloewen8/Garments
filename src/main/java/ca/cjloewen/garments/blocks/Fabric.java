package ca.cjloewen.garments.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Fabric extends Block {
	public static final String name = "fabric_";
	public static final FabricColor[] COLORS = {
		new FabricColor("white", MaterialColor.SNOW),
		new FabricColor("orange", MaterialColor.ADOBE),
		new FabricColor("magenta", MaterialColor.MAGENTA),
		new FabricColor("light_blue", MaterialColor.LIGHT_BLUE),
		new FabricColor("yellow", MaterialColor.YELLOW),
		new FabricColor("lime", MaterialColor.LIME),
		new FabricColor("pink", MaterialColor.PINK),
		new FabricColor("gray", MaterialColor.GRAY),
		new FabricColor("light_gray", MaterialColor.LIGHT_GRAY),
		new FabricColor("cyan", MaterialColor.CYAN),
		new FabricColor("purple", MaterialColor.PURPLE),
		new FabricColor("blue", MaterialColor.BLUE),
		new FabricColor("brown", MaterialColor.BROWN),
		new FabricColor("green", MaterialColor.GREEN),
		new FabricColor("red", MaterialColor.RED),
		new FabricColor("black", MaterialColor.BLACK)
	};
	
	public final FabricColor color;
	
	public Fabric(FabricColor color) {
		super(Block.Properties.create(Material.WOOL, color.color).hardnessAndResistance(0.8F).sound(SoundType.CLOTH));
		this.color = color;
	}
	
	public static class FabricColor {
		public String name;
		public MaterialColor color;
		public Fabric block;
		
		public FabricColor(String name, MaterialColor color) {
			this.name = name;
			this.color = color;
		}
		
		@SubscribeEvent
		public static void onBlockRegistered(RegistryEvent.Register<Block> event) {
			event.getRegistry().getEntries().parallelStream()
				.map((entry) -> entry.getValue())
				.filter((block) -> block instanceof Fabric)
				.forEach((block) -> {
					Fabric fabric = (Fabric) block;
					fabric.color.block = fabric;
				});
		}
	}
}
