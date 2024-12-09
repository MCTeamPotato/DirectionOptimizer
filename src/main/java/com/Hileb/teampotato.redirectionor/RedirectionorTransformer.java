package com.Hileb.teampotato.redirectionor;

import nilloader.api.lib.asm.ClassReader;
import nilloader.api.lib.asm.ClassWriter;
import nilloader.api.lib.asm.Opcodes;
import nilloader.api.lib.asm.tree.AbstractInsnNode;
import nilloader.api.lib.asm.tree.ClassNode;
import nilloader.api.lib.asm.tree.MethodNode;

import java.util.ListIterator;

public class RedirectionorTransformer extends nilloader.api.ClassTransformer {
	@Override
	public byte[] transform(String className, byte[] originalData) {
		if (!RedirectionorFastUtil.isEnum(originalData) || !RedirectionorFastUtil.isAvailable(className)){
                return originalData;
        }
		try{
            ClassReader classReader = new ClassReader(originalData);
            ClassNode cn = new ClassNode();
            classReader.accept(cn, 0);
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
                    if (RedirectionorConfig.Config.printTransformedClasses) Redirectionor.LOGGER.info("Redirectionor : " + className);
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
}
