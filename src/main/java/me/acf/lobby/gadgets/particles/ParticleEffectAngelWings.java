package me.acf.lobby.gadgets.particles;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import me.acf.lobby.Lag.TPS;
import me.acf.lobby.gadgets.Particles;
import me.acf.lobby.gadgets.loader.ParticleLoader;
import me.hub.Main;
import me.hub.API.Util.UtilParticles;
import me.hub.API.Util.UtilPlayer;
import me.hub.atualizar.Atualizar;
import me.hub.atualizar.ModosUpdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class ParticleEffectAngelWings extends ParticleLoader {

    boolean x = true;
    boolean o = false;
   public ParticleEffectAngelWings(JavaPlugin plugin)
   {
		super("§bAngel Wings", plugin, Material.FEATHER,0, "§eAsa de Anjo.");
   }

    private boolean[][] shape = {
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, x, x, x, x, o, o, o, o, o, o, o, x, x, x, x, o, o},
            {o, o, x, x, x, x, x, o, o, o, x, x, x, x, x, o, o, o},
            {o, o, o, x, x, x, x, x, x, x, x, x, x, x, o, o, o, o},
            {o, o, o, o, x, x, x, x, x, x, x, x, x, o, o, o, o, o},
            {o, o, o, o, x, x, x, x, o, x, x, x, x, o, o, o, o, o},
            {o, o, o, o, o, x, x, x, o, x, x, x, o, o, o, o, o, o},
            {o, o, o, o, o, x, x, o, o, o, x, x, o, o, o, o, o, o},
            {o, o, o, o, x, x, o, o, o, o, o, x, x, o, o, o, o, o}
    };


    @EventHandler
    public void onUpdate(Atualizar e)
    {
      if (e.getType() == ModosUpdate.FAST)
      {
          for (Player player : Particles.Particle.keySet()) {
        	  if (Particles.Particle.get(player).equals("§bAngel Wings"))
        	  {
          	if (Particles.isMoving(player))
          	{
          		if (UtilPlayer.getCardinalDirection(player).equals("South"))
          		drawParticles(player.getLocation().add(0,0,-0.50));
          		if (UtilPlayer.getCardinalDirection(player).equals("North"))
          		drawParticles(player.getLocation().add(0,0,+0.50));
        		if (UtilPlayer.getCardinalDirection(player).equals("West"))
              		drawParticles(player.getLocation().add(+0.50,0,0));
        		if (UtilPlayer.getCardinalDirection(player).equals("East"))
              		drawParticles(player.getLocation().add(-0.50,0,0));
        		if (UtilPlayer.getCardinalDirection(player).equals("WN"))
            		drawParticles(player.getLocation().add(+0.50,0,+0.50));
        		if (UtilPlayer.getCardinalDirection(player).equals("NE"))
            		drawParticles(player.getLocation().add(-0.50,0,+0.50));
        		if (UtilPlayer.getCardinalDirection(player).equals("ES"))
            		drawParticles(player.getLocation().add(-0.50,0,-0.50));
        		if (UtilPlayer.getCardinalDirection(player).equals("SW"))
            		drawParticles(player.getLocation().add(+0.50,0,-0.50));
          	}
          }
          }
      }
    }
    
    
    private void drawParticles(Location location) {
    	 if (TPS.getTPS() < 9.3D) return;
        double space = 0.2;
        double defX = location.getX() - (space * shape[0].length / 2) + space;
        double x = defX;
        double y = location.clone().getY() + 2;
        double angle = -((location.getYaw() + 180) / 60);
        angle += (location.getYaw() < -180 ? 3.25 : 2.985);

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j]) {

                    Location target = location.clone();
                    target.setX(x);
                    target.setY(y);

                    Vector v = target.toVector().subtract(location.toVector());
                    Vector v2 = getBackVector(location);
                    v = rotateAroundAxisY(v, angle);
                    v2.setY(0).multiply(-0.2);

                    location.add(v);
                    location.add(v2);
                    for (int k = 0; k < 3; k++)
                        UtilParticles.display(255, 255, 255, location);
                    location.subtract(v2);
                    location.subtract(v);
                }
                x += space;
            }
            y -= space;
            x = defX;
        }
    }

    public static Vector rotateAroundAxisY(Vector v, double angle) {
        double x, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = v.getX() * cos + v.getZ() * sin;
        z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    public static Vector getBackVector(Location loc) {
        final float newZ = (float) (loc.getZ() + (1 * Math.sin(Math.toRadians(loc.getYaw() + 90 * 1))));
        final float newX = (float) (loc.getX() + (1 * Math.cos(Math.toRadians(loc.getYaw() + 90 * 1))));
        return new Vector(newX - loc.getX(), 0, newZ - loc.getZ());
    }

}