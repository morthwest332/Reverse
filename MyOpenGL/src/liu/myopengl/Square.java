package liu.myopengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Square {

	private float []vertices={
			-1.0f,1.0f,0.0f,
			-1.0f,-1.0f,0.0f,
			1.0f,-1.0f,0.0f,
			1.0f,1.0f,0.0f
	};
	
	private short []indices={0,1,2,0,2,3};
	
	
	private float []colors={
			1.0f,0f,0f,1f,
			0f,1f,0f,1f,
			0f,0f,1f,1f,
			1f,0f,1f,1f
	};
	private ShortBuffer indexBuffer;
	
	private FloatBuffer vertexBuffer;
	
	private FloatBuffer colorBuffer;
	public Square()
	{
		//float 为4个字节
		ByteBuffer vbb=ByteBuffer.allocateDirect(vertices.length*4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer=vbb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		
		//short 为2个字节
		ByteBuffer ibb=ByteBuffer.allocateDirect(indices.length*2);
		ibb.order(ByteOrder.nativeOrder());
		indexBuffer=ibb.asShortBuffer();
		indexBuffer.put(indices);
		indexBuffer.position(0);
		
		ByteBuffer cbb=ByteBuffer.allocateDirect(colors.length*4);
		cbb.order(ByteOrder.nativeOrder());
		colorBuffer=cbb.asFloatBuffer();
		colorBuffer.put(colors);
		colorBuffer.position(0);
	}
	
	public void draw(GL10  g1)
	{
		g1.glFrontFace(GL10.GL_CCW);//设置逆时针方向为面的前面
		
		//glEnable(int ,,)  用于开启各种功能
		g1.glEnable(GL10.GL_CULL_FACE);//启用隐藏图形材料的面
		
		//某对象无论如何位置变化，我们都只能看到构成其组成的多边形的某一面时，可使用该函数
		//参数分别表示禁用多边形正面或者背面上的光照、阴影和颜色计算及操作，消除不必要的渲染计算
		g1.glCullFace(GL10.GL_BACK);//参数包括GL_FRONT和GL_BACK
		
		//g1.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
		g1.glEnableClientState(GL10.GL_COLOR_ARRAY);
		g1.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		g1.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		//g1.glClearColor(0.4f, 0.5f, 0.5f, 1.0f);
		g1.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
		g1.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
		g1.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		g1.glDisable(GL10.GL_CULL_FACE);
	}
}
