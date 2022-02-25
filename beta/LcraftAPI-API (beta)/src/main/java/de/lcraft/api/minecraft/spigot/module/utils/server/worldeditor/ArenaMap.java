package de.lcraft.api.minecraft.spigot.module.utils.server.worldeditor;

import de.lcraft.api.java_utils.serialize.SerializeObject;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Map;

public class ArenaMap implements SerializeObject {

	private ArrayList<Block> allBlocks;

	public ArenaMap() {
		allBlocks = new ArrayList<>();
	}

	@Override
	public Map<String, Object> serialize() {
		return null;
	}

	@Override
	public <T extends SerializeObject> Object deserialize() {
		return null;
	}

	public ArrayList<Block> getAllBlocks() {
		return allBlocks;
	}

}
