package ca.cjloewen.garments.registries;

import ca.cjloewen.base.BaseRegistry;
import ca.cjloewen.garments.Garments;
import ca.cjloewen.garments.blocks.Fabric.FabricColor;
import ca.cjloewen.garments.items.Fabric;
import ca.cjloewen.garments.items.WoolPickBrush;
import ca.cjloewen.garments.items.garments.Underwear;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Garments.MODID)
public class Items extends BaseRegistry<Item> {
	public static ItemGroup GENERAL_GROUP;
	
	@ObjectHolder(WoolPickBrush.NAME)
	public static WoolPickBrush WOOL_PICK_BRUSH;
	@ObjectHolder(Fabric.NAME)
	public static Fabric FABRIC;
	@ObjectHolder(Underwear.NAME)
	public static Underwear UNDERWEAR;
	
	public Items() {
		super(ForgeRegistries.ITEMS, Garments.MODID);
		// Item groups.
		GENERAL_GROUP = new GeneralItemGroup();
		// Items.
		register(WoolPickBrush.NAME, WoolPickBrush.class);
		register(Fabric.NAME, Fabric.class);
		register(Underwear.NAME, Underwear.class);
		// Block items.
		for (FabricColor color : ca.cjloewen.garments.blocks.Fabric.COLORS) {
			register(ca.cjloewen.garments.blocks.Fabric.NAME + color.name, () -> new ModBlockItem(color.block));
		}
	}
	
	/**
	 * A group for most items.
	 */
	private static class GeneralItemGroup extends ItemGroup {
		public GeneralItemGroup() {
			super(-1, "garments");
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(UNDERWEAR);
		}
	}
	
	/**
	 * A general block item.
	 */
	private static class ModBlockItem extends BlockItem {
		public ModBlockItem(Block blockIn) {
			super(blockIn, new Properties().group(GENERAL_GROUP));
		}
	}
}
