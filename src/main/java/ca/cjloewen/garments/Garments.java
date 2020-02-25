package ca.cjloewen.garments;

import ca.cjloewen.base.BaseMod;
import ca.cjloewen.base.ConfigHolder;
import ca.cjloewen.garments.config.ClientConfig;
import ca.cjloewen.garments.config.ServerConfig;
import net.minecraftforge.fml.common.Mod;

@Mod(Garments.MODID)
public class Garments extends BaseMod {
	public static final String MODID = "garments";
	public ConfigHolder<ClientConfig, ServerConfig> CONFIGS;
	
	public Garments() {
		super();
		
		CONFIGS = new ConfigHolder<ClientConfig, ServerConfig>(new ClientConfig(), new ServerConfig()).register();
	}
}
