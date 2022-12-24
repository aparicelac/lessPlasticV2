package com.example.lessplastic.Views;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.lessplastic.R;

import java.text.DecimalFormat;

public class CircleGraphView extends View{
    // Valores para las particiones del gráfico de pastel
    private float[] values;
    private String[] categories = {"Bolsas", "Botellas", "Tecnopor", "Empaques", "PVC", "Envases"};
    // Colores para cada partición
    private int[] colors = {
            ContextCompat.getColor(getContext(), R.color.char1),
            ContextCompat.getColor(getContext(), R.color.char2),
            ContextCompat.getColor(getContext(), R.color.char3),
            ContextCompat.getColor(getContext(), R.color.char4),
            ContextCompat.getColor(getContext(), R.color.char5),
            ContextCompat.getColor(getContext(), R.color.char6)

    };
    private Paint paint;
    private Paint paintText;
    private Paint paintLegText;
    private Paint paintLine;
    private DecimalFormat df;
    private RectF rectF;

    public CircleGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        paint = new Paint();
        paintText = new Paint();
        paintLegText = new Paint();
        paintLine = new Paint();
        rectF = new RectF();
        df = new DecimalFormat("#.#");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Obtiene el ancho y alto de la vista
        int width = getWidth();
        int height = getHeight();
        // Calcula el radio del gráfico de pastel
        float radius = Math.min(width*0.5f, height*0.5f) / 2;
        // Establece el rectángulo que contiene el gráfico de pastel
        rectF.set(width / 2 - radius, height / 2 - radius, width / 2 + radius, height / 2 + radius);

        // Variables para almacenar el ángulo inicial y final de cada partición
        float startAngle = 0;
        float endAngle = 0;
        float angleC = 0;

        float[] textX = {0.75f, 0.75f, 0.07f, 0.07f, 0.07f, 0.75f};
        float[] textY = {0.5f, 0.65f, 0.65f, 0.5f, 0.35f, 0.35f};

        int total = 0;
        for (int i = 0; i < values.length; i++){
            total += values[i];
        }
        // Itera sobre cada partición del gráfico de pastel
        for (int i = 0; i < values.length; i++) {
            // Establece el color de la partición
            System.out.println(colors[i]);

            paint.setColor(colors[i]);
            paintText.setColor(colors[i]);
            paintText.setTextSize(36);

            paintLegText.setColor(Color.GRAY);
            paintLegText.setTextSize(24);

            //paintLine.setColor(colors[i]);
            //paintLine.setStrokeWidth(3);

            angleC = values[i]*360/total;

            //System.out.println(values[i]/total * 100+" "+df.format(values[i]/total * 100));
            canvas.drawText(df.format(values[i]/total * 100)+"%", width*textX[i], height*textY[i], paintText);
            canvas.drawText(categories[i], width*textX[i], height*(textY[i]+0.04f), paintLegText);


            // Calcula el ángulo final de la partición
            endAngle = startAngle + angleC;
            // Dibuja la partición del gráfico de pastel
            canvas.drawArc(rectF, startAngle, endAngle - startAngle, true, paint);

            //canvas.drawLine(width*(textX[i]+0.03f), height*(textY[i]+0.03f), (float)x, (float)y, paintLine);

            // Actualiza el ángulo inicial para la siguiente partición
            startAngle = endAngle;
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Obtenemos el tamaño máximo permitido por el layout padre
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // Calculamos el tamaño que necesita nuestro gráfico para cumplir con nuestros requisitos de diseño
        int desiredWidth = (int) (heightSize * 1.5); // proporción de aspecto de 1.5:1
        if (desiredWidth > widthSize) {
            // Si no hay suficiente espacio en el ancho, ajustamos el alto en consecuencia
            desiredWidth = widthSize;
            heightSize = (int) (widthSize / 1.5);
        }

        // Llamamos al método setMeasuredDimension para establecer el tamaño final medido
        setMeasuredDimension(desiredWidth, heightSize);
    }

    public void setValues(float[] v) {
        values = v;
    }
}
