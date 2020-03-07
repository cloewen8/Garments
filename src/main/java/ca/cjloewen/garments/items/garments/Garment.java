package ca.cjloewen.garments.items.garments;

import ca.cjloewen.garments.registries.Items;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.Item;

public abstract class Garment extends Item implements IDyeableArmorItem {
	// TODO Lots (a lot based on ArmorItem)!
	// - Damage over time.
	// - Dispenser behavior.
	// - Repair with fabric (damage slowly applied while moving, extra when diving or burning).
	// - Equip on right click.
	
	public final GarmentSlotType slot;
	
	public Garment(GarmentSlotType slot) {
		super(new Properties().group(Items.GENERAL_GROUP));
		this.slot = slot;
	}
}
