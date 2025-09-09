package xyz.nucleoid.plasmid.impl.game.common.ui.element;

import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.elements.GuiElementInterface;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import xyz.nucleoid.plasmid.api.game.common.ui.WaitingLobbyUiElement;
import xyz.nucleoid.plasmid.api.game.common.ui.WaitingLobbyUiLayout;

import java.util.function.Consumer;

public class ReadyGameWaitingLobbyUiElement implements WaitingLobbyUiElement {
    private final WaitingLobbyUiLayout layout;
    private final Consumer<Boolean> callback;
    public boolean hasVoted = false;
    public ReadyGameWaitingLobbyUiElement(WaitingLobbyUiLayout layout, Consumer<Boolean> callback) {
        this.layout = layout;
        this.callback = callback;
    }

    @Override
    public GuiElementInterface createMainElement() {
        return new GuiElementBuilder(!this.hasVoted ? Items.GREEN_WOOL : Items.RED_WOOL )
                .setItemName(Text.translatable("text.plasmid.game.waiting_lobby.player_vote_item"))
                .setCallback((index, type, action, gui) -> {
                    if (WaitingLobbyUiElement.isClick(type, gui)) {
                        this.hasVoted = !this.hasVoted;
                        this.callback.accept(this.hasVoted);
                        this.layout.refresh();
                    }
                })
                .build();
    }
}
