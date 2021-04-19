package ht.treechop.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import ht.treechop.TreeChopMod;
import ht.treechop.client.Client;
import ht.treechop.client.gui.options.ExclusiveOptionRow;
import ht.treechop.client.gui.options.LabeledOptionRow;
import ht.treechop.client.gui.options.OptionList;
import ht.treechop.client.gui.util.GUIUtil;
import ht.treechop.client.gui.widget.StickyWidget;
import ht.treechop.common.settings.Setting;
import ht.treechop.common.settings.SettingsField;
import ht.treechop.common.settings.SneakBehavior;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Collection;
import java.util.LinkedList;

public abstract class ClientSettingsScreen extends Screen {

    protected static final int ROW_HEIGHT = 25;

    protected OptionList optionsRowList;
    private Button doneButton;

    public ClientSettingsScreen() {
        super(new TranslationTextComponent("treechop.gui.settings.title", TreeChopMod.MOD_NAME));
    }

    @Override
    protected void init() {
        super.init();

        Collection<LabeledOptionRow> optionRows = new LinkedList<>();

        optionRows.add(
                new LabeledOptionRow(
                        font,
                        new TranslationTextComponent("treechop.gui.settings.label.chopping_enabled"),
                        new ExclusiveOptionRow.Builder()
                                .add(
                                        new TranslationTextComponent("gui.yes"),
                                        () -> Client.getChopSettings().setChoppingEnabled(true),
                                        () -> makeSettingWidgetState(SettingsField.CHOPPING, true)
                                )
                                .add(
                                        new TranslationTextComponent("gui.no"),
                                        () -> Client.getChopSettings().setChoppingEnabled(false),
                                        () -> makeSettingWidgetState(SettingsField.CHOPPING, false)
                                )
                                .build()
                )
        );

        optionRows.add(
                new LabeledOptionRow(
                        font,
                        new TranslationTextComponent("treechop.gui.settings.label.felling_enabled"),
                        new ExclusiveOptionRow.Builder()
                                .add(
                                        new TranslationTextComponent("gui.yes"),
                                        () -> Client.getChopSettings().setFellingEnabled(true),
                                        () -> makeSettingWidgetState(SettingsField.FELLING, true)
                                )
                                .add(
                                        new TranslationTextComponent("gui.no"),
                                        () -> Client.getChopSettings().setFellingEnabled(false),
                                        () -> makeSettingWidgetState(SettingsField.FELLING, false)
                                )
                                .build()
                )
        );

        optionRows.add(
                new LabeledOptionRow(
                        font,
                        new TranslationTextComponent("treechop.gui.settings.label.sneaking_affects"),
                        new ExclusiveOptionRow.Builder()
                                .add(
                                        new TranslationTextComponent("treechop.gui.settings.button.chopping"),
                                        () -> Client.getChopSettings().setSneakBehavior(SneakBehavior.INVERT_CHOPPING),
                                        () -> StickyWidget.State.of(
                                                Client.getChopSettings().getSneakBehavior() == SneakBehavior.INVERT_CHOPPING,
                                                isSettingPermitted(SettingsField.CHOPPING, !Client.getChopSettings().getChoppingEnabled())
                                                        && isSettingPermitted(SettingsField.SNEAK_BEHAVIOR, SneakBehavior.INVERT_CHOPPING)
                                        )
                                )
                                .add(
                                        new TranslationTextComponent("treechop.gui.settings.button.felling"),
                                        () -> Client.getChopSettings().setSneakBehavior(SneakBehavior.INVERT_FELLING),
                                        () -> StickyWidget.State.of(
                                                Client.getChopSettings().getSneakBehavior() == SneakBehavior.INVERT_FELLING,
                                                isSettingPermitted(SettingsField.FELLING, !Client.getChopSettings().getFellingEnabled())
                                                        && isSettingPermitted(SettingsField.SNEAK_BEHAVIOR, SneakBehavior.INVERT_FELLING)
                                        )
                                )
                                .add(
                                        new TranslationTextComponent("treechop.gui.settings.button.nothing"),
                                        () -> Client.getChopSettings().setSneakBehavior(SneakBehavior.NONE),
                                        () -> makeSettingWidgetState(SettingsField.SNEAK_BEHAVIOR, SneakBehavior.NONE)
                                )
                                .build()
                )
        );

        optionRows.add(
                new LabeledOptionRow(
                        font,
                        new TranslationTextComponent("treechop.gui.settings.label.only_chop_trees_with_leaves"),
                        new ExclusiveOptionRow.Builder()
                                .add(
                                        new TranslationTextComponent("gui.yes"),
                                        () -> Client.getChopSettings().setTreesMustHaveLeaves(true),
                                        () -> makeSettingWidgetState(SettingsField.TREES_MUST_HAVE_LEAVES, true)
                                )
                                .add(
                                        new TranslationTextComponent("gui.no"),
                                        () -> Client.getChopSettings().setTreesMustHaveLeaves(false),
                                        () -> makeSettingWidgetState(SettingsField.TREES_MUST_HAVE_LEAVES, false)
                                )
                                .build()
                )
        );

        optionRows.add(
                new LabeledOptionRow(
                        font,
                        new TranslationTextComponent("treechop.gui.settings.label.chop_in_creative_mode"),
                        new ExclusiveOptionRow.Builder()
                                .add(
                                        new TranslationTextComponent("gui.yes"),
                                        () -> Client.getChopSettings().setChopInCreativeMode(true),
                                        () -> makeSettingWidgetState(SettingsField.CHOP_IN_CREATIVE_MODE, true)
                                )
                                .add(
                                        new TranslationTextComponent("gui.no"),
                                        () -> Client.getChopSettings().setChopInCreativeMode(false),
                                        () -> makeSettingWidgetState(SettingsField.CHOP_IN_CREATIVE_MODE, false)
                                )
                                .build()
                )
        );

        optionRows.add(
                new LabeledOptionRow(
                        font,
                        new TranslationTextComponent("treechop.gui.settings.label.show_chopping_indicator"),
                        new ExclusiveOptionRow.Builder()
                                .add(
                                        new TranslationTextComponent("gui.yes"),
                                        () -> Client.setChoppingIndicatorVisible(true),
                                        () -> StickyWidget.State.of(Client.getChoppingIndicatorVisible(), true)
                                )
                                .add(
                                        new TranslationTextComponent("gui.no"),
                                        () -> Client.setChoppingIndicatorVisible(false),
                                        () -> StickyWidget.State.of(!Client.getChoppingIndicatorVisible(), true)
                                )
                                .build()
                )
        );

        this.optionsRowList = addListener(new OptionList(
                minecraft,
                width,
                getListTop(),
                getListBottom(),
                ROW_HEIGHT,
                optionRows
        ));

        final int doneButtonWidth = 200;
        doneButton = addButton(new Button(
                (width - doneButtonWidth) / 2,
                getDoneButtonTop(),
                doneButtonWidth,
                GUIUtil.BUTTON_HEIGHT,
                ITextComponent.getTextComponentOrEmpty(I18n.format("gui.done")),
                button -> closeScreen()
        ));
    }

    private boolean isSettingPermitted(SettingsField field, Object value) {
        return Client.getServerPermissions().isPermitted(new Setting(field, value));
    }

    private StickyWidget.State makeSettingWidgetState(SettingsField field, Object value) {
        return StickyWidget.State.of(
                Client.getChopSettings().get(field) == value,
                Client.getServerPermissions().isPermitted(new Setting(field, value))
        );
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        doneButton.y = getDoneButtonTop();

        this.renderBackground(matrixStack);
        optionsRowList.render(matrixStack, mouseX, mouseY, partialTicks);
        drawCenteredString(matrixStack, this.font, this.title, this.width / 2, getTitleTop(), 16777215);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        // TODO: check out ClientSettingsScreen.func_243293_a for draw reordering; might be important for tooltips
    }

    protected int getTop() {
        return 32;
    }

    protected int getBottom() {
        return height - 32;
    }

    protected int getTitleTop() {
        return getTop() + 20;
    }

    protected int getListTop() {
        return getTop() + 52;
    }

    protected int getListBottom() {
        return getListTop() + OptionList.getHeightForRows(6, ROW_HEIGHT);
    }

    protected int getDoneButtonTop() {
        return getBottom() - GUIUtil.BUTTON_HEIGHT;
    }
}