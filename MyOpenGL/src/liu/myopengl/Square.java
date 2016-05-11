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
		//float Ϊ4���ֽ�
		ByteBuffer vbb=ByteBuffer.allocateDirect(vertices.length*4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer=vbb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		
		//short Ϊ2���ֽ�
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
		g1.glFrontFace(GL10.GL_CCW);//������ʱ�뷽��Ϊ���ǰ��
		
		//glEnable(int ,,)  ���ڿ������ֹ���
		g1.glEnable(GL10.GL_CULL_FACE);//��������ͼ�β��ϵ���
		
		//ĳ�����������λ�ñ仯�����Ƕ�ֻ�ܿ�����������ɵĶ���ε�ĳһ��ʱ����ʹ�øú���
		//�����ֱ��ʾ���ö����������߱����ϵĹ��ա���Ӱ����ɫ���㼰��������������Ҫ����Ⱦ����
		g1.glCullFace(GL10.GL_BACK);//��������GL_FRONT��GL_BACK
		
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
