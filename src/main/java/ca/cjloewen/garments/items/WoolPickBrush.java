package ca.cjloewen.garments.items;

import java.util.HashSet;

import ca.cjloewen.garments.registries.Items;
import net.minecraft.block.Block;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ToolItem;

public class WoolPickBrush extends ToolItem {
	public static final String NAME = "wool_pick_brush";
	
	public WoolPickBrush() {
		super(3, -2.4f, ItemTier.STONE, new HashSet<Block>(), new Properties()
			.group(Items.GENERAL_GROUP)
			.maxDamage(16));
	}
}
