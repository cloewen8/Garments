package ca.cjloewen.garments.registries;

import ca.cjloewen.base.BaseRegistry;
import ca.cjloewen.base.ToolRecipe;
import ca.cjloewen.garments.Garments;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

public class Recipes extends BaseRegistry<IRecipeSerializer<?>> {
	public Recipes() {
		super(ForgeRegistries.RECIPE_SERIALIZERS, Garments.MODID);
		
		register(ToolRecipe.Serializer.NAME, ToolRecipe.Serializer.class);
	}
}
