package com.theincgi.advancedMacros.lua.functions.entity;

import org.luaj.vm2_v3_0_1.LuaError;
import org.luaj.vm2_v3_0_1.LuaValue;
import org.luaj.vm2_v3_0_1.lib.ThreeArgFunction;
import org.luaj.vm2_v3_0_1.lib.TwoArgFunction;

import com.theincgi.advancedMacros.AdvancedMacros;
import com.theincgi.advancedMacros.event.ForgeEventHandler;
import com.theincgi.advancedMacros.event.ForgeEventHandler.RenderFlags;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class HighlightEntity extends ThreeArgFunction{
	@Override
	public LuaValue call(LuaValue arg, LuaValue action, LuaValue active) {
		Entity e = Minecraft.getMinecraft().world.getEntityByID(arg.checkint());
		boolean flag = active.optboolean(true);
		switch ( action.checkjstring() ) {
		case "glow":{
			RenderFlags r = AdvancedMacros.forgeEventHandler.entityRenderFlags.computeIfAbsent(e, (key)->{ return new RenderFlags();});
			r.setGlow(flag);
			break;
		}
		case "xray":{
			RenderFlags r = AdvancedMacros.forgeEventHandler.entityRenderFlags.computeIfAbsent(e, (key)->{ return new RenderFlags(); });
			r.setXray(flag);
			break;
		}
		default:
			throw new LuaError("Unknown action type '"+action.checkjstring()+"'");
		}
		
		return NONE;
	}
}
