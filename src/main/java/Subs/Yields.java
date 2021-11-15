package Subs;
import lotc.tech.farming.hobbit.FarmingUpdates;
import org.bukkit.Material;

public class Yields {

    public static int getYields(Material material){
        switch (material){
            case WOODEN_HOE :
                //1 crop, 1 seed min
                return 1;
            case STONE_HOE:
                //1 crop, 1 seed min
                return 1;
            case IRON_HOE:
                //2 crop, 1 seed min
                return 2;
            case GOLDEN_HOE:
                //2 crop, 1 seed min
                return 2;
            case DIAMOND_HOE:
                //3 crop, 1 seed min
                return 3;
            default:
                //How'd you even get here?
                return 0;

        }
    }

}
