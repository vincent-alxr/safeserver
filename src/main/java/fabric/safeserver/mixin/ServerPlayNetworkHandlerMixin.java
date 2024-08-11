package fabric.safeserver.mixin;

import fabric.safeserver.event.ServerSendMessageEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {

    @Shadow
    public ServerPlayerEntity player;

    @Inject(method = "onChatMessage", at = @At("HEAD"), cancellable = true)
    private void fabric_allowSendChatMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
        if (!ServerSendMessageEvents.ALLOW_CHAT.invoker().allowSendChatMessage(this.player, packet.chatMessage())) {
            ci.cancel();
        }
    }

    @Inject(method = "onCommandExecution", at = @At("HEAD"), cancellable = true)
    private void fabric_allowSendCommandMessage(CommandExecutionC2SPacket packet, CallbackInfo ci) {
        String command = packet.command();
        if (!ServerSendMessageEvents.ALLOW_COMMAND.invoker().allowSendCommandMessage(this.player, command)) {
            ci.cancel();
        }
    }
}
