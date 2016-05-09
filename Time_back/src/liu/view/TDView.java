package liu.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;



public class TDView extends View implements Runnable{

	private Paint mPaint;
	private int mCenterX;
	private int mCenterY;
	private Camera mCamera;
	private Matrix mMatrix;
	
	private float mCanvasRotateX = 0;
	private float mCanvasRotateY = 0;
	private float mCanvasMaxRotateDegree = 20;
	
	private Paint point_paint;
	private float pointX=0f;
	private Paint line_paint;
	
	private Date date;
	private SimpleDateFormat df;
	private Paint time_paint;
	public TDView(Context context)
	{
		super(context);
		inits();
	}
	
	public TDView(Context context,AttributeSet attrs)
	{
		super(context,attrs);
		inits();
	}
	public TDView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		inits();
		// TODO 自动生成的构造函数存根
	}

	private void inits() {
		// TODO 自动生成的方法存根
		
		date=new Date();
		df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
		mPaint.setColor(Color.BLACK);
		mPaint.setStyle(Paint.Style.STROKE);
	
		mCamera=new Camera();
		mMatrix=new Matrix();
		
		
		line_paint=new Paint(Paint.ANTI_ALIAS_FLAG);
		line_paint.setColor(Color.RED);
		
		
		point_paint=new Paint(Paint.ANTI_ALIAS_FLAG);
		point_paint.setColor(Color.YELLOW);
		
		
		time_paint=new Paint();
		time_paint.setColor(Color.MAGENTA);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO 自动生成的方法存根
		float x=event.getX();
		float y=event.getY();
		int action=event.getAction();
		
		switch(action)
		{
		case MotionEvent.ACTION_DOWN:
		{
			/*rotateCanvasWhenMove(x,y);*/
			mCanvasRotateX=x;
			mCanvasRotateY=y;
			return true;
		
		}
		case MotionEvent.ACTION_MOVE:
		{
			/*rotateCanvasWhenMove(x, y);*/
			mCanvasRotateX=x;
			mCanvasRotateY=y;
            invalidate();
            return true;
		}
		case MotionEvent.ACTION_UP: {
            mCanvasRotateY = 0;
            mCanvasRotateX = 0;
            invalidate();

            return true;
        }
		}
		return super.onTouchEvent(event);
	}	
	private void rotateCanvasWhenMove(float x, float y) {
		// TODO 自动生成的方法存根
		 	float dx = x - mCenterX;
		    float dy = y - mCenterY;

		    float percentX = dx / mCenterX;
		    float percentY = dy /mCenterY;
		    
		    //如果点击View超出了left 则percentX=-1f
		    if (percentX > 1f) {
		        percentX = 1f;
		    } else if (percentX < -1f) {
		        percentX = -1f;
		    }
		    if (percentY > 1f) {
		        percentY = 1f;
		    } else if (percentY < -1f) {
		        percentY = -1f;
		    }

		   this. mCanvasRotateY = mCanvasMaxRotateDegree * percentX;
		   this. mCanvasRotateX = -(mCanvasMaxRotateDegree * percentY);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO 自动生成的方法存根
		super.onDraw(canvas);
		mCenterX=getWidth()/2;
		mCenterY=getHeight()/2;
		
	/*	Log.i("Test","mCenterX=="+mCenterX);
		Log.i("Test","mCenterY=="+mCenterY);*/
		//canvas.drawCircle(mCenterX, mCenterY, 100, mPaint);
		rotateCanvas(canvas);
		//canvas.translate(1, 1);
        canvas.drawCircle(mCenterX, mCenterY, 100, mPaint);
		
        canvas.save();
        for(int i=0;i<120;i++)
        {
        	canvas.rotate(3, mCenterX, mCenterY);
        	canvas.drawLine(mCenterX, mCenterY-100, mCenterX, mCenterY-90, line_paint);
        }
        
        canvas.restore();
        canvas.save();
        canvas.rotate(pointX, mCenterX, mCenterY);
        canvas.drawLine(mCenterX, mCenterY, mCenterX, mCenterY-80, point_paint);
        canvas.restore();
        canvas.drawText(df.format(date).toString(), 100, 100, time_paint);
	}

	private void rotateCanvas(Canvas canvas) {
		mMatrix.reset();
        mCamera.save();
        mCamera.rotateX(mCanvasRotateX);
        mCamera.rotateY(mCanvasRotateY);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();
       //以图片的中心点为旋转中心,否则以(0,0)点为旋转中心
        mMatrix.preTranslate(-mCenterX, -mCenterY);
        mMatrix.postTranslate(mCenterX, mCenterY);
        //将矩阵作用于整个canvas
        canvas.concat(mMatrix);
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true)
		{
			//this.date=new Date();
			for(float i=0;i<1000;i=(float) (i+0.001))
			{
				this.date=new Date();
				this.pointX=i;
				postInvalidate();
			}
		}
	}

	
}
