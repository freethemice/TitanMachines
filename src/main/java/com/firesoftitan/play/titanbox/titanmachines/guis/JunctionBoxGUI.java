package com.firesoftitan.play.titanbox.titanmachines.guis;

import com.firesoftitan.play.titanbox.libs.blocks.TitanBlock;
import com.firesoftitan.play.titanbox.libs.managers.TitanBlockManager;
import com.firesoftitan.play.titanbox.titanmachines.TitanMachines;
import com.firesoftitan.play.titanbox.titanmachines.blocks.JunctionBoxBlock;
import com.firesoftitan.play.titanbox.titanmachines.enums.PipeChestFilterTypeEnum;
import com.firesoftitan.play.titanbox.titanmachines.enums.PipeTypeEnum;
import com.firesoftitan.play.titanbox.titanmachines.managers.ItemSorterManager;
import com.firesoftitan.play.titanbox.titanmachines.managers.PipesManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class JunctionBoxGUI {
    private final Inventory inventory;
    public static String name = "Junction Box Gui";
    private final int size = 54;
    private final Location location;
    private final String key;
    private final Player player;
    private BlockFace currentFace = BlockFace.NORTH;
    public JunctionBoxGUI(Player player, Location chest) {
        this.location = chest.clone();
        this.player = player;
        this.key = ItemSorterManager.instance.getKey(this.location);
        this.inventory = Bukkit.createInventory(null, size, JunctionBoxGUI.name);
    }

    public Location getLocation() {
        return location.clone();
    }

    public String getKey() {
        return key;
    }

    /**
     * @return player viewing gui.
     */
    @SuppressWarnings("unused")
    public Player getPlayer() {
        return player;
    }

    public void render()
    {

        ItemStack borderItem = new ItemStack(Material.CYAN_STAINED_GLASS_PANE);
        for(int i = 0; i < size; i++)
        {
            this.inventory.setItem(i, borderItem.clone());
        }
        TitanBlock titanBlock = TitanBlockManager.getTitanBlock(JunctionBoxBlock.titanID, this.location);
        JunctionBoxBlock junctionBoxBlock = JunctionBoxBlock.convert(titanBlock);
        if (junctionBoxBlock == null) return;
        updateInventoryWindow();

        ItemStack item;
        int i = 18;
        for (BlockFace face: JunctionBoxBlock.blockFaces) {
            item = new ItemStack(Material.GREEN_SHULKER_BOX);
            String s = "Click to change to this inventory";
            if (face == currentFace)
            {
                item = new ItemStack(Material.RED_SHULKER_BOX);
                s = "This is the current inventory being shown.";
            }
            item = TitanMachines.itemStackTool.changeName(item, ChatColor.YELLOW + "Inventory: " + ChatColor.GREEN + face );
            item = TitanMachines.itemStackTool.addLore(item, ChatColor.AQUA + s);
            item = addFilterList(face, junctionBoxBlock, item);
            item = TitanMachines.nbtTool.set(item, "button", i);
            item = TitanMachines.nbtTool.set(item, "location", this.location);
            item = TitanMachines.nbtTool.set(item, "current_face", this.currentFace.name());
            this.inventory.setItem(i, item.clone());
            i++;
        }
        i = 27;
        for (BlockFace face: JunctionBoxBlock.blockFaces) {
            List<ItemStack> filterList = junctionBoxBlock.getFilterList(face);
            item = new ItemStack(Material.ANVIL);
            if (select.containsKey(player.getUniqueId()))
            {
                if (select.get(player.getUniqueId()).getBlockFace() == face)
                {
                    if (select.get(player.getUniqueId()).getPipeChestFilterType() == PipeChestFilterTypeEnum.TOTAL_MATCH) item = new ItemStack(Material.CLOCK);
                    else if (select.get(player.getUniqueId()).getPipeChestFilterType() == PipeChestFilterTypeEnum.MATERIAL_ONLY)  item = new ItemStack(Material.COMPASS);
                    else item = new ItemStack(Material.BARRIER);

                }
            }
            item = TitanMachines.itemStackTool.changeName(item, ChatColor.YELLOW + "Move this items to: " + ChatColor.GREEN + face);
            item = TitanMachines.itemStackTool.addLore(item, ChatColor.WHITE + "Click here to addItem/remove item from your inventory.", ChatColor.WHITE + "Right click to cancel", ChatColor.YELLOW + "Items: " + ChatColor.GREEN + filterList.size() + "/20");
            item = addFilterList(face, junctionBoxBlock, item);
            item = TitanMachines.nbtTool.set(item, "button", i);
            item = TitanMachines.nbtTool.set(item, "location", this.location);
            item = TitanMachines.nbtTool.set(item, "current_face", this.currentFace.name());
            this.inventory.setItem(i, item.clone());
            i++;
        }
        i = 36;
        for (BlockFace face: JunctionBoxBlock.blockFaces) {
            item = new ItemStack(Material.NETHER_STAR);
            item = TitanMachines.itemStackTool.changeName(item, ChatColor.YELLOW + "Set to No Filter: " + ChatColor.GREEN + face);
            item = TitanMachines.itemStackTool.addLore(item, ChatColor.WHITE + "Click here to set this slot to catch everything that doesn't have a filter");
            if (junctionBoxBlock.getNoFilter() == face)
            {
                item = new ItemStack(Material.DISPENSER);
                item = TitanMachines.itemStackTool.changeName(item, ChatColor.YELLOW + "This is the No Filter slot: " + ChatColor.GREEN + face);
            }

            item = TitanMachines.nbtTool.set(item, "button", i);
            item = TitanMachines.nbtTool.set(item, "location", this.location);
            item = TitanMachines.nbtTool.set(item, "current_face", this.currentFace.name());
            this.inventory.setItem(i, item.clone());
            i++;
        }
        i = 45;
        for (BlockFace face: JunctionBoxBlock.blockFaces) {
            item = new ItemStack(Material.NETHER_STAR);
            item = TitanMachines.itemStackTool.changeName(item, ChatColor.YELLOW + "Set to Overflow: " + ChatColor.GREEN + face);
            item = TitanMachines.itemStackTool.addLore(item, ChatColor.WHITE + "Click here to set this slot to overflow, when this section is full it will be moved here.");
            if (junctionBoxBlock.getOverflow() == face)
            {
                item = new ItemStack(Material.DROPPER);
                item = TitanMachines.itemStackTool.changeName(item, ChatColor.YELLOW + "This is the Overflow slot: " + ChatColor.GREEN + face);
            }

            item = TitanMachines.nbtTool.set(item, "button", i);
            item = TitanMachines.nbtTool.set(item, "location", this.location);
            item = TitanMachines.nbtTool.set(item, "current_face", this.currentFace.name());
            this.inventory.setItem(i, item.clone());
            i++;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                updateInventoryWindow();
                if (!player.getOpenInventory().getTitle().equals(JunctionBoxGUI.name)) this.cancel();
            }
        }.runTaskTimer(TitanMachines.instants, 10, 5);
    }

    private static ItemStack addFilterList(BlockFace face, JunctionBoxBlock junctionBoxBlock, ItemStack item) {
        List<ItemStack> filterList = junctionBoxBlock.getFilterList(face);
        for (ItemStack itemStack: filterList)
        {
            String name1 = TitanMachines.itemStackTool.getName(itemStack);
            name1 = TitanMachines.formattingTool.fixCapitalization(name1);
            if (junctionBoxBlock.getFilterType(face, itemStack).getValue() > -1)
                item = TitanMachines.itemStackTool.addLore(item, ChatColor.YELLOW + name1 + ": " + ChatColor.GREEN + junctionBoxBlock.getFilterType(face, itemStack).getCaption());
        }
        return item;
    }

    private void updateInventoryWindow() {
        TitanBlock titanBlock = TitanBlockManager.getTitanBlock(JunctionBoxBlock.titanID, this.location);
        JunctionBoxBlock junctionBoxBlock = JunctionBoxBlock.convert(titanBlock);
        if (junctionBoxBlock != null) {
            Inventory inventory1 = junctionBoxBlock.getInventory(currentFace);
            for (int i = 0; i < inventory1.getSize(); i++) {
                this.inventory.setItem(i, inventory1.getItem(i));
            }
        }
    }

    @SuppressWarnings("unused")
    public BlockFace getCurrentFace() {
        return currentFace;
    }

    public void setCurrentFace(BlockFace currentFace) {
        this.currentFace = currentFace;
    }

    public void open()
    {
        this.render();
        this.player.openInventory(this.inventory);
    }
    private static final HashMap<UUID, SelectorGUI> select = new HashMap<UUID, SelectorGUI>();
    public static void setTypeSelect(Player player, ItemStack item)
    {
        if (select.containsKey(player.getUniqueId())) {
            SelectorGUI s = select.get(player.getUniqueId());
            TitanBlock titanBlock = TitanBlockManager.getTitanBlock(JunctionBoxBlock.titanID, s.getLocation());
            JunctionBoxBlock junctionBoxBlock = JunctionBoxBlock.convert(titanBlock);
            if (junctionBoxBlock != null) {
                List<ItemStack> filterList = junctionBoxBlock.getFilterList(s.getBlockFace());
                if (s.getPipeChestFilterType() == PipeChestFilterTypeEnum.DISABLED || filterList.size() < 20)
                {
                    filterList.add(item.clone());
                    junctionBoxBlock.setFilterType(s.getBlockFace(), item, s.getPipeChestFilterType());
                    junctionBoxBlock.setFilterList(s.getBlockFace(), filterList);

                    select.remove(player.getUniqueId());
                }
            }
            JunctionBoxGUI junctionBoxGUI = new JunctionBoxGUI(player, s.getLocation());
            junctionBoxGUI.setCurrentFace(s.getcurrentBlockFace());
            junctionBoxGUI.open();
        }


    }
    public static void onClickButtonEvent(Player player, Integer button, Location location, Location sorting, BlockFace blockFace, ClickType clickType) {
        SelectorGUI s = select.get(player.getUniqueId());
        if (clickType == ClickType.RIGHT && s != null)
        {
            select.remove(player.getUniqueId());
            JunctionBoxGUI junctionBoxGUI = new JunctionBoxGUI(player, location);
            junctionBoxGUI.setCurrentFace(blockFace);
            junctionBoxGUI.open();
            return;
        }
        if (button >= 18 && button < 18 + JunctionBoxBlock.blockFaces.length)
        {
            int select = button - 18;
            BlockFace blockFaceN = JunctionBoxBlock.blockFaces[select];
            JunctionBoxGUI junctionBoxGUI = new JunctionBoxGUI(player, location);
            junctionBoxGUI.setCurrentFace(blockFaceN);
            junctionBoxGUI.open();
        }

        if (button >= 27 && button < 27 + JunctionBoxBlock.blockFaces.length)
        {
            if (s == null) {
                s = new SelectorGUI(location, sorting, null);
            }
            s.setBlockFace(JunctionBoxBlock.blockFaces[button - 27]);
            s.setCurrentBlockFace(blockFace);
            if (s.getPipeChestFilterType() == null) s.setPipeChestFilterType(PipeChestFilterTypeEnum.TOTAL_MATCH);
            else if (s.getPipeChestFilterType() == PipeChestFilterTypeEnum.TOTAL_MATCH) s.setPipeChestFilterType(PipeChestFilterTypeEnum.MATERIAL_ONLY);
            else if (s.getPipeChestFilterType() == PipeChestFilterTypeEnum.MATERIAL_ONLY) s.setPipeChestFilterType(PipeChestFilterTypeEnum.DISABLED);
            else s.setPipeChestFilterType(PipeChestFilterTypeEnum.TOTAL_MATCH);
            select.put(player.getUniqueId(), s);
            JunctionBoxGUI junctionBoxGUI = new JunctionBoxGUI(player, location);
            junctionBoxGUI.setCurrentFace(blockFace);
            junctionBoxGUI.open();

        }
        if (button >= 36 && button < 36 + JunctionBoxBlock.blockFaces.length)
        {
            BlockFace blockFace1 = JunctionBoxBlock.blockFaces[button - 36];
            TitanBlock titanBlock = TitanBlockManager.getTitanBlock(JunctionBoxBlock.titanID, location);
            JunctionBoxBlock convert = JunctionBoxBlock.convert(titanBlock);
            if (convert != null) convert.setNoFilter(blockFace1);
            JunctionBoxGUI junctionBoxGUI = new JunctionBoxGUI(player, location);
            junctionBoxGUI.setCurrentFace(blockFace);
            junctionBoxGUI.open();
        }
        if (button >= 45 && button < 45 + JunctionBoxBlock.blockFaces.length)
        {
            BlockFace blockFace1 = JunctionBoxBlock.blockFaces[button - 45];
            TitanBlock titanBlock = TitanBlockManager.getTitanBlock(JunctionBoxBlock.titanID, location);
            JunctionBoxBlock convert = JunctionBoxBlock.convert(titanBlock);
            if (convert != null) convert.setOverflow(blockFace1);

            JunctionBoxGUI junctionBoxGUI = new JunctionBoxGUI(player, location);
            junctionBoxGUI.setCurrentFace(blockFace);
            junctionBoxGUI.open();
        }
    }

}
