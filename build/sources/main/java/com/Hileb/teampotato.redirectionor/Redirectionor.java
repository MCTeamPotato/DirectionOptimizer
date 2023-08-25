package com.Hileb.teampotato.redirectionor;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import javax.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@IFMLLoadingPlugin.Name(Redirectionor.MODID)
public class Redirectionor implements IFMLLoadingPlugin {

    public static final String MODID = "redirectionor";

    public static final String TRANSFORMERCLASS="com.Hileb.teampotato.redirectionor.RedirectionorTansformer";


    @Override
    public String[] getASMTransformerClass() {
        return new String[]{TRANSFORMERCLASS};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}