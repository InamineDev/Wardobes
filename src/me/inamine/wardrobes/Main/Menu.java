package me.inamine.wardrobes.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.text.WordUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Menu
{
	Utils u = new Utils();

	public void openMenu(Player pl, int page)
	{
		Inventory inv = Bukkit.createInventory(null, 45,
				u.getMessage("inventory-title") + u.getMessage("page-" + page));
		fillBorders(inv);
		ItemStack next = new ItemStack(Material.ARROW, 1);
		ItemMeta nextmeta = next.getItemMeta();
		nextmeta.setDisplayName(u.getMessage("next-page"));
		next.setItemMeta(nextmeta);

		ItemStack last = new ItemStack(Material.ARROW, 1);
		ItemMeta lastmeta = last.getItemMeta();
		lastmeta.setDisplayName(u.getMessage("last-page"));
		last.setItemMeta(lastmeta);
		if (page == 1)
		{
			inv.setItem(44, next);
		} else
		{
			inv.setItem(36, last);
		}
		fillWardrobe(inv, page, pl);
		addRemoveIcons(inv);
		pl.openInventory(inv);
	}

	private Inventory fillBorders(Inventory inv)
	{
		Material mat = Material.GRAY_STAINED_GLASS_PANE;
		ItemStack placeholder = new ItemStack(mat, 1);
		ItemMeta meta = placeholder.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " &c "));
		placeholder.setItemMeta(meta);
		for (int i = 0; i < 9; i++)
		{
			inv.setItem(i, placeholder);
		}
		for (int i = 36; i < 45; i++)
		{
			inv.setItem(i, placeholder);
		}
		return inv;
	}

	private Inventory fillWardrobe(Inventory inv, int page, Player pl)
	{
		int slot = 0;
		for (int i = (page * 8) - 8; i < page * 8; i++)
		{
			if (pl.hasPermission("wardrobe." + Main.getInst().getConfig().getString("armor.set" + i + ".permission")))
			{
				ItemStack unlocked = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
				ItemMeta meta = unlocked.getItemMeta();
				meta.setDisplayName(u.getMessage("unlocked"));
				unlocked.setItemMeta(meta);
				inv.setItem(slot, unlocked);
			} else
			{
				ItemStack locked = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
				ItemMeta meta = locked.getItemMeta();
				meta.setDisplayName(u.getMessage("locked"));
				locked.setItemMeta(meta);
				inv.setItem(slot, locked);
			}

			PlayerInventory plinv = pl.getInventory();

			int r = Main.getInst().getConfig().getInt("armor.set" + i + ".red");
			int g = Main.getInst().getConfig().getInt("armor.set" + i + ".green");
			int b = Main.getInst().getConfig().getInt("armor.set" + i + ".blue");

			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			LeatherArmorMeta meta = (LeatherArmorMeta) chest.getItemMeta();
			String cname = WordUtils.capitalize(Main.getInst().getConfig().getString("armor.set" + i + ".permission")) + " Shirt";
			meta.setDisplayName(cname);
			meta.setColor(Color.fromRGB(r, g, b));
			chest.setItemMeta(meta);
			ItemStack currentChest = plinv.getChestplate();
			if (currentChest != null && currentChest.getType() == Material.LEATHER_CHESTPLATE)
			{
				LeatherArmorMeta lam = (LeatherArmorMeta) currentChest.getItemMeta();
				if (lam.getColor().asRGB() == meta.getColor().asRGB())
				{
					chest.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
				}
			}

			ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS, 1);
			String lname = WordUtils.capitalize(Main.getInst().getConfig().getString("armor.set" + i + ".permission")) + " Pants";
			meta.setDisplayName(lname);
			legs.setItemMeta(meta);
			ItemStack currentLegs = plinv.getLeggings();
			if (currentLegs != null && currentLegs.getType() == Material.LEATHER_LEGGINGS)
			{
				LeatherArmorMeta lam = (LeatherArmorMeta) currentLegs.getItemMeta();
				if (lam.getColor().equals(meta.getColor()))
				{
					legs.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
				}
			}

			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
			String bname = WordUtils.capitalize(Main.getInst().getConfig().getString("armor.set" + i + ".permission")) + " Shoes";
			meta.setDisplayName(bname);
			boots.setItemMeta(meta);
			ItemStack currentBoots = plinv.getBoots();
			if (currentBoots != null && currentBoots.getType() == Material.LEATHER_BOOTS)
			{
				LeatherArmorMeta lam = (LeatherArmorMeta) currentBoots.getItemMeta();
				if (lam.getColor().toString().equals(meta.getColor().toString()))
				{
					boots.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
				}
			}

			inv.setItem(slot + 9, chest);
			inv.setItem(slot + 18, legs);
			inv.setItem(slot + 27, boots);

			slot++;
		}

		return inv;
	}

	private Inventory addRemoveIcons(Inventory inv)
	{
		ItemStack reset = new ItemStack(Material.BARRIER);

		ItemMeta meta = reset.getItemMeta();
		meta.setDisplayName(u.getMessage("remove-chest"));
		reset.setItemMeta(meta);
		inv.setItem(17, reset);
		meta.setDisplayName(u.getMessage("remove-legs"));
		reset.setItemMeta(meta);
		inv.setItem(26, reset);
		meta.setDisplayName(u.getMessage("remove-boots"));
		reset.setItemMeta(meta);
		inv.setItem(35, reset);

		return inv;
	}

}
