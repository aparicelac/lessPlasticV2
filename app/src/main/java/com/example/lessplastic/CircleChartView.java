package com.example.lessplastic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CircleChartView extends View {
    private final ArrayList<String> plasticList = new ArrayList<>();
    private final ArrayList<Double> percentList = new ArrayList<>();
    private final ArrayList<Color> listaColores = new ArrayList<>();
    private int ancho;
    private int alto;
    private final float radio;

    public CircleChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.radio = 0.3f;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        ingresandoDatos();

        //recibiendo el ancho y alto
        this.ancho = getWidth();
        this.alto = getHeight();

        //pintar fondo
        canvas.drawRGB(255, 255, 255);
        //pincel negro
        @SuppressLint("DrawAllocation") Paint pincelNegro = new Paint();
        pincelNegro.setColor(Color.BLACK);
        //pincel de pais
        @SuppressLint("DrawAllocation") Paint colorPais = new Paint();
        //leyenda
        leyendaCirculo(canvas, colorPais, pincelNegro);

        //viendo tamaño de circulo
        //canvas.drawCircle((0.10f+ radio)*ancho, (0.25f+ radio)*alto, radio*ancho, pincel1);

        //requiere de una API 26(el minimo es 21) - Android 8.0 para arriba
        creandoGraficoCircular(canvas, pincelNegro, colorPais);
    }

    private void creandoGraficoCircular(Canvas canvas, Paint pincelNegro, Paint colorPais){
        //el tamanio del segemnto del circulo medido con un rectangulo
        RectF rectangulo = new RectF(0.1f*ancho, 0.25f*alto,
                0.7f*ancho, 0.85f*alto);

        //true: segmento contando el centro
        //false: segmento sin contar el centro
        //canvas.drawArc(rectangulo, 0.0f, 90.0f, true, pincel1);

        float sumaAngulos = 0.0f;
        float sweepAngle = 0.0f;
        float [] xy;

        pincelNegro.setFakeBoldText(true);

        for(int i = 0; i < this.plasticList.size(); i++){
            sweepAngle = percentList.get(i).floatValue()*360.0f/100.0f;
            colorPais.setColor(this.listaColores.get(i).toArgb());
            //pintando grafico
            canvas.drawArc(rectangulo,
                    sumaAngulos, sweepAngle,true, colorPais);
            //porcentajes(texto) dentro del grafico
            xy = textoPorcentajeGrafico(sumaAngulos, sweepAngle);

            canvas.drawText(percentList.get(i)+"%", xy[0], xy[1],pincelNegro);
            //contador
            sumaAngulos += percentList.get(i).floatValue()*360.0f/100.0f;
        }
        //requiere de una API 26(el minimo es 21) - Android 8.0 para arriba
        circuloInteriorGrafico(canvas);
    }

    private void leyendaCirculo(Canvas canvas, Paint colorPais,Paint pincelNegro) {
        //grosor de pincel. No funciona con el texto
        pincelNegro.setStrokeWidth(3);
        //solo texto
        pincelNegro.setTextSize(40);

        canvas.drawText("", 0.25f*ancho,
                0.1f*alto, pincelNegro);

        //cambiando pincel
        pincelNegro.setTextSize(22);
        pincelNegro.setStrokeWidth(1);

        //leyenda
        System.err.println(this.plasticList.size());
        for(int i = 0; i < 3; i++){
            colorPais.setColor(this.listaColores.get(i).toArgb());
            canvas.drawRect(0.75f*ancho,(0.25f + 0.04f * i)*alto,
                    0.77f*ancho, (0.27f + 0.04f * i)*alto, colorPais);
            canvas.drawText(plasticList.get(i), 0.79f*ancho,
                    (0.27f + 0.04f * i)*alto, pincelNegro);
        }
    }

    private void circuloInteriorGrafico(Canvas canvas){
        //dibujando circulo del interior del grafico
        Paint colorCirculoInterior = new Paint();
        //requiere de una API 26(el minimo es 21) - Android 8.0 para arriba

        Color color = Color.valueOf(Color.rgb(240, 240, 240));
        colorCirculoInterior.setColor(color.toArgb());

        canvas.drawCircle((0.10f+ radio)*ancho, (0.25f+ radio)*alto,
                (radio/2.5f)*ancho, colorCirculoInterior);
    }

    private float[] textoPorcentajeGrafico(float startAngle, float sweepAngle) {
        float[] xy = new float[2];
        float currentAngle = (startAngle + sweepAngle / 2) * (float) Math.PI / 180.0f;
        float reajusteX = 126.0f;
        float reajusteY = 72.0f;

        //el 0.7f es del tamaño de circunferencia que toman los textos
        xy[0] = (float) Math.cos(currentAngle) * 0.75f * (this. radio * this.ancho)
                + this.ancho / 2.0f - reajusteX; //eje x
        xy[1] = (float) Math.sin(currentAngle) * 0.75f * (this. radio * this.ancho)
                + this.alto / 2.0f + reajusteY;  //eje y
        return xy;
    }

    public void ingresandoDatos(){

        this.plasticList.add("Botellas");
        this.percentList.add(44.0);
        this.plasticList.add("Bolsas");
        this.percentList.add(26.0);
        this.plasticList.add("Tecnopor");
        this.percentList.add(30.0);

        colorDePaises();
    }

    private void colorDePaises() {
        int r = 240;
        int g = 240;
        int b = 240;
        Color color;
        //r, g, b >= 15 para que no sea de color negro
        for (int i = 0; i < plasticList.size(); i++){
            r = (int) (Math.random()*240) + 15;
            g = (int) (Math.random()*240) + 15;
            b = (int) (Math.random()*240) + 15;
            color = Color.valueOf(Color.rgb(r, g, b));
            this.listaColores.add(color);
        }
    }
}