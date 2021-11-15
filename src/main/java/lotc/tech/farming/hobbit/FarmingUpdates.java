package lotc.tech.farming.hobbit;

import Subs.Yields;
import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class FarmingUpdates extends JavaPlugin{
    //An array of all the vanilla hoe types, for use when checking if the item used by the player is a hoe
    Material[] hoes = {Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.GOLDEN_HOE, Material.DIAMOND_HOE};


    //apparently required calls
    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    /*
   Heavy inspiration here from the FarmingUpgrades mod
   The plan is to track if the block is a crop, then if the item used was a hoe
    */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onFarm(BlockBreakEvent event, BlockData blockData) {
        var player = event.getPlayer();
        var centre = event.getBlock();
        Boolean hoeFlag = false; //Defines if you have a hoe or not
        int yield; //Defining the amount of crops that get dropped on using a hoe

        //Ignoring what was previously said, looking through the Bukkit documentation there's a datapoint that tracks a crops growth
        //In the words of every smash player, We Take Those
        if (centre.getBlockData() instanceof Ageable isCrop) {

            //This line checks to see if a crop is still growing. If it is, it's a normal break
            if(isCrop.getAge() != isCrop.getMaximumAge()) return;

            //All that remains now are crops that are at their maximum age
            //From here, the next steps are checking that a hoe is used
            //GetMainHand is not the same as getItemInMainHand, because computers ifg
            var toolUsed = player.getInventory().getItemInMainHand();
            if (toolUsed.getType() == null){
                //If the user hits the crop with their hands, poof, crop gone. I may swap this out so this is the ELSE
                //Update on this, with the Array loop,there's no difference where I put this
                //Update on the update, this many IFs makes me sick. What is this? Some kind of AI? /j
                //Yet More Updates, probably going to switch when I work out Enums
                centre.setType(Material.AIR);
                return;
                //Ya gotta be sure apparently
                //I dunno man, I was tired, this is probably going to be scrapped anyway, I found the Enum for Material and that looks promissing
            } else if (toolUsed.getType() != null){
                //Quick little loop to check if the tool used exists in the array
                for (Material i : hoes) {
                    if (toolUsed.getType() == i){
                        //Look at that, ya got a hoe
                        hoeFlag = true;
                    }
                    //You didn't get a hoe, go back to normal please
                    if (hoeFlag = false){
                        centre.setType(Material.AIR);
                        return;
                    } else{
                        //Now the real code begins
                        //Select Case seems like my best bet here
                        //Run through my Hoe Types, set the yield
                        //UPDATE - They're switches here, crazy
                        //UPDATE - I need to work out subroutines, this is going to be stupid otherwise

                        //Jokes on me I guess, that's happening in the Sub
                        int cropYields = Yields.getYields(toolUsed.getType());

                        //Cleaner than expected, hopefully that works
                        //Next up is working out the rest
                        //I think it'd be best to work out fortune enchanctments, and probably exclude Silk Touch
                        if (toolUsed.getEnchantments() == Enchantment.LOOT_BONUS_BLOCKS) {
                        //Below equation from https://minecraft.fandom.com/wiki/Fortune
                            cropYields = cropYields*((1/(toolUsed.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 2)) + ((toolUsed.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1)/2));
                        }
                        //We should now have Crop Yields right
                        //Now we're just looking to find the crop being broken, that way we can find the seeds too


                        }
                    }
                }
            }

        }
    }




