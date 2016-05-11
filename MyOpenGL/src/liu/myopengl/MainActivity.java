package liu.myopengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	Square square;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GLSurfaceView view=new GLSurfaceView(this);
		view.setRenderer(new MyRender());
		
		setContentView(view);
	}

	
	private class MyRender  implements Renderer
	{
		private float angle=30;
		public MyRender()
		{
		 square=new Square();
			//square.draw(g1);
		}
		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			// TODO 自动生成的方法存根
			// Set the background color to black ( rgba ).  
			 gl.glClearColor(1.0f, 0.5f, 0.0f, 0.5f);  // OpenGL docs.  
			 // Enable Smooth Shading, default not really needed.  
			 gl.glShadeModel(GL10.GL_SMOOTH);// OpenGL docs.  
			 // Depth buffer setup.  
			 gl.glClearDepthf(1.0f);// OpenGL docs.  
			 // Enables depth testing.  
			 gl.glEnable(GL10.GL_DEPTH_TEST);// OpenGL docs.  
			 // The type of depth testing to do.  
			 gl.glDepthFunc(GL10.GL_LEQUAL);// OpenGL docs.  
			 // Really nice perspective calculations.  
			 gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, // OpenGL docs.  
			 GL10.GL_NICEST);  
		}

		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			// TODO 自动生成的方法存根
			 // Sets the current view port to the new size.  
			 gl.glViewport(0, 0, width, height);// OpenGL docs.  
			 // Select the projection matrix  
			 gl.glMatrixMode(GL10.GL_PROJECTION);// OpenGL docs.  
			 // Reset the projection matrix  
			 gl.glLoadIdentity();// OpenGL docs.  
			 // Calculate the aspect ratio of the window  
			 GLU.gluPerspective(gl, 45.0f,  
			 (float) width / (float) height,  
			 0.1f, 100.0f);  
			 // Select the modelview matrix  
			 gl.glMatrixMode(GL10.GL_MODELVIEW);// OpenGL docs.  
			 // Reset the modelview matrix  
			 gl.glLoadIdentity();// OpenGL docs.  
		}

		@Override
		public void onDrawFrame(GL10 gl) {
			// TODO 自动生成的方法存根
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | // OpenGL docs.  
					 GL10.GL_DEPTH_BUFFER_BIT);  
			
			gl.glLoadIdentity();  
			gl.glTranslatef(0, 0, -10);
			
			
			//square  A
			gl.glPushMatrix();
			
			gl.glRotatef(angle, 0, 0, 1);
			square.draw(gl);
			
			gl.glPopMatrix();
			
			gl.glPushMatrix();
			
			gl.glRotatef(-angle, 0, 0, 1);
			gl.glTranslatef(2, 0, 0);
			gl.glScalef(.5f, .5f, .5f);
			
			square.draw(gl);
			
			gl.glPushMatrix();
			gl.glRotatef(angle, 0, 0, 1);
			gl.glScalef(.5f, .5f, .5f);
			gl.glTranslatef(2, 0, 0);
			
			square.draw(gl);
			
			gl.glPopMatrix();
			gl.glPopMatrix();
			angle++;
		}

	


		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
