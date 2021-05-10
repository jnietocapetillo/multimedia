package com.example.multimedia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Paint;


import androidx.annotation.Nullable;


public class Lienzo_clase extends View {

    private Path path;
    private Paint paint;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;

    public Lienzo_clase(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    //configuramos el area sobre la que pintar
    private void setupDrawing()
    {
        path = new Path();
        paint = new Paint();
        paint.setColor(Color.parseColor("#0f0f0f"));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

    }
    //tamaño que asignamos a la vista
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w,h,oldw,oldh);
        canvasBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap,0,0,paint);
        canvas.drawPath(path, paint);
    }

    public boolean onTouchEvent (MotionEvent e)
    {
        //funcion que utilizamos para saber donde presionamos en pantalla
        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x,y);
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(x,y);
                drawCanvas.drawPath(path,paint);
                path.reset();
                break;
            default: return false;
        }

        invalidate(); //refrescamos y dibujamos
        return true; //devolvemos true siempre
    }
    //funcion que actualiza el color del pincel
    public void setColor(String color)
    {
        invalidate();
        paint.setColor(Color.parseColor(color));

    }

    //actualiza el grosor del trazo
    public void setStroke(int ancho)
    {
        invalidate();
        paint.setStrokeWidth(ancho);
    }
}
