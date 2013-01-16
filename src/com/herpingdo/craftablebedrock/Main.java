package com.herpingdo.craftablebedrock;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	private ShapedRecipe bedrock = new ShapedRecipe(new ItemStack(Material.BEDROCK, 2)).shape("ooo", "odo", "ooo").setIngredient('o', Material.OBSIDIAN).setIngredient('d', Material.DIAMOND);
	
	@Override
	public void onEnable()
	{
		getServer().addRecipe(bedrock);
	}
	
	/* I'm lazy, OK? */
	private void x(Object x)
	{
		System.out.println(x);
	}
}
