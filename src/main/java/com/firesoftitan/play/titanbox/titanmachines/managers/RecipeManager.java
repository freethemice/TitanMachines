package com.firesoftitan.play.titanbox.titanmachines.managers;

import com.firesoftitan.play.titanbox.libs.managers.TitanBlockManager;
import com.firesoftitan.play.titanbox.titanmachines.TitanMachines;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static com.firesoftitan.play.titanbox.titanmachines.TitanMachines.tools;

public class RecipeManager {
    public RecipeManager() {
        addPipes();
        addBlockBreaker();
        addLumberjack();
        AddShorting();
        addChunkHopper();
        addAreaHopper();
        addTrash();
        addJunctionBox();
        addMobKiller();
    }
    private void addMobKiller()
    {
        ItemStack[] matrix = new ItemStack[9];
        matrix[0] = new ItemStack(Material.COPPER_INGOT);
        matrix[1] = new ItemStack(Material.IRON_SWORD);
        matrix[2] = new ItemStack(Material.COPPER_INGOT);
        matrix[3] = new ItemStack(Material.IRON_INGOT);
        matrix[4] = TitanMachines.instants.getBlockBreaker();
        matrix[5] = new ItemStack(Material.IRON_INGOT);
        matrix[6] = new ItemStack(Material.COPPER_INGOT);
        matrix[7] = new ItemStack(Material.REDSTONE);
        matrix[8] = new ItemStack(Material.COPPER_INGOT);
        ItemStack partItem = TitanMachines.instants.getMobKiller();
        partItem.setAmount(1);
        TitanBlockManager.registerTitanBlock(TitanMachines.instants, partItem, matrix);
    }
    private void addJunctionBox()
    {
        ItemStack[] matrix = new ItemStack[9];
        matrix[0] = new ItemStack(Material.COPPER_INGOT);
        matrix[1] = new ItemStack(Material.CHEST);
        matrix[2] = new ItemStack(Material.COPPER_INGOT);
        matrix[3] = TitanMachines.instants.getPipe();
        matrix[4] = new ItemStack(Material.CHEST);
        matrix[5] = TitanMachines.instants.getPipe();
        matrix[6] = new ItemStack(Material.COPPER_INGOT);
        matrix[7] = new ItemStack(Material.REDSTONE);
        matrix[8] = new ItemStack(Material.COPPER_INGOT);
        ItemStack partItem = TitanMachines.instants.getJunctionBox();
        partItem.setAmount(1);
        TitanBlockManager.registerTitanBlock(TitanMachines.instants, partItem, matrix);
    }
    private void addPipes() {
        ItemStack[] matrix = new ItemStack[9];
        matrix[0] = new ItemStack(Material.COPPER_INGOT);
        matrix[1] = new ItemStack(Material.COPPER_INGOT);
        matrix[2] = new ItemStack(Material.COPPER_INGOT);
        matrix[3] = new ItemStack(Material.WATER_BUCKET);
        matrix[4] = new ItemStack(Material.WATER_BUCKET);
        matrix[5] = new ItemStack(Material.WATER_BUCKET);
        matrix[6] = new ItemStack(Material.COPPER_INGOT);
        matrix[7] = new ItemStack(Material.COPPER_INGOT);
        matrix[8] = new ItemStack(Material.COPPER_INGOT);
        ItemStack partItem = TitanMachines.instants.getPipe();
        partItem.setAmount(3);
        TitanBlockManager.registerTitanBlock(TitanMachines.instants, partItem, matrix);
        //tools.getRecipeTool().addAdvancedRecipe(partItem, matrix);

    }
    private void addAreaHopper() {
        ItemStack[] matrix = new ItemStack[9];
        matrix[0] = new ItemStack(Material.COPPER_INGOT);
        matrix[1] = new ItemStack(Material.COPPER_INGOT);
        matrix[2] = new ItemStack(Material.COPPER_INGOT);
        matrix[3] = TitanMachines.instants.getPipe();
        matrix[4] = new ItemStack(Material.HOPPER);
        matrix[5] = TitanMachines.instants.getPipe();
        matrix[6] = new ItemStack(Material.COPPER_INGOT);
        matrix[7] = new ItemStack(Material.COPPER_INGOT);
        matrix[8] = new ItemStack(Material.COPPER_INGOT);

        ItemStack partItem = TitanMachines.instants.getAreaHopper();
        tools.getRecipeTool().addAdvancedRecipe(partItem, matrix);
    }
    private void addChunkHopper() {
        ItemStack[] matrix = new ItemStack[9];
        matrix[0] = new ItemStack(Material.COPPER_INGOT);
        matrix[1] = new ItemStack(Material.REDSTONE);
        matrix[2] = new ItemStack(Material.COPPER_INGOT);
        matrix[3] = TitanMachines.instants.getPipe();
        matrix[4] = TitanMachines.instants.getAreaHopper();
        matrix[5] = TitanMachines.instants.getPipe();
        matrix[6] = new ItemStack(Material.COPPER_INGOT);
        matrix[7] = new ItemStack(Material.REDSTONE);
        matrix[8] = new ItemStack(Material.COPPER_INGOT);

        ItemStack partItem = TitanMachines.instants.getChunkHopper();
        tools.getRecipeTool().addAdvancedRecipe(partItem, matrix);
    }
    private void AddShorting() {
        ItemStack[] matrix = new ItemStack[9];
        matrix[0] = new ItemStack(Material.COPPER_INGOT);
        matrix[1] = TitanMachines.instants.getChunkHopper();
        matrix[2] = new ItemStack(Material.COPPER_INGOT);
        matrix[3] = TitanMachines.instants.getPipe();
        matrix[4] = new ItemStack(Material.REDSTONE);
        matrix[5] = TitanMachines.instants.getPipe();
        matrix[6] = new ItemStack(Material.COPPER_INGOT);
        matrix[7] = TitanMachines.instants.getChunkHopper();
        matrix[8] = new ItemStack(Material.COPPER_INGOT);

        ItemStack partItem = TitanMachines.instants.getItemSorter();
        tools.getRecipeTool().addAdvancedRecipe(partItem, matrix);
    }
    private void addBlockBreaker() {

        ItemStack[] matrix = new ItemStack[9];
        matrix[0] = new ItemStack(Material.COPPER_INGOT);
        matrix[1] = new ItemStack(Material.REDSTONE);
        matrix[2] = new ItemStack(Material.COPPER_INGOT);
        matrix[3] = TitanMachines.instants.getPipe();
        matrix[4] = new ItemStack(Material.DISPENSER);
        matrix[5] = TitanMachines.instants.getPipe();
        matrix[6] = new ItemStack(Material.COPPER_INGOT);
        matrix[7] = new ItemStack(Material.REDSTONE);
        matrix[8] = new ItemStack(Material.COPPER_INGOT);

        ItemStack partItem = TitanMachines.instants.getBlockBreaker();
        tools.getRecipeTool().addAdvancedRecipe(partItem, matrix);
    }
    private void addLumberjack() {

        ItemStack[] matrix = new ItemStack[9];
        matrix[0] = new ItemStack(Material.COPPER_INGOT);
        matrix[1] = new ItemStack(Material.IRON_AXE);
        matrix[2] = new ItemStack(Material.COPPER_INGOT);
        matrix[3] = new ItemStack(Material.IRON_INGOT);
        matrix[4] = TitanMachines.instants.getBlockBreaker();
        matrix[5] = new ItemStack(Material.IRON_INGOT);
        matrix[6] = new ItemStack(Material.COPPER_INGOT);
        matrix[7] = new ItemStack(Material.REDSTONE);
        matrix[8] = new ItemStack(Material.COPPER_INGOT);

        ItemStack partItem = TitanMachines.instants.getLumberjack();
        TitanBlockManager.registerTitanBlock(TitanMachines.instants, partItem, matrix);
    }
    private void addTrash() {

        ItemStack[] matrix = new ItemStack[9];
        matrix[0] = new ItemStack(Material.COPPER_INGOT);
        matrix[1] = new ItemStack(Material.COPPER_INGOT);
        matrix[2] = new ItemStack(Material.COPPER_INGOT);
        matrix[3] = new ItemStack(Material.COPPER_INGOT);
        matrix[4] = new ItemStack(Material.BARREL);
        matrix[5] = new ItemStack(Material.COPPER_INGOT);
        matrix[6] = new ItemStack(Material.COPPER_INGOT);
        matrix[7] = new ItemStack(Material.COPPER_INGOT);
        matrix[8] = new ItemStack(Material.COPPER_INGOT);

        ItemStack partItem = TitanMachines.instants.getTrashBarrel();
        tools.getRecipeTool().addAdvancedRecipe(partItem, matrix);
    }
}
