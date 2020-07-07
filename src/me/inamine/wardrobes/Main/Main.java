package me.inamine.wardrobes.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	Utils u = new Utils();
	private static Main inst;

	public Main()
	{
		inst = this;
	}

	public static Main getInst()
	{
		return inst;
	}

	public void onEnable()
	{
		FM.checkFiles();
		saveDefaultConfig();
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new ListenerClass(), this);
	}

	public void onDisable()
	{
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			if (args.length == 0)
			{
				sender.sendMessage(u.getMessage("no-console"));
				return true;
			} else if (args.length == 1)
			{
				if (args[0].equalsIgnoreCase("reload"))
				{
					reloadConfig();
					FM.checkFiles();
					sender.sendMessage(u.getMessage("reload-complete"));
					return true;
				}
			}
		} else
		{
			Player pl = (Player) sender;
			if (args.length == 0)
			{
				Menu m = new Menu();
				m.openMenu(pl,1);
				return true;
			} else if (args.length == 1 && pl.hasPermission("wardrobe.reload"))
			{
				if (args[0].equalsIgnoreCase("reload"))
				{
					reloadConfig();
					FM.checkFiles();
					sender.sendMessage(u.getMessage("reload-complete"));
					return true;
				}
			}
		}

		return false;
	}

}
