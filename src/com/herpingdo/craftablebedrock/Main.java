package com.herpingdo.craftablebedrock;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	private ShapedRecipe bedrock = new ShapedRecipe(new ItemStack(Material.BEDROCK, 2)).shape("ooo", "odo", "ooo").setIngredient('o', Material.OBSIDIAN).setIngredient('d', Material.DIAMOND);
	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this,  this);
		getServer().addRecipe(bedrock);
		x("Successfully registered recipe for bedrock.");
		ItemStack stack = new ItemStack(Material.STICK, 1);
		ItemMeta meta = stack.getItemMeta();
		meta.setLore(Arrays.asList(new String[]{"Bedrock Breaker"}));
		stack.setItemMeta(meta);
		ShapedRecipe breaker = new ShapedRecipe(stack).shape("d", "s").setIngredient('s', Material.STICK).setIngredient('d', Material.DIAMOND);
		getServer().addRecipe(breaker);
		x("Successfully registered recipe for breaker!");
	}
	
	/* TODO: Permissions */
	@EventHandler
	public void onInteract(PlayerInteractEvent evt)
	{
		if (evt.getAction().equals(Action.LEFT_CLICK_BLOCK))
		{
			x("Has action!");
			Player player = evt.getPlayer();
			ItemStack i = player.getItemInHand();
			if (!(i.getType().equals(Material.STICK) && i.getItemMeta().getLore().contains("Bedrock Breaker"))) return;
			x("Has item!");
			if (evt.getClickedBlock().getType().equals(Material.BEDROCK))
			{
				x("Is bedrock!");
				if (evt.getClickedBlock().getLocation().getBlockY() <= 4)
				{
					player.sendMessage("You may not break through the floor!");
					return;
				}
				evt.getClickedBlock().setType(Material.AIR);
				player.getWorld().dropItem(evt.getClickedBlock().getLocation(), new ItemStack(Material.BEDROCK, 1));
			}
		}
	}
	
	
	/* I'm lazy, OK? */
	private void x(Object x)
	{
		System.out.println(x);
	}
}
