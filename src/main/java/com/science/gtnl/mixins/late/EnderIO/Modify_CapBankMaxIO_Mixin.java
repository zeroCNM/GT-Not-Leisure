package com.science.gtnl.mixins.late.EnderIO;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import crazypants.enderio.machine.capbank.BlockItemCapBank;
import crazypants.enderio.machine.capbank.CapBankType;

@SuppressWarnings("UnusedMixin")
@Mixin(value = BlockItemCapBank.class, remap = false)
public class Modify_CapBankMaxIO_Mixin {

    @Redirect(
        method = "receiveEnergy",
        at = @At(value = "INVOKE", target = "Lcrazypants/enderio/machine/capbank/CapBankType;getMaxIO()I"),
        require = 1)
    private int modifyMaxIO(CapBankType instance) {
        if (instance == CapBankType.VIBRANT) {
            return Integer.MAX_VALUE;
        }
        return instance.getMaxIO();
    }
}
