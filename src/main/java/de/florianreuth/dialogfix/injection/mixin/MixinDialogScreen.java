/*
 * This file is part of DialogFix - https://github.com/florianreuth/DialogFix
 * Copyright (C) 2025-2026 Florian Reuth <git@florianreuth.de> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.florianreuth.dialogfix.injection.mixin;

import net.minecraft.client.gui.components.AbstractContainerWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.MultiLineEditBox;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.dialog.DialogScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DialogScreen.class)
public abstract class MixinDialogScreen extends Screen {

    public MixinDialogScreen(final Component component) {
        super(component);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void selectFirstInput(CallbackInfo ci) {
        for (final GuiEventListener child : this.children()) {
            if (!(child instanceof final AbstractContainerWidget containerWidget)) {
                continue;
            }

            for (final GuiEventListener guiEventListener : containerWidget.children()) {
                if (guiEventListener instanceof EditBox || guiEventListener instanceof MultiLineEditBox) {
                    containerWidget.setFocused(guiEventListener);
                    setFocused(containerWidget);
                    break;
                }
            }
        }
    }

}
