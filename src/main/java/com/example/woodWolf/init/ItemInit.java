package com.example.woodWolf.init;

import com.example.woodWolf.WoodWolf;
import com.example.woodWolf.WoodWolf.WoodWolfItemGroup;
import com.example.woodWolf.items.ModSpawnEggItem;
import com.example.woodWolf.items.SpecialItem;
import com.example.woodWolf.items.SpecialSwordItem;
import com.example.woodWolf.util.ModItemTiers;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	//https://www.youtube.com/watch?v=0xho33lA3-k&list=PLaevjqy3XufYmltqo0eQusnkKVN7MpTUe&index=21&t=0s
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, WoodWolf.MOD_ID);

	public static final RegistryObject<SpecialItem> example_item = ITEMS.register("example_item",
			() -> new SpecialItem(new Item.Properties().group(WoodWolfItemGroup.instance)));

	public static final RegistryObject<SpecialSwordItem> changeling_anti_magic_sword = ITEMS.register("changeling_anti_magic_sword",
			() -> new SpecialSwordItem(ModItemTiers.EXAMPLE, 7, 5.0f,
					new Item.Properties().group(WoodWolfItemGroup.instance)));

	//voor hex colors https://www.mathsisfun.com/hexadecimal-decimal-colors.html
	public static final RegistryObject<ModSpawnEggItem> WOODWOLF_SPAWN_EGG = ITEMS.register("woodwolf_spawn_egg",
			() -> new ModSpawnEggItem(ModEntityTypes.WOODWOLF_ENTITY, 0x4999987, 0x2892061,
					new Item.Properties().group(WoodWolfItemGroup.instance).maxStackSize(64)));

}
