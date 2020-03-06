package ca.cjloewen.garments.items.garments;

import net.minecraft.inventory.EquipmentSlotType;

public enum GarmentSlotType {
	HEAD("head", 0, new String[] {"head"}),
	CHEST("chest", 1, new String[] {"chest"}),
	TORSO("torso", 2, new String[] {"chest"}),
	WAIST("waist", 3, new String[] {"legs"}),
	LEGS("legs", 4, new String[] {"legs", "feet"}),
	FEET("feet", 5, new String[] {"feet"});
	
	private final int slotIndex;
	private final String name;
	private final String[] underNames;
	
	private GarmentSlotType(String name, int slotIndex, String[] underNames) {
		this.name = name;
		this.slotIndex = slotIndex;
		this.underNames = underNames;
	}
	
	public int getSlotIndex() {
		return slotIndex;
	}
	
	public String getName() {
		return name;
	}
	
	public EquipmentSlotType[] getUnderSlots() {
		EquipmentSlotType[] underSlots = new EquipmentSlotType[underNames.length];
		for (int i = 0; i < underNames.length; i++) {
			underSlots[i] = EquipmentSlotType.fromString(underNames[i]);
		}
		return underSlots;
	}
	
	public static GarmentSlotType fromString(String targetName) {
		for (GarmentSlotType slotType : values()) {
			if (slotType.getName().equals(targetName)) {
				return slotType;
			}
		}

		throw new IllegalArgumentException("Invalid slot '" + targetName + "'");
	}
}
