package ca.cjloewen.garments.registries;

import ca.cjloewen.base.BaseRegistry;
import ca.cjloewen.garments.Garments;
import ca.cjloewen.garments.items.WoolPickBrush;
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
	
	public Items() {
		super(ForgeRegistries.ITEMS, Garments.MODID);
		// Item groups.
		GENERAL_GROUP = new GeneralItemGroup();
		// Items.
		register(WoolPickBrush.NAME, WoolPickBrush.class);
	}
	
	/**
	 * A group for most items.
	 */
	private class GeneralItemGroup extends ItemGroup {
		public GeneralItemGroup() {
			super(-1, "garments");
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(WOOL_PICK_BRUSH);
		}
	}
}
