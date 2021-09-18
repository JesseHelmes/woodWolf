package com.example.woodWolf;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.woodWolf.entities.WoodWolfEntity;
import com.example.woodWolf.init.BlockInit;
//import com.example.woodWolf.init.ItemInit;
import com.example.woodWolf.init.ItemInit;
import com.example.woodWolf.init.ModEntityTypes;
//import com.example.woodWolf.init.ModTileEntityTypes;
import com.example.woodWolf.items.ModSpawnEggItem;
import com.example.woodWolf.world.gen.ModEntityGen;
import com.example.woodWolf.world.gen.TutorialOreGen;


//The value here should match an entry in the META-INF/mods.toml file
@Mod("woodwolf")
@Mod.EventBusSubscriber(modid = WoodWolf.MOD_ID, bus = Bus.MOD)
public class WoodWolf
{
	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "woodwolf";

	public static WoodWolf instance;

	/*

	 textures upload en dan weer download
	 https://minecraft.novaskin.me/skin/6344738968961024/stick

	woodwolf en de changeling items een keer verdelen in 2 mods wanneer ik dat ECHT nodig vindt natuurlijk

	command die alle wild woodwolfs killed en misschien 1 die all ground items van hun delete
	 */

	public WoodWolf() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::doClientStuff);

		instance = this;

		ItemInit.ITEMS.register(modEventBus);
		BlockInit.BLOCKS.register(modEventBus);
		ModEntityTypes.ENTITY_TYPES.register(modEventBus);
		//tile
		//ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
		TutorialOreGen.generateOre();
		ModEntityGen.spawnEntityGen();
	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();

		BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			final Item.Properties properties = new Item.Properties().group(WoodWolfItemGroup.instance);
			final BlockItem blockItem = new BlockItem(block, properties);
			blockItem.setRegistryName(block.getRegistryName());
			registry.register(blockItem);
		});

		LOGGER.debug("Registered BlockItems!");
	}

	private void setup(final FMLCommonSetupEvent event) {

	}

	private void doClientStuff(final FMLClientSetupEvent event) {

	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {

	}

	@SubscribeEvent
	public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
		ModSpawnEggItem.initSpawnEggs();
	}

	public static class WoodWolfItemGroup extends ItemGroup {
		public static final ItemGroup instance = new WoodWolfItemGroup(ItemGroup.GROUPS.length, "woodwolf");

		private WoodWolfItemGroup(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemInit.changeling_anti_magic_sword.get());//BlockInit.dark_stone
		}
	}


	@SubscribeEvent
	public void creeperAddGoals(EntityJoinWorldEvent event){
		if (!(event.getEntity() instanceof CreeperEntity)) {
			return;
		}

		CreeperEntity creeper = (CreeperEntity) event.getEntity();

		creeper.goalSelector.addGoal(3, new AvoidEntityGoal<>(creeper, WoodWolfEntity.class, 6.0F, 1.0D, 1.2D));
	}

	/*
	  Woodwolf I can add entity proximity to living event and check for living entity and then check if there is a dark stone block nearby instead if doing it on the block to take potion effects away.
Ground items half of the block entity
Grow Block 16 item 8
	 
	 */

	//remove potion effects test!
	//@SubscribeEvent
	public void livingTick(LivingEvent event) {
		if (!(event.getEntity() instanceof LivingEntity)) {
			return;
		}

		LivingEntity livingEntity = (LivingEntity) event.getEntity();

		if (livingEntity.world.isRemote || !livingEntity.isAlive() || !livingEntity.isServerWorld()) {
			return;
		}

		this.removeMagic(livingEntity);

	}
	
	//removes all potion effects
	public void removeMagic(LivingEntity livingEntity) {
		if(livingEntity.getActivePotionEffects().isEmpty()) {
			return;
		}
		
		/*Predicate<ItemEntity> DARK_STONE_BLOCK = (itemEntity) -> {
			Item item = itemEntity.getItem().getItem();
			return (item == Blocks.BAMBOO.asItem()) && itemEntity.isAlive()
					&& !itemEntity.cannotPickup();
		};
		

		//checks for dark blocks in long range
		List<> list = livingEntity.world.getEntitiesWithinAABB(.class, livingEntity.getBoundingBox().grow(16.0D),
				DARK_STONE_BLOCK_ITEM);
		
		if(!list.isEmpty()) {
			livingEntity.clearActivePotions();
			return;
		}*/
		
		Predicate<ItemEntity> DARK_STONE_BLOCK_ITEM = (itemEntity) -> {
			Item item = itemEntity.getItem().getItem();
			return (item == BlockInit.DARK_STONE.get().asItem()) && itemEntity.isAlive()
					&& !itemEntity.cannotPickup();
		};


		//checks for dark block items in short range
		List<ItemEntity> list = livingEntity.world.getEntitiesWithinAABB(ItemEntity.class, livingEntity.getBoundingBox().grow(8.0D),
				DARK_STONE_BLOCK_ITEM);
		if(!list.isEmpty()) {
			livingEntity.clearActivePotions();
			return;
		}
	}
}
