package riskyken.armourersWorkshop.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.common.lib.LibBlockNames;
import riskyken.armourersWorkshop.common.lib.LibModInfo;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourerBrain;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockArmourerBrain extends AbstractModBlock implements ITileEntityProvider {

    public BlockArmourerBrain() {
        super(LibBlockNames.ARMOURER_BRAIN);
    }

    @Override
    public Block setBlockName(String name) {
        GameRegistry.registerBlock(this, "block." + name);
        return super.setBlockName(name);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        if (!world.isRemote) {
            return true;
        }
        TileEntity te = world.getTileEntity(x, y, z);
        if (te != null & te instanceof TileEntityArmourerBrain) {
            ((TileEntityArmourerBrain) te).buildArmourItem(player);
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    private IIcon sideIcon;

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
        blockIcon = register.registerIcon(LibModInfo.ID.toLowerCase() + ":"
                + "frame-side");
        sideIcon = register.registerIcon(LibModInfo.ID.toLowerCase() + ":"
                + "armourer-brain");
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        if (side < 2) {
            return blockIcon;
        }
        return sideIcon;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return null;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityArmourerBrain();
    }
}