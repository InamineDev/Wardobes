package me.inamine.wardrobes.Main;

import net.md_5.bungee.api.ChatColor;

public class Utils
{

	public String getMessage(String st)
	{
		String msg = FM.getMsg().getString(st);
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		return msg;
	}
}
