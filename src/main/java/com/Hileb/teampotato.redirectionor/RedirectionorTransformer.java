package com.Hileb.teampotato.redirectionor;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

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
