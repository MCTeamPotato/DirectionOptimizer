package com.Hileb.teampotato.redirectionor;

import nilloader.api.lib.asm.Symbol;
/**
 * @Project Redirectionor
 * @Author Hileb
 * @Date 2023/8/29 22:00
 **/
public class RedirectionorFastUtil {
    public static boolean isEnum(byte[] clazz){
        if (clazz == null || clazz.length < 8) return false;
        int constantsCount = readUnsignedShort(clazz, 8);
        int passcount = 10;
        for(int i = 1; i < constantsCount; i++){
            switch (clazz[passcount]){
                case Symbol.CONSTANT_FIELDREF_TAG:
                case Symbol.CONSTANT_METHODREF_TAG:
                case Symbol.CONSTANT_INTERFACE_METHODREF_TAG:
                case Symbol.CONSTANT_INTEGER_TAG:
                case Symbol.CONSTANT_FLOAT_TAG:
                case Symbol.CONSTANT_NAME_AND_TYPE_TAG:
                case Symbol.CONSTANT_DYNAMIC_TAG:
                case Symbol.CONSTANT_INVOKE_DYNAMIC_TAG:
                    passcount += 5;
                    break;
                case Symbol.CONSTANT_LONG_TAG:
                case Symbol.CONSTANT_DOUBLE_TAG:
                    passcount += 9;
                    break;
                case Symbol.CONSTANT_UTF8_TAG:
                    passcount += 3 + readUnsignedShort(clazz, passcount + 1);
                    break;
                case Symbol.CONSTANT_METHOD_HANDLE_TAG:
                    passcount += 4;
                    break;
                case Symbol.CONSTANT_CLASS_TAG:
                case Symbol.CONSTANT_STRING_TAG:
                case Symbol.CONSTANT_METHOD_TYPE_TAG:
                case Symbol.CONSTANT_PACKAGE_TAG:
                case Symbol.CONSTANT_MODULE_TAG:
                    passcount += 3;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        passcount = readUnsignedShort(clazz, passcount);
        return (passcount & 16384) !=0;
    }

    public static int readUnsignedShort(byte[] b, int index) {
        return ((b[index] & 0xFF) << 8) | (b[index + 1] & 0xFF);
    }

    public static boolean isAvailable(String name){
        return RedirectionorConfig.Config.isBlock != (isPrefixed(name) || isContained(name));
    }

    public static boolean isContained(String name){
        for(String modid : RedirectionorConfig.Config.contains){
            if (name.contains(modid)){
                return true;
            }
        }
        return false;
    }

    public static boolean isPrefixed(String name){
        for(String modid : RedirectionorConfig.Config.prefix){
            if (name.startsWith(modid)){
                return true;
            }
        }
        return false;
    }
}
