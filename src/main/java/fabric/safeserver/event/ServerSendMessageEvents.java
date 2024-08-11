package fabric.safeserver.event;

import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;

public final class ServerSendMessageEvents {
    private ServerSendMessageEvents() {
    }

    public static final Event<ServerSendMessageEvents.AllowChat> ALLOW_CHAT = EventFactory.createArrayBacked(ServerSendMessageEvents.AllowChat.class, listeners -> (player, message) -> {
        for (ServerSendMessageEvents.AllowChat listener : listeners) {
            if (!listener.allowSendChatMessage(player, message)) {
                return false;
            }
        }

        return true;
    });

    public static final Event<ServerSendMessageEvents.AllowCommand> ALLOW_COMMAND = EventFactory.createArrayBacked(ServerSendMessageEvents.AllowCommand.class, listeners -> (player, command) -> {
        for (ServerSendMessageEvents.AllowCommand listener : listeners) {
            if (!listener.allowSendCommandMessage(player, command)) {
                return false;
            }
        }

        return true;
    });

    @FunctionalInterface
    public interface AllowChat {
        /**
         * Called when the client is about to send a chat message,
         * typically from a client GUI. Returning {@code false}
         * prevents the message from being sent, and
         *
         * @param message the message that will be sent to the server
         * @return {@code true} if the message should be sent, otherwise {@code false}
         */
        boolean allowSendChatMessage(ServerPlayerEntity player, String message);
    }

    @FunctionalInterface
    public interface AllowCommand {
        /**
         * Called when the client is about to send a command,
         * which is whenever the player executes a command
         * including client commands registered with {@code fabric-command-api}.
         * Returning {@code false} prevents the command from being sent, and
         * The command string does not include a slash at the beginning.
         *
         * @param command the command that will be sent to the server, without a slash at the beginning.
         * @return {@code true} if the command should be sent, otherwise {@code false}
         */
        boolean allowSendCommandMessage(ServerPlayerEntity player, String command);
    }
}
