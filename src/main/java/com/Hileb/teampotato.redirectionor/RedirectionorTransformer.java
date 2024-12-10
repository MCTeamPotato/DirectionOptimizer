package com.Hileb.teampotato.redirectionor;

import nilloader.api.lib.asm.ClassReader;
import nilloader.api.lib.asm.ClassWriter;
import nilloader.api.lib.asm.Opcodes;
import nilloader.api.lib.asm.tree.AbstractInsnNode;
import nilloader.api.lib.asm.tree.ClassNode;
import nilloader.api.lib.asm.tree.MethodNode;

import java.util.ListIterator;

public class RedirectionorTransformer implements nilloader.api.ClassTransformer {
	@Override
	public byte[] transform(String className, byte[] originalData) {
		if (!RedirectionorConfig.isAvailable(className) || !isEnum(originalData)){
                return originalData;
        }
		try{
            ClassReader classReader = new ClassReader(originalData);
            ClassNode cn = new ClassNode();
            classReader.accept(cn, 0);
            if ((cn.access & 16384) ==0) return originalData;
            for(MethodNode mn:cn.methods){
                if ("values".equals(mn.name) && mn.desc.startsWith("()")){
                    ListIterator<AbstractInsnNode> iterator = mn.instructions.iterator();
                    AbstractInsnNode node;
                    while (iterator.hasNext()){
                        node = iterator.next();
                        int code = node.getOpcode();
                        if (code != Opcodes.GETSTATIC && code != Opcodes.ARETURN){
                            iterator.remove();
                        }
                    }
                    if (RedirectionorConfig.Config.printTransformedClasses) RedirectionorPremain.LOGGER.info("Redirectionor : " + className);
                    ClassWriter classWriter = new ClassWriter(classReader, 0);
                    cn.accept(classWriter);
                    return classWriter.toByteArray();
                }
            }
            return originalData;
        }catch (Throwable ignore){
            return originalData;
        }
	}

    public static boolean isEnum(byte[] clazz){
        if (clazz == null || clazz.length < 8) return false;
        int constantsCount = readUnsignedShort(clazz, 8);
        int passcount = 10;
        for(int i = 0; i < constantsCount; i++){
            switch (clazz[passcount]){
                case 3:
                case 4:
                case 9:
                case 10:
                case 11:
                case 12:
                case 17:
                case 18:
                    passcount += 5;
                    break;
                case 5:
                case 6:
                    passcount += 9;
                    break;
                case 1:
                    passcount += 3 + readUnsignedShort(clazz, passcount + 1);
                    break;
                case 15:
                    passcount += 4;
                    break;
                case 7:
                case 8:
                case 16:
                case 19:
                case 20:
                    passcount += 3;
                    break;
                default:
                    break;
            }
        }
        passcount = readUnsignedShort(clazz, passcount);
        return (passcount & 16384) !=0;
    }

    public static int readUnsignedShort(byte[] b, int index) {
        return ((b[index] & 0xFF) << 8) | (b[index + 1] & 0xFF);
    }
}
