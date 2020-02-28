package ca.cjloewen.garments.items;

import ca.cjloewen.garments.registries.Items;
import net.minecraft.item.Item;

public class Fabric extends Item {
	public static final String NAME = "fabric_sheet";
	
	public Fabric() {
		super(new Properties().group(Items.GENERAL_GROUP));
	}
}
