package examples.physics.application;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.dyn4j.geometry.Mass;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.Layer;
import br.com.etyllica.physics.PhysicsApplication;
import br.com.etyllica.physics.RigidBody;

public class BoxesFalling extends PhysicsApplication {
		
	public BoxesFalling(int w, int h) {
		super(w, h);
	}
	
	private RigidBody floor;
	
	private List<RigidBody> crates;	
	
	@Override
	public void load() {
				
		floor = new RigidBody(new Layer(60,500,w-60,20));
		//floor.setMass(MassType.INFINITE);
		floor.setMass(Mass.Type.INFINITE);
		
		this.world.addBody(floor);
				
		crates = new ArrayList<RigidBody>();
		loading = 10;
		
		for(int i=0;i<6;i++){
			
			RigidBody crate = new RigidBody(new ImageLayer(180+50*i, 10+70*i, "crate.jpg"));
						
			this.world.addBody(crate);
			crates.add(crate);
		}
		
		//crates.get(0).getLinearVelocity().set(50, 20.0);
		this.last = System.currentTimeMillis();
		
		loading = 100;
	}

	@Override
	public void update(long now){
				
		updateWorld(now);
	}

	@Override
	public void draw(Graphics g) {

		g.setColor(Color.BLACK);
		g.drawRect(floor.getLayer());
		
		for(RigidBody crate: crates){
			crate.draw(g);
		}
		
	}

	/** The time stamp for the last iteration */
	protected long last;

	private void updateWorld(long time){

		// get the elapsed time from the last iteration
		long diff = time - this.last;
		// set the last time
		this.last = time;
		
		// update the world with the elapsed time
		this.world.update(diff);
		//this.world.updatev(time);
	}

}
