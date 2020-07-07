package me.inamine.wardrobes.Main;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class ListenerClass implements Listener
{
	Utils u = new Utils();

	@EventHandler
	public void onInvClick(InventoryClickEvent e)
	{
		if (e.getSlotType().equals(SlotType.ARMOR))
		{
			if (!(e.getRawSlot() == 5))
			{
				e.setCancelled(true);
			}
		}

		int page = 0;
		if (e.getView().getTitle().equals((u.getMessage("inventory-title") + u.getMessage("page-1"))))
		{
			page = 1;
		} else if (e.getView().getTitle().equals((u.getMessage("inventory-title") + u.getMessage("page-2"))))
		{
			page = 2;
		} else
			return;

		e.setCancelled(true);

		Menu m = new Menu();
		if (e.getSlot() >= 0 && e.getSlot() < 45 && !e.getCurrentItem().getType().equals(Material.AIR)
				&& !e.getCurrentItem().getType().equals(Material.GRAY_STAINED_GLASS_PANE))
		{

			Player pl = (Player) e.getWhoClicked();
			PlayerInventory plinv = pl.getInventory();

			if (e.getSlot() == 44 && page == 1)
				m.openMenu(pl, 2);
			else if (e.getSlot() == 36 && page == 2)
				m.openMenu(pl, 1);

			if (e.getCurrentItem().getType().equals(Material.BARRIER) && e.getSlot() == 17)
			{
				plinv.setChestplate(new ItemStack(Material.AIR));
				m.openMenu(pl, page);
				return;
			} else if (e.getCurrentItem().getType().equals(Material.BARRIER) && e.getSlot() == 26)
			{
				plinv.setLeggings(new ItemStack(Material.AIR));
				m.openMenu(pl, page);
				return;
			} else if (e.getCurrentItem().getType().equals(Material.BARRIER) && e.getSlot() == 35)
			{
				plinv.setBoots(new ItemStack(Material.AIR));
				m.openMenu(pl, page);
				return;
			} else
			{
				if (e.getCurrentItem().getType().equals(Material.LEATHER_CHESTPLATE))
				{
					if (!e.getCurrentItem().getEnchantments().containsKey(Enchantment.SILK_TOUCH))
					{
						int i = e.getSlot();
						while (i > 8)
						{
							i = i - 9;
						}
						if (e.getInventory().getItem(i).getType().equals(Material.GREEN_STAINED_GLASS_PANE))
						{
							ItemStack item = e.getCurrentItem();
							item.removeEnchantment(Enchantment.SILK_TOUCH);
							ItemMeta meta = item.getItemMeta();
							meta.setUnbreakable(true);
							item.setItemMeta(meta);
							plinv.setChestplate(item);
							m.openMenu(pl, page);
						}
					}
					return;
				} else if (e.getCurrentItem().getType().equals(Material.LEATHER_LEGGINGS))
				{
					if (!e.getCurrentItem().getEnchantments().containsKey(Enchantment.SILK_TOUCH))
					{
						int i = e.getSlot();
						while (i > 8)
						{
							i = i - 9;
						}
						if (e.getInventory().getItem(i).getType().equals(Material.GREEN_STAINED_GLASS_PANE))
						{
							ItemStack item = e.getCurrentItem();
							item.removeEnchantment(Enchantment.SILK_TOUCH);
							ItemMeta meta = item.getItemMeta();
							meta.setUnbreakable(true);
							item.setItemMeta(meta);
							plinv.setLeggings(item);
							m.openMenu(pl, page);
						}
					}
					return;
				} else if (e.getCurrentItem().getType().equals(Material.LEATHER_BOOTS))
				{
					if (!e.getCurrentItem().getEnchantments().containsKey(Enchantment.SILK_TOUCH))
					{
						int i = e.getSlot();
						while (i > 8)
						{
							i = i - 9;
						}
						if (e.getInventory().getItem(i).getType().equals(Material.GREEN_STAINED_GLASS_PANE))
						{
							ItemStack item = e.getCurrentItem();
							item.removeEnchantment(Enchantment.SILK_TOUCH);
							ItemMeta meta = item.getItemMeta();
							meta.setUnbreakable(true);
							item.setItemMeta(meta);
							plinv.setBoots(item);
							m.openMenu(pl, page);
						}
					}
					return;
				}
			}

		} else
			return;

	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e)
	{
		List<ItemStack> list = e.getDrops();
		Iterator<ItemStack> i = list.iterator();
		while (i.hasNext())
		{
			ItemStack item = i.next();
			if (item.getType().equals(Material.LEATHER_CHESTPLATE) || item.getType().equals(Material.LEATHER_LEGGINGS)
					|| item.getType().equals(Material.LEATHER_BOOTS))
			{
				i.remove();
			}
		}
	}

	@EventHandler
	public void onDrag(InventoryDragEvent e)
	{

		if (e.getRawSlots().contains(6) || e.getRawSlots().contains(7) || e.getRawSlots().contains(8))
		{
			e.setCancelled(true);
			return;
		}

	}

}
