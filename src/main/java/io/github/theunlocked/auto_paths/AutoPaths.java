package io.github.theunlocked.auto_paths;

import io.github.theunlocked.auto_paths.lib.ProxyCommon;
import io.github.theunlocked.auto_paths.lib.References;
import scala.Int;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = References.MODID, version = References.VERSION, dependencies = "required-after:FML;after:Thaumcraft;after:Baubles;after:ThermalExpansion")
public class AutoPaths
{
	
	
    @SidedProxy(clientSide = References.Client,
    		serverSide = References.Common)
    public static ProxyCommon proxy;
    
    @Instance(References.MODID)
	public static AutoPaths instance;
    	
    //Config Data
    public static float trampleChance = 0.9f;
    String[] trample = new String[]{"minecraft:grass -> minecraft:dirt", "minecraft:sand -> minecraft:sandstone"};
    		
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());

        config.load();

        trampleChance = 1f - (config.getFloat("Trample Chance", "AutoPaths Config", 10f, 0f, 100f, "when triggered to trample material, the chance that it is actually trampled") / 100f);
        trample = config.getStringList("Trample results", "AutoPaths Config", new String[]{"minecraft:grass->minecraft:dirt", "minecraft:sand->minecraft:sandstone"},
        		"used to set what blocks turn into what other blocks when trampled. use the format 'modid:blockname->modid:blockname' where the first block is the original and the second block is the trampled");        
        config.save();
        
        for(String str : trample){
        	String[] sepStr = str.split("->");
        	Block from = GameRegistry.findBlock(new GameRegistry.UniqueIdentifier(sepStr[0]).modId, new GameRegistry.UniqueIdentifier(sepStr[0]).name);
        	Block to = GameRegistry.findBlock(new GameRegistry.UniqueIdentifier(sepStr[1]).modId, new GameRegistry.UniqueIdentifier(sepStr[1]).name);
        	TrampleHandler.blockSwitch.put(from, to);
        }
        
        MinecraftForge.EVENT_BUS.register(new TrampleHandler());
        TrampleHandler.preInit();
}

    
    	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	
    	proxy.registerRenderInformation();
    }
    
    @EventHandler
    public void load(FMLLoadEvent event)
    {
    }
	public AutoPaths(){
		
	}	
}
