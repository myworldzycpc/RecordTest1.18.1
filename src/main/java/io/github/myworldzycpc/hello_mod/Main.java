package io.github.myworldzycpc.hello_mod;

import net.minecraft.Util;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod("hello_mod")
@Mod.EventBusSubscriber
public class Main {

    @SubscribeEvent
    public static void playerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {

        Player player = event.getPlayer();
        Level level = player.level;
        player.sendMessage(new TextComponent("Hello, " + player.getDisplayName().getString() + ". From " + (level.isClientSide() ? "CLIENT" : "SERVER")), Util.NIL_UUID);

    }

    @SubscribeEvent
    public static void leftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getPlayer();
        player.sendMessage(new TextComponent("LeftClickBlock is fired."), Util.NIL_UUID);
    }

    @SubscribeEvent
    public static void rightClickItem(PlayerInteractEvent.RightClickItem event) {

        Player player = event.getPlayer();
        if (!player.level.isClientSide()) {
            ItemStack itemStack = event.getItemStack();
            if (itemStack.getItem() == Items.DIAMOND_SWORD) {
                HitResult hitResult = player.pick(20, 0, false);
                Vec3 location = hitResult.getLocation();
                player.teleportTo(location.x, location.y, location.z);
            }
        }
    }

//    @SubscribeEvent
//    public static void createFluid(BlockEvent.CreateFluidSourceEvent event) {
//        if (!event.getWorld().isClientSide()) {
//            ServerLevel serverLevel = (ServerLevel) event.getWorld();
//            serverLevel.players().forEach(serverPlayer -> serverPlayer.addItem(Items.APPLE.getDefaultInstance()));
//        }
//    }

    @SubscribeEvent
    public static void blockEvent(BlockEvent event) {

    }

    @SubscribeEvent
    public static void entityPlace(BlockEvent.EntityPlaceEvent event) {
        if (event.getEntity() instanceof Player player) {
            // event.getEntity()现在被转换成了玩家（如果这个实体确实是玩家），player是Player类的实例了
        }
    }
}
