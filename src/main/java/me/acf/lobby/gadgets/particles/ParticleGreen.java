/**
 * 
 */
package me.acf.lobby.gadgets.particles;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import me.acf.lobby.gadgets.Particles;
import me.acf.lobby.gadgets.loader.ParticleLoader;
import me.hub.API.Util.UtilParticles;
import me.hub.atualizar.Atualizar;
import me.hub.atualizar.ModosUpdate;




/**
 * @author adriancf
 *
 */
public class ParticleGreen extends ParticleLoader {


   public ParticleGreen(JavaPlugin plugin)
   {
		super(false,"§bParticleGreen", plugin, Material.SADDLE,0, "§eSeja feliz.");
   }
	
    @EventHandler
    public void onUpdate(Atualizar e)
    {
      if (e.getType() == ModosUpdate.TICK)
      {
          for (Player player : Particles.Particle.keySet()) {
        	  if (Particles.Particle.get(player).equals(GetName()))
        	  {
      			float x = (float) (Math.sin(player.getTicksLived()/7d) * 1f);
    			float z = (float) (Math.cos(player.getTicksLived()/7d) * 1f);
    			float y = (float) (Math.cos(player.getTicksLived()/17d) * 1f + 1f);
    			

          		UtilParticles.display(me.hub.API.Util.Particles.VILLAGER_HAPPY, 0.0f, 0.0f, 0.0f, player.getLocation().add(x, y, z), 2);
          	
        	  }
          }
      }
    }
    
    public static void ParticleRain()
    {
    	
    }
}
